package com.sbs.example.mysqlTextBoard.service;

import java.util.ArrayList;
import java.util.List;

import com.sbs.example.mysqlTextBoard.Container;
import com.sbs.example.mysqlTextBoard.dto.Article;
import com.sbs.example.mysqlTextBoard.dto.Board;
import com.sbs.example.mysqlTextBoard.dto.Member;
import com.sbs.example.mysqlTextBoard.util.Util;

public class BuildService {

	private ArticleService articleService;
	private MemberService memberService;

	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		System.out.println("site/article 폴더 생성");
		Util.mkdirs("site");

		Util.copy("site_template/app.css", "site/app.css");
		Util.copy("site_template/app.js", "site/app.js");

		buildIndexPage();
		buildArticleDetailPages();
		buildListPages();
		buildStat();
	}

	private void buildStat() {
		List<Article> articles = articleService.getArticles();
		List<Board> boards = articleService.getForPrintBoards();
		int totalHit = 0;
		int noticeHit = 0;
		int itHit = 0;
		for(Board board : boards) {
			List<Article> boardArticles = articleService.getForPrintArticles(board.id);
			for(Article article : boardArticles) {
				totalHit += article.hit;
				
				if(board.id == 1) {
					noticeHit += article.hit; 
				}else {
					itHit += article.hit;
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

			List<Article> articles = articleService.getForPrintArticles(board.id);
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
		sb.append(getHeadHtml("article_list_" + board.code));

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

			String link = getArticleDetailFileName(board.getCode(), article.id);

			mainContent.append("<div>");
			mainContent.append("<div class=\"article-list__cell-id\">" + article.id + "</div>");
			mainContent.append("<div class=\"article-list__cell-reg-date\">" + article.regDate + "</div>");
			mainContent.append("<div class=\"article-list__cell-writer\">" + article.extra__writer + "</div>");
			mainContent.append("<div class=\"article-list__cell-title\">");

			mainContent.append("<a href=\"" + link + "\" class=\"hover-underline\">" + article.title + "</a>");

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
		head = head.replace("<main class=\"flex-grow-1\">", "<main class=\"flex-grow-1 back-image\">");		
		head = head.replace("<h1 class=\"con\">","<h1 class=\"con color-w\">");
		sb.append(head);
		String html = "";
		List<Article> articles = articleService.getArticles();
		int count = 0;
		for(Article article : articles) {
			String board = articleService.getBoardByCode(article.boardId);
			count++;			
			html += "<div class=\"list_content\">\n";
			html += "<a href=\"" + board + "-article-detail-" + article.id + ".html\" class=\"link_post\">\n";
			html += "<nav>\n";
			html += "<strong class=\"tit_post\">" + article.getTitle() + "</strong>\n";
			html += "<p class=\"txt_post\">" + article.body + "</p>\n";
			html += "</nav>\n";
			html += "</a>\n";
			html += "<div class=\"detail_into\">\n";
			html += board.toUpperCase() + " LIST , " + article.getRegDate();
			html += "</div></div>";
			if(count ==5) {
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
			List<Article> articles = articleService.getForPrintArticles(board.id);
			int articleCount = articles.size();
			int count = articles.size();
			// 게시물 상세피이지 생성
			for (Article article : articles) {
				String head = getHeadHtml("article_detail", article);
				String html = "";
				String bodyHtml = "";
				String pageHtml = "";
				StringBuilder sb = new StringBuilder();
				String writer = memberService.getMemberName(article.memberId);

				count--;
				sb.append(head);

				html += "<div class=\"article-list__cell-id\">" + article.id + "</div>";
				html += "<div class=\"article-list__cell-title\">" + article.title + "</div>";
				html += "<div class=\"article-list__cell-writer\">" + writer + "</div>";
				html += "<div class=\"article-list__cell-reg-date\">" + article.regDate + "</div>";

				html = template.replace("{$title}", html);

				bodyHtml = article.body;

				html = html.replace("{$body}", bodyHtml);
				
				if (count == articles.size() - 1 && count == 0) {
					pageHtml += "<a href=\"#\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-" + board.getCode() + "-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"#\" class=\"hover-underline\"> 이전글 &gt;</a> ";
					html = html.replace("{$detail-page}", pageHtml);
				} else if (count == articles.size() - 1) {
					pageHtml += "<a href=\"#\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-" + board.getCode() + "-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"" + board.getCode() + "-article-detail-" + (article.id - 1)
							+ ".html\" class=\"hover-underline\"> 이전글 &gt;</a> ";
					html = html.replace("{$detail-page}", pageHtml);
				} else if (count != 0) {
					pageHtml += "<a href=\"" + board.getCode() + "-article-detail-" + (article.id + 1)
							+ ".html\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-" + board.getCode() + "-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"" + board.getCode() + "-article-detail-" + (article.id - 1)
							+ ".html\" class=\"hover-underline\"> 이전글 &gt;</a>";
					html = html.replace("{$detail-page}", pageHtml);
				} else if (count == 0) {
					pageHtml += "<a href=\"" + board.getCode() + "-article-detail-" + (article.id + 1)
							+ ".html\" class=\"hover-underline\">&lt; 다음글 </a>";
					pageHtml += "<a href=\"article-list-" + board.getCode() + "-1.html\" class=\"hover-underline\">목록</a>";
					pageHtml += "<a href=\"#\" class=\"hover-underline\"> 이전글 &gt;</a>";
					html = html.replace("{$detail-page}", pageHtml);
				}
				
				html = html.replace("${site-domain}", "blog.homar.co.kr");
				html = html.replace("${file-name}", getArticleDetailFileName(article.id));
				sb.append(html);
				sb.append(foot);

				String fileName = board.getCode() + "-article-detail-" + article.id + ".html";
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

			String link = "article-list-" + board.code + "-1.html";

			boardMenuContentHtml.append("<a href=\"" + link + "\" class=\"block\">");

			boardMenuContentHtml.append(getTitleBarContentByPageName("article_list_" + board.code));

			boardMenuContentHtml.append("</a>");

			boardMenuContentHtml.append("</li>");
		}

		head = head.replace("${menu-bar__menu-1__board-menu-content}", boardMenuContentHtml.toString());

		String titleBarContentHtml = getTitleBarContentByPageName(pageName);

		head = head.replace("${title-bar__content}", titleBarContentHtml);
		
		String pageTitle = getPageTitle(pageName, relObj) ;
		
		head = head.replace("${page-title}", pageTitle);
		
		
		String siteName = "DongLog";
		String siteSubject = "개발자의 기술/일상 블로그";
		String siteDescription = "개발자의 기술/일상 관련 글들을 공유합니다.";
		String siteKeywords = "HTML, CSS, JAVASCRIPT, JAVA, SPRING, MySQL, 리눅스, 리액트";
		String siteDomain = "blog.homar.co.kr";
		String siteMainUrl = "https://" + siteDomain;
		String currentDate = Util.getNowDateStr().replace(" ", "T");
		
		if ( relObj instanceof Article ) {
			Article article = (Article)relObj;
			siteSubject = article.title;
			siteDescription = article.body;
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
		if(forPringtPageName.equals("index")) {
			forPringtPageName = "home";
		}
		forPringtPageName = forPringtPageName.toUpperCase();
		forPringtPageName = forPringtPageName.replaceAll("-", " ");
		sb.append("DongLog | ");
		sb.append(forPringtPageName);
		
		if(relObj instanceof Article) {
			Article article = (Article) relObj;
			
			sb.insert(0, article.title + " | ");
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
		} else if (pageName.startsWith("article_list_")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>NOTICE LIST</span>";
		} else if (pageName.startsWith("stat")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>STATISTICS</span>";
		}

		return "";
	}
	private String getArticleDetailFileName(int id) {
		return "article_detail_" + id + ".html";
	}
	private String getArticleListFileName(Board board, int page) {
		return getArticleListFileName(board.code, page);
	}

	private String getArticleListFileName(String boardCode, int page) {
		return "article-list-" + boardCode + "-" + page + ".html";
	}

	private String getArticleDetailFileName(String code, int id) {
		return code + "-article-detail-" + id + ".html";
	}

}
