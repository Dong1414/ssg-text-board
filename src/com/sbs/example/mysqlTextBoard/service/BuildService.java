package com.sbs.example.mysqlTextBoard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.util.Util;

public class BuildService {

	private ArticleService articleService;
	private MemberService memberService;
	private DisqusApiService disqusApiService;

	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
		disqusApiService = Container.disqusApiService;
	}

	public void buildSite() {
		System.out.println("site/article 폴더 생성");
		Util.mkdirs("site");

		Util.copy("site_template/app.css", "site/app.css");
		Util.copy("site_template/app.js", "site/app.js");
		Util.copy("site_template/scroll_button.js", "site/scroll_button.js");

		loadDisqusData();

		buildIndexPage();
		buildArticleTagPage();
		buildArticleDetailPages();
		buildListPages();
		buildTagListPages();
		buildStat();
	}

	private void buildTagListPages() {
		Map<String, List<Article>> articlesByTagMap = Container.articleService.getArticlesByTagMap();
		String jsonText = Util.getJsonText(articlesByTagMap);
		Util.writeFile("site/article_tag.json", jsonText);

		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("article_tag");
		String foot = Util.getFileContents("site_template/foot.html");

		String html = Util.getFileContents("site_template/article_list_by_tag.html");

		System.out.println(articlesByTagMap.get("DB"));
		
		
		sb.append(head);
		sb.append(html);
		sb.append(foot);

		String filePath = "site/article-search.html";
		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");
	}

	public void buildArticleTagPage() {
		List<Article> articles = articleService.getForPrintArticles(0);
		String jsonText = Util.getJsonText(articles);
		Util.writeFile("site/article_list.json", jsonText);

		Util.copy("site_template/article_search.js", "site/article_search.js");

		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("article_search");
		String foot = Util.getFileContents("site_template/foot.html");

		String html = Util.getFileContents("site_template/search.html");

		sb.append(head);
		sb.append(html);
		sb.append(foot);

		String filePath = "site/article-search.html";
		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");

	}

	private void loadDisqusData() {
		List<Article> articles = articleService.getArticles();

		for (Article article : articles) {
			Map<String, Object> disqusArticleData = disqusApiService.getArticleData(article);

			if (disqusArticleData != null) {
				int likesCount = (int) disqusArticleData.get("likesCount");
				int commentsCount = (int) disqusArticleData.get("commentsCount");

				Map<String, Object> modifyArgs = new HashMap<>();
				modifyArgs.put("id", article.getId());
				modifyArgs.put("likesCount", likesCount);
				modifyArgs.put("commentsCount", commentsCount);

				articleService.modify(modifyArgs);
			}
		}
	}

	private void buildStat() {
		List<Article> articles = articleService.getArticles();
		List<Board> boards = articleService.getForPrintBoards();
		int totalHit = 0;
		int noticeHit = 0;
		int itHit = 0;
		for (Board board : boards) {
			List<Article> boardArticles = articleService.getForPrintArticles(board.getId());
			for (Article article : boardArticles) {
				totalHit += article.getHit();

				if (board.getId() == 1) {
					noticeHit += article.getHit();
				} else {
					itHit += article.getHit();
				}
			}
		}

		String head = getHeadHtml("stat");
		String template = Util.getFileContents("site_template/stat.html");
		String foot = Util.getFileContents("site_template/foot.html");

		String fileName = "stat.html";

		String html = "<meta charset=\"UTF-8\">";
		html += "<div>회원 수 : " + memberService.getMemberCount() + "</div>";
		html += "<div>전체 게시물 수 : " + articles.size() + "</div>";

		html += "<div>공지사항 게시판 게시물 수 : " + articleService.getNoticesCount() + "</div>";
		html += "<div>IT 게시판 게시물 수 : " + articleService.getFreeCount() + "</div>";

		html += "<div>전체 게시물 조회 수 : " + totalHit + "</div>";
		html += "<div>공지사항 게시판 조회 수 : " + noticeHit + "</div>";
		html += "<div>IT 게시판 조회 수 : " + itHit + "</div>";

		html = template.replace("{$body}", html);

		html = head + html + foot;
		String filePath = "site/" + fileName;
		Util.writeFile(filePath, html);

	}

	private void buildListPages() {

		List<Board> boards = articleService.getForPrintBoards();

		int itemsInAPage = 10;
		int pageBoxMenuSize = 10;

		for (Board board : boards) {

			List<Article> articles = articleService.getForPrintArticles(board.getId());

			int articlesCount = articles.size();

			int totalPage = (int) Math.ceil((double) articlesCount / itemsInAPage);

			for (int i = 1; i <= totalPage; i++) {
				buildArticleListPage(board, itemsInAPage, pageBoxMenuSize, articles, i);
			}
		}
	}

	private void buildArticleListPage(Board board, int itemsInAPage, int pageBoxSize, List<Article> articles,
			int page) {
		StringBuilder sb = new StringBuilder();

		// 헤더 시작
		sb.append(getHeadHtml("article_list_" + board.getCode()));

		// 바디 시작
		String bodyTemplate = Util.getFileContents("site_template/list.html");

		StringBuilder mainContent = new StringBuilder();

		int articlesCount = articles.size();
		int start = (page - 1) * itemsInAPage;
		int end = start + itemsInAPage - 1;

		if (end >= articlesCount) {
			end = articlesCount - 1;
		}

		for (int i = start; i <= end; i++) {
			Article article = articles.get(i);

			String link = getArticleDetailFileName(article.getId());

			mainContent.append("<div>");
			mainContent.append("<div class=\"article-list__cell-id\">" + article.getId() + "</div>");
			mainContent.append("<div class=\"article-list__cell-reg-date\">" + article.getRegDate() + "</div>");
			mainContent.append("<div class=\"article-list__cell-writer\">" + article.getExtra__writer() + "</div>");
			mainContent.append("<div class=\"article-list__cell-title\">");

			mainContent.append("<a href=\"" + link + "\" class=\"hover-underline\">" + article.getTitle() + " (" + article.getCommentsCount() + ")</a>");

			mainContent.append("</div>");
			mainContent.append("</div>");
		}

		StringBuilder pageMenuContent = new StringBuilder();

		// 토탈 페이지 계산
		int totalPage = (int) Math.ceil((double) articlesCount / itemsInAPage);

		// 현재 페이지 계산
		if (page < 1) {
			page = 1;
		}

		if (page > totalPage) {
			page = totalPage;
		}

		// 현재 페이지 박스 시작, 끝 계산
		int previousPageBoxesCount = (page - 1) / pageBoxSize;
		int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
		int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;

		if (pageBoxEndPage > totalPage) {
			pageBoxEndPage = totalPage;
		}

		// 이전버튼 페이지 계산
		int pageBoxStartBeforePage = pageBoxStartPage - 1;
		if (pageBoxStartBeforePage < 1) {
			pageBoxStartBeforePage = 1;
		}

		// 다음버튼 페이지 계산
		int pageBoxEndAfterPage = pageBoxEndPage + 1;

		if (pageBoxEndAfterPage > totalPage) {
			pageBoxEndAfterPage = totalPage;
		}

		// 이전버튼 노출여부 계산
		boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
		// 다음버튼 노출여부 계산
		boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;

		if (pageBoxStartBeforeBtnNeedToShow) {
			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxStartBeforePage)
					+ "\" class=\"flex flex-ai-c\">&lt; 이전</a></li>");
		}

		for (int i = pageBoxStartPage; i <= pageBoxEndPage; i++) {
			String selectedClass = "";

			if (i == page) {
				selectedClass = "article-page-menu__link--selected";
			}

			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, i) + "\" class=\"flex flex-ai-c "
					+ selectedClass + "\">" + i + "</a></li>");
		}

		if (pageBoxEndAfterBtnNeedToShow) {
			pageMenuContent.append("<li><a href=\"" + getArticleListFileName(board, pageBoxEndAfterPage)
					+ "\" class=\"flex flex-ai-c\">다음 &gt;</a></li>");
		}

		String body = bodyTemplate.replace("${list-page}", pageMenuContent.toString());
		body = body.replace("${TR}", mainContent.toString());

		sb.append(body);

		// 푸터 시작
		sb.append(Util.getFileContents("site_template/foot.html"));

		// 파일 생성 시작
		String fileName = getArticleListFileName(board, page);
		String filePath = "site/" + fileName;

		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");
	}

	private void buildIndexPage() {
		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("index");
		String foot = Util.getFileContents("site_template/foot.html");

		String mainHtml = Util.getFileContents("site_template/index.html");
		head = head.replace("<video style=\"display: none;\"></video>",
				"<div class=\"jb-box\">\r\n" + "  <video muted autoplay loop>\r\n"
						+ "    <source src=\"index.mp4\" type=\"video/mp4\">\r\n"
						+ "    <strong>Your browser does not support the video tag.</strong>\r\n" + "  </video>\r\n"
						+ "  <div class=\"jb-text\">\r\n"
						+ "    <p>Do not try to be original, just try to be good.</p>\r\n" + "  </div>\r\n" + "</div>");
		head = head.replace("<h1 class=\"con\">", "<h1 class=\"con color-w\">");
		sb.append(head);
		String html = "";
		List<Article> articles = articleService.getArticles();
		int count = 0;

		for (Article article : articles) {
			String board = articleService.getBoardByCode(article.getBoardId());
			count++;

			html += "<div data-aos=\"fade-up\" data-aos-anchor-placement=\"top-bottom\" class=\"list_content\">\n";
			html += "<a href=\"article-detail-" + article.getId() + ".html\" class=\"link_post\">\n";
			html += "<nav>\n";
			html += "<strong class=\"tit_post\">" + article.getTitle() + "</strong>\n";
			html += "<p class=\"txt_post\">" + article.getBody() + "</p>\n";
			html += "</nav>\n";
			html += "</a>\n";
			html += "<div class=\"detail_into\">\n";
			html += board.toUpperCase() + " LIST , " + article.getRegDate();
			html += "</div></div>";
			if (count == 5) {
				break;
			}
		}
		html = mainHtml.replace("${newarticle3}", html);
		sb.append(html);
		sb.append(foot);

		String filePath = "site/index.html";

		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");
	}

	private void buildArticleDetailPages() {
		List<Board> boards = articleService.getForPrintBoards();

		String foot = Util.getFileContents("site_template/foot.html");
		String template = Util.getFileContents("site_template/detail.html");

		for (Board board : boards) {
			List<Article> articles = articleService.getForPrintArticles(board.getId());
			int articleCount = articles.size();
			int count = articles.size();
			// 게시물 상세피이지 생성
			for (Article article : articles) {
				String head = getHeadHtml("article_detail", article);
				String html = "";
				String bodyHtml = "";
				String pageHtml = "";
				StringBuilder sb = new StringBuilder();

				count--;
				sb.append(head);

				html += "<div class=\"article-detail__cell-id\">" + article.getId() + "</div>";
				html += "<div class=\"article-detail__cell-title\">" + article.getTitle() + "</div>";
				html += "<div class=\"article-detail__cell-writer\">" + article.getExtra__writer() + "</div>";
				html += "<div class=\"article-detail__cell-reg-date\">" + article.getRegDate() + "</div>";
				html += "<div class=\"article-detail__likes-count\">추천 : " + article.getLikesCount() + "</div>";
				html += "<div class=\"article-detail__comments-count\">댓글 : " + article.getCommentsCount() + "</div>";

				html = template.replace("{$title}", html);

				bodyHtml = article.getBody();
				html = html.replace("{$body}", bodyHtml);
				
				String tagHtml = "";
				if (!article.getExtra__tagsStr().equals("")) {
					String[] tags = article.getExtra__tagsStr().split("#");
					
					for(int i = 1 ; i < tags.length ; i++) {
						
						tagHtml += "<a href=\"#\" class=\"tag_link\">#" + tags[i] + "  </a>"; 
					}
					
				}				
				html = html.replace("{$tag}", tagHtml);

				if (count == articles.size() - 1 && count == 0) {
					pageHtml += "<a href=\"#\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"#\" class=\"hover-underline\"> 이전글 &gt;</a> ";
					html = html.replace("{$detail-page}", pageHtml);
				} else if (count == articles.size() - 1) {
					pageHtml += "<a href=\"#\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"article-detail-" + (article.getId() - 1)
							+ ".html\" class=\"hover-underline\"> 이전글 &gt;</a> ";
					html = html.replace("{$detail-page}", pageHtml);
				} else if (count != 0) {
					pageHtml += "<a href=\"article-detail-" + (article.getId() + 1)
							+ ".html\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"article-detail-" + (article.getId() - 1)
							+ ".html\" class=\"hover-underline\"> 이전글 &gt;</a>";
					html = html.replace("{$detail-page}", pageHtml);
				} else if (count == 0) {
					pageHtml += "<a href=\"article-detail-" + (article.getId() + 1)
							+ ".html\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"#\" class=\"hover-underline\"> 이전글 &gt;</a>";
					html = html.replace("{$detail-page}", pageHtml);
				}

				html = html.replace("${site-domain}", "blog.homar.co.kr");
				html = html.replace("${file-name}", getArticleDetailFileName(article.getId()));
				sb.append(html);
				sb.append(foot);

				String fileName = "article-detail-" + article.getId() + ".html";
				articleCount--;
				String filePath = "site/" + fileName;

				Util.writeFile(filePath, sb.toString());
				System.out.println(filePath + " 생성");
			}

		}
	}

	private String getHeadHtml(String pageName) {
		return getHeadHtml(pageName, null);
	}

	private String getHeadHtml(String pageName, Object relObj) {
		String head = Util.getFileContents("site_template/head.html");

		StringBuilder boardMenuContentHtml = new StringBuilder();
		List<Board> forPrintBoards = articleService.getForPrintBoards();

		for (Board board : forPrintBoards) {
			boardMenuContentHtml.append("<li>");

			String link = "article-list-" + board.getCode() + "-1.html";

			boardMenuContentHtml.append("<a href=\"" + link + "\" class=\"block\">");

			boardMenuContentHtml.append(getTitleBarContentByPageName("article_list_" + board.getCode()));

			boardMenuContentHtml.append("</a>");

			boardMenuContentHtml.append("</li>");
		}

		head = head.replace("${menu-bar__menu-1__board-menu-content}", boardMenuContentHtml.toString());

		String titleBarContentHtml = getTitleBarContentByPageName(pageName);

		head = head.replace("${title-bar__content}", titleBarContentHtml);

		String pageTitle = getPageTitle(pageName, relObj);

		head = head.replace("${page-title}", pageTitle);

		String siteName = Container.config.getSiteName();
		String siteSubject = "개발자의 기술/일상 블로그";
		String siteDescription = "개발자의 기술/일상 관련 글들을 공유합니다.";
		String siteKeywords = "HTML, CSS, JAVASCRIPT, JAVA, SPRING, MySQL, 리눅스, 리액트";
		String siteDomain = "blog.homar.co.kr";
		String siteMainUrl = "https://" + siteDomain;
		String currentDate = Util.getNowDateStr().replace(" ", "T");

		if (relObj instanceof Article) {
			Article article = (Article) relObj;
			siteSubject = article.getTitle();
			siteDescription = article.getBody();
			siteDescription = siteDescription.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", "");
		}

		head = head.replace("${site-name}", siteName);
		head = head.replace("${site-subject}", siteSubject);
		head = head.replace("${site-description}", siteDescription);
		head = head.replace("${site-domain}", siteDomain);
		head = head.replace("${site-domain}", siteDomain);
		head = head.replace("${current-date}", currentDate);
		head = head.replace("${site-main-url}", siteMainUrl);
		head = head.replace("${site-keywords}", siteKeywords);

		return head;
	}

	private String getPageTitle(String pageName, Object relObj) {
		StringBuilder sb = new StringBuilder();

		String forPringtPageName = pageName;
		if (forPringtPageName.equals("index")) {
			forPringtPageName = "home";
		}
		forPringtPageName = forPringtPageName.toUpperCase();
		forPringtPageName = forPringtPageName.replaceAll("-", " ");

		sb.append(Container.config.getSiteName() + " | ");
		sb.append(forPringtPageName);

		if (relObj instanceof Article) {
			Article article = (Article) relObj;

			sb.insert(0, article.getTitle() + " | ");
		}

		return sb.toString();
	}

	private String getTitleBarContentByPageName(String pageName) {
		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>최신글</span>";
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-file-alt\"></i> <span>ARTICLE DETAIL</span>";
		} else if (pageName.startsWith("article_list_it")) {
			return "<i class=\"fab fa-free-code-camp\"></i> <span>IT LIST</span>";
		} else if (pageName.startsWith("article_list_notice")) {
			return "<i class=\"fas fa-flag\"></i> <span>NOTICE LIST</span>";
		} else if (pageName.startsWith("article_list_free")) {
			return "<i class=\"fab fa-freebsd\"></i> <span>FREE</span>";
		} else if (pageName.equals("article_search")) {
			return "<i class=\"fas fa-search\"></i> <span>ARTICLE SEARCH</span>";
		} else if (pageName.startsWith("article_list_")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>NOTICE LIST</span>";
		} else if (pageName.equals("article_tag")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>TAG LIST</span>";
		}

		return "";
	}

	public String getArticleDetailFileName(int id) {
		return "article-detail-" + id + ".html";
	}

	private String getArticleListFileName(Board board, int page) {
		return getArticleListFileName(board.getCode(), page);
	}

	private String getArticleListFileName(String boardCode, int page) {
		return "article-list-" + boardCode + "-" + page + ".html";
	}
}
