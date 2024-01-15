package com.kh.kakaoApi.controller;

import com.kh.kakaoApi.service.KakaoUserService;
import com.kh.kakaoApi.vo.KakaoUser;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final KakaoUserService kakaoService;
    
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());

        return "index";
    }
    
    @GetMapping("/main")
    public String home(Model model, HttpSession session) {
        	KakaoUser InUser = (KakaoUser) session.getAttribute("InUser");
          model.addAttribute("InUser", InUser);
        return "main";
    }

}
