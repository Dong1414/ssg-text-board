package com.sbs.example.mysqlTextBoard;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.example.mysqlTextBoard.apidto.DisqusApiDataListThread;
import com.sbs.example.mysqlTextBoard.util.Util;

public class TestRunner {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class TestDataType1 {
		public int age;
		public String name;
		public int height;
	}

	public void run() {
		testApi3();
	}
	//disqus api와 jackson 연결, 좋아요, 댓글 수 가져오기 
	private void testApi3() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		DisqusApiDataListThread rs = (DisqusApiDataListThread) Util.callApiResponseTo(DisqusApiDataListThread.class,
				url, "api_key=" + Container.config.getDisqusApiKey(), "forum=homar-ssg",
				"thread:ident=it-article-detail-9.html");
		System.out.println(rs.response.get(0).likes + rs.response.get(0).posts);
	}

	private void testJackson() {
		String jsonString = "{\"age\":22, \"name\":\"홍길동\"}";

		ObjectMapper ob = new ObjectMapper();
		Map rs = null;

		try {
			rs = ob.readValue(jsonString, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(rs.get("age"));
	}

	private void testJackson2() {
		String jsonString = "1";

		ObjectMapper ob = new ObjectMapper();
		Integer rs = null;

		try {
			rs = ob.readValue(jsonString, Integer.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(rs);
	}

	private void testJackson3() {
		String jsonString = "[1,2,3]";

		ObjectMapper ob = new ObjectMapper();
		List<Integer> rs = null;

		try {
			rs = ob.readValue(jsonString, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(rs.get(1));
	}

	private void testJackson4() {
		String jsonString = "[{\"age\":22, \"name\":\"홍길동\"},{\"age\":23, \"name\":\"홍길순\"},{\"age\":24, \"name\":\"임꺽정\"}]";

		ObjectMapper ob = new ObjectMapper();
		List<Map<String, Object>> rs = null;

		try {
			rs = ob.readValue(jsonString, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(rs.get(1).get("age"));
	}

	private void testJackson5() {
		String jsonString = "[{\"age\":22, \"name\":\"홍길동\", \"height\":178},{\"age\":23, \"name\":\"홍길순\", \"height\":168},{\"age\":24, \"name\":\"임꺽정\"}]";

		ObjectMapper ob = new ObjectMapper();
		List<TestDataType1> rs = null;

		try {
			rs = ob.readValue(jsonString, new TypeReference<List<TestDataType1>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return;
		}

		System.out.println(rs.get(0).height + rs.get(2).height);
	}

	private void testApi() {
		String url = "https://disqus.com/api/3.0/forums/listThreads.json";
		String rs = Util.callApi(url, "api_key=v89gL9XMjw9ySgdCXhaUOucXeCctWD0wCaQBdE9R4ExzzMW5piyk16v7InvAUxGd",
				"forum=homar-ssg", "thread:ident=it-article-detail-9.html");
		System.out.println(rs);
	}

}