package com.kh.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.vo.MemberVO;

//MemberMapper -> MemberDAO
@Mapper
public interface MemberDAO {
	MemberVO loginMember(String memberId, String memberPwd);
	
}
