package com.kh.kakaoApi.controller;

import com.kh.kakaoApi.dto.KakaoDTO;
import com.kh.kakaoApi.repository.KakaoUserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.kakaoApi.common.MsgEntity;
import com.kh.kakaoApi.service.KakaoUserService;
import com.kh.kakaoApi.vo.KakaoUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
@RequestMapping("kakao")
public class KakaoUserController {
    private final KakaoUserService kakaoService;
    private final KakaoUserRepository kakaoUserRepository;
    @GetMapping("/callback")
    public String callback(HttpServletRequest request,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String birthdate,
                           Model model,
                           HttpSession session) throws Exception {

        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"), name, birthdate);
        //카카오톡에서 인증한 이메일을 가지고 오는 것이지 DB에서 존재하는 email을 가지고 오는게 아님
        KakaoUser existingUser = kakaoUserRepository.findByEmail(kakaoInfo.getEmail());
        if (existingUser != null) {
            session.setAttribute("InUser", existingUser);
            return "redirect:/main";
        }
        model.addAttribute("kakaoInfo", kakaoInfo);
        return "register";
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<MsgEntity> registerUser(@RequestParam String email,
                                                 @RequestParam String nickname,
                                                 @RequestParam String name,
                                                 @RequestParam String birthdate) {
        KakaoDTO kakaoDTO = KakaoDTO.builder()
                .email(email)
                .nickname(nickname)
                .name(name)
                .birthdate(birthdate)
                .build();

        KakaoUser registeredUser = kakaoService.registerUser(kakaoDTO);

        return ResponseEntity.ok()
                .body(new MsgEntity("Success", registeredUser));
    }
}