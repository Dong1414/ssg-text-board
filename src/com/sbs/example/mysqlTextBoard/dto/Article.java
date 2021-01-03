package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	public int memberId;
	public int boardId;
	public int hit;
	public int likesCount;
	public int commentsCount;

	public String extra__writer;

	public Article(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.title = (String) map.get("title");
		this.body = (String) map.get("body");
		this.memberId = (int) map.get("memberId");
		this.boardId = (int) map.get("boardId");
		this.hit=(int) map.get("hit");
		this.likesCount = (int) map.get("likesCount");
		this.commentsCount = (int) map.get("commentsCount");
		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}

	public String getId() {

		return id+"";
	}

	public String getRegDate() {
		return regDate;
	}

	public String getTitle() {
		return title;
	}

}
