package com.kh.api.login.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

//응답받은 결과를 담기위한 공간 Entity

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class MsgEntity {
	private String msg;
	private Object result;
	
	/*
	public MsgEntity(String msg, Object result) {
		this.msg = msg;
		this.result = result;
	} 와 @AllArgsConstructor는 같기때문에 주석처리
	*/
}
