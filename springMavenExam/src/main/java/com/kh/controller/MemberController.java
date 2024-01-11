package com.kh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.service.MemberService;

@Controller
@RequestMapping("/")
public class MemberController {
	private MemberService memberService;
	
	@PostMapping("/login")
	public String memberLogin(String memberId, String memberPwd) {
		//로그인할 때 필요한 코드를 작성해주면 됨
		memberService.loginMember(memberId, memberPwd);
		return "redirect:/";	
	}
}
