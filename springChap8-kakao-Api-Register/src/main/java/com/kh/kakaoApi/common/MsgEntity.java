package com.kh.kakaoApi.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

//응답받은 결과 보여주는 공간
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class MsgEntity {
	private String msg;
	private Object result;
	/*
	@AllArgsConstructor 
	=
	public MsgEntity(String msg, Object result) {
		this.msg = msg;
		this.result = result;
	}
	*/

}
