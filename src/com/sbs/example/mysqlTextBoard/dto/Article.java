package com.sbs.example.mysqlTextBoard.dto;

import java.util.Map;
import lombok.Data;

@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hit;
	private int likesCount;
	private int commentsCount;
	private int hitCount;
	private String extra__tagsStr;

	private String extra__writer;

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
		this.hitCount = (int) map.get("hitCount");
		this.commentsCount = (int) map.get("commentsCount");
		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}
		if (map.containsKey("extra__tagsStr")) {
			this.extra__tagsStr = (String) map.get("extra__tagsStr");
		}
	}	
}
