package com.sbs.example.mysqlTextBoard.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.mysqlTextBoard.dao.MemberDao;
import com.sbs.example.mysqlTextBoard.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = new MemberDao();
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.add(loginId, loginPw, name);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}



	public int getMemberCount() {

		return memberDao.getMemberCount();
	}

	public String getMemberName(int id) {
		return memberDao.getMemberName(id);
	}

}
