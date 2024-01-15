package com.kh.kakaoApi.vo;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class KakaoUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="kakaoUser_seq")
	@SequenceGenerator(name="kakaoUser_seq", sequenceName="kakaoUser_seq", allocationSize=1)
	private Long id;

    private String email;
    private String nickname;
    private String name;
    private String birthdate;
}