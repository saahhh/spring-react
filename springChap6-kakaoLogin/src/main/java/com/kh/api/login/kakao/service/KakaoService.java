package com.kh.api.login.kakao.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.kh.api.login.kakao.dto.KakaoDTO;

@Service
public class KakaoService {
	@Value("${kakao.client.id}")
	private String KAKAO_CLIENT_ID;
	
	@Value("${kakao.client.secret}")
	private String KAKAO_CLIENT_SECRET;
	
	//value를 썼기 때문에 각 값을 변수에 넣어서 보관하겠다는 의미
	@Value("${kakao.redirect.url}")
	private String KAKAO_REDIRECT_URL;
	
	//URI는 URL보다 큰 범위
	//URL은 URI안에 있는 링크 일 뿐이다
	//카카오 자체에서 인증으로 들어가는 공식 주소
	private final static String KAKAO_AUTH_URI="http://kauth.kakao.com";
	//카카오 자체에서 API로 들어가는 공식 주소
	private final static String KAKAO_API_URI="https://kapi.kakao.com";
	
	public String getKakaoLogin() {
		return KAKAO_AUTH_URI + "/oauth/authorize?client_id=" + KAKAO_CLIENT_ID + "&redict_uri=" + KAKAO_REDIRECT_URL + "&response_type=code";
	}
	
	public KakaoDTO getKakaoInfo(String code) throws Exception {
		if(code == null) throw new Exception("존재하는 인증 코드가 없습니다.");
		
		// 로그인이 허용된 토큰이 들어갈 공간
		String accessToken="";
		// 만약에 토큰이 재발급된다면 재발급된 토큰이 들어갈 공간 (자동로그인이 풀린경우)
		String refreshToken="";

		// http HEADER에 내 정보를 흘려 보내겠다 작성
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", "application/x-www-form-urlencoded");
			
			// 카카오 로그인 아이디 + 시크릿키 + 코드 + 리다이렉트 url 모두 붙여줄 것
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authoriztion_code");
			params.add("client_id", KAKAO_CLIENT_ID);
			params.add("client_secret", KAKAO_CLIENT_SECRET);
			params.add("code", code);
			params.add("redirect_url", KAKAO_REDIRECT_URL);
		} catch (Exception err) {
			err.printStackTrace();
			throw new Exception("api를 불러오지 못했습니다.");
		}
	}
}
