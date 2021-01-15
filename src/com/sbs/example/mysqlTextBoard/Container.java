package com.sbs.example.mysqlTextBoard;

import java.util.Scanner;

import com.sbs.example.mysqlTextBoard.controller.ArticleController;
import com.sbs.example.mysqlTextBoard.controller.BuildController;
import com.sbs.example.mysqlTextBoard.controller.Controller;
import com.sbs.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlTextBoard.service.ArticleService;
import com.sbs.example.mysqlTextBoard.service.BuildService;
import com.sbs.example.mysqlTextBoard.service.DisqusApiService;
import com.sbs.example.mysqlTextBoard.service.GoogleAnalyticsApiService;
import com.sbs.example.mysqlTextBoard.service.MemberService;
import com.sbs.example.mysqlTextBoard.service.TagService;
import com.sbs.example.mysqlTextBoard.service.Tagservice;
import com.sbs.example.mysqlTextBoard.session.Session;

public class Container {

	public static Scanner scanner;

	public static MemberService memberService;
	public static ArticleService articleService;
	public static BuildService buildService;
	public static TagService tagService;
	public static DisqusApiService disqusApiService;
	public static GoogleAnalyticsApiService googleAnalyticsApiService;
	
	public static Controller articleController;
	public static Controller memberController;
	public static Controller buildController;
	
	public static AppConfig config;
	public static Session session;

	

	static {
		config = new AppConfig();
		scanner = new Scanner(System.in);

		session = new Session();

		googleAnalyticsApiService = new GoogleAnalyticsApiService();
		disqusApiService = new DisqusApiService();
		
		tagService = new TagService();
		memberService = new MemberService();
		articleService = new ArticleService();
		buildService = new BuildService();

		
		articleController = new ArticleController();
		memberController = new MemberController();
		buildController = new BuildController();

	}

}
