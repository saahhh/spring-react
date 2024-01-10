package com.kh.api.login.kakao.dto;

import lombok.Builder;
import lombok.Data;

//DTO는 데이터 전송하는 속성
@Builder
@Data
public class KakaoDTO {
	//변수 : 동의항목
	private long id;
	private String email;
	private String nickname;
	
}
