package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dao.MemberDAO;
import com.kh.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDao;
	
	public MemberVO loginMember(String memberId, String memberPwd) {
		return memberDao.loginMember(memberId, memberPwd);	
	}
}
