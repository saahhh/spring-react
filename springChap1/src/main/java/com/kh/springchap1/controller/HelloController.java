package com.kh.springchap1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//api로 전달해서 호출
@RestController 
//Controller : HTML 파일을 바라보게 함
//RestController : 파일이 아니라 Controller 안에 있는 데이터를 전달해줌
@RequestMapping("/api")
//cors : 쿠키나 세션 무언가 접속하는 것을 허용해줄 때 사용 (문만 열어주는 용도)

/*
allowCredentials : 브라우저에서 요청에 대한 응답을 할 때, 요청의 인증 정보(쿠키, HTTP 인증)을 포함할 것인지를 나타낸 것
					인증 정보를 포함한 요청을 서버로 전송할 수 있게 해줌
					인증 정보를 서로 주고 받을 수 있게 해주는 역할을 함
						
*/
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true")
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello from Spring Boot!";
	}
	
	@GetMapping("/greet")
	public String getGreet() {
		return "Hello Srping Boot";
	}
	
	@GetMapping("/java")
	public String getJava() {
		return "Spring boot test code";
	}
	
	//GetMapping react 라는 엔드포인트를 주고 react에서 api호출
	@GetMapping("/react")
	public String getReact() {
		return "Hello React";
	}

}
