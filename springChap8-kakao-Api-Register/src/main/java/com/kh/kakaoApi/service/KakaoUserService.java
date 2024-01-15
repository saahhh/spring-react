package com.kh.kakaoApi.service;

import com.kh.kakaoApi.dto.KakaoDTO;
import com.kh.kakaoApi.repository.KakaoUserRepository;
import com.kh.kakaoApi.vo.KakaoUser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoUserService {
    @Value("${kakao.client.id}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";

    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    // UserRepository 추가
    private KakaoUserRepository kakaoUserRepository;

    public KakaoUserService(KakaoUserRepository userRepository) {
        this.kakaoUserRepository = userRepository;
    }

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public KakaoDTO getKakaoInfo(String code, String name, String birthdate) throws Exception {
        if (code == null) throw new Exception("API를 불러오지 못했습니다.");

        String accessToken = "";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("code", code);
            params.add("redirect_uri", KAKAO_REDIRECT_URL);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) jsonObj.get("access_token");
        } catch (Exception e) {
            throw new Exception("API 호출 실패");
        }

        return getUserInfoWithToken(accessToken, name, birthdate);
    }

    private KakaoDTO getUserInfoWithToken(String accessToken, String name, String birthdate) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RestTemplate rt = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
        JSONObject account = (JSONObject) jsonObj.get("kakao_account");
        JSONObject profile = (JSONObject) account.get("profile");

        long id = (long) jsonObj.get("id");
        String email = String.valueOf(account.get("email"));
        String nickname = String.valueOf(profile.get("nickname"));

        return KakaoDTO.builder()
                .id(id)
                .email(email)
                .nickname(nickname)
                // 추가
                .name(name) 
                .birthdate(birthdate)
                .build();
    }

    public KakaoUser registerUser(KakaoDTO kakaoDTO) {
        KakaoUser user = new KakaoUser();
        user.setEmail(kakaoDTO.getEmail());
        user.setNickname(kakaoDTO.getNickname());
        user.setName(kakaoDTO.getName());
        user.setBirthdate(kakaoDTO.getBirthdate());

        // 사용자를 데이터베이스에 저장
        return kakaoUserRepository.save(user);
    }
}
