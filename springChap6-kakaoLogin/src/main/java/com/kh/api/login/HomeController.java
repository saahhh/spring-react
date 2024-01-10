package com.kh.api.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.api.login.kakao.service.KakaoService;

import lombok.RequiredArgsConstructor;

@Controller

public class HomeController {
	private KakaoService kakaoService;
	
	public HomeController(KakaoService kakaoService) {
		super();
		this.kakaoService = kakaoService;
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
		return "index";
	}

}
