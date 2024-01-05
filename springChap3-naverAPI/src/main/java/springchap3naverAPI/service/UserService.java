package springchap3naverAPI.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import springchap3naverAPI.controller.UserController;
import springchap3naverAPI.model.UserSNS;
import springchap3naverAPI.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/loginSuccess")
	public void naverLoginService(@AuthenticationPrincipal OAuth2User principal,
			@RequestParam(value = "naverResponse", required = false) String naverResponse, Model model) {
		System.out.println("OAuth2User Attribute : " + principal.getAttributes());
		String name = null;
		String email = null;
		// 만약에 네이버 응답이 들어와서 null값이 아니라면
		if (naverResponse != null) {
			// 들어온 naver응답 값을 Json 형식으로 담을 수 있게 Json 형태를 세팅해주고 Json안에 Mapper처리 (공간과 내용을
			// 감싸준다)
			JsonNode responseNode;
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				// .get("response") : {name email}이 보임
				responseNode = objectMapper.readTree(naverResponse).get("response");

				if (responseNode != null) {
					name = responseNode.get("name").asText();
					email = responseNode.get("email").asText();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// OAuth2User 에서 이름과 이메일을 추출
		if (name == null || email == null) { // 값이 없을 때 한번 더 추출
			// principal.getName() 으로 가지고 온 정보에서 이메일과 이름만 출력
			String principalName = principal.getName();

			// replaceAll : 문자열에서 공백이나 숫자 등 패턴을 찾을 때 도와주는 식, 패턴을 찾아 형식을 깨는 식
			String[] keyValue = principalName.replaceAll("[{}]", "").split(",");
			for (String pair : keyValue) {
				String[] entry = pair.split("=");
				if (entry.length == 2) {
					String key = entry[0].trim();
					String value = entry[1].trim();
					if ("name".equals(key)) {
						name = value;
					} else if ("email".equals(key)) {
						email = value;
					}
				}
			}
		}

		// 사용자 정보를 데이터베이스에 저장
		String provider = principal.getName();
		System.out.println("UserController 82 ↓");
		System.out.println("String provider = principal.getName(); " + provider);

		// 1. model
		UserSNS user = new UserSNS();
		user.setName(name);
		user.setEmail(email);
		user.setProvider(provider);

		// 저장
		userRepository.save(user);

		// 안에 모든 속성 가지고 올 때 사용하는 구문
		// model.addAttribute("name", principal.getAttribute("name"));
		// model.addAttribute("email", principal.getAttribute("email"));

		model.addAttribute("name", name);
		model.addAttribute("email", email);

		// 모델이 naverResponse로 가지고 와야하는 경우 Naver 응답 추가
		model.addAttribute("naverResponse", naverResponse);

		// return "loginSuccess";
	}

	@GetMapping("/loginSuccess")
	public void googleLoginService(@AuthenticationPrincipal OAuth2User principal, Model model) {
		model.addAttribute("name", principal.getAttribute("name"));
		model.addAttribute("email", principal.getAttribute("email"));
		System.out.println("OAuth2User Attribute : " + principal.getAttributes());
		String name = null;
		String email = null;

		if (name == null || email == null) {
			String principalName = principal.getName();

			String[] keyValue = principalName.replaceAll("[{}]", "").split(",");
			for (String pair : keyValue) {
				String[] entry = pair.split("=");
				if (entry.length == 2) {
					String key = entry[0].trim();
					String value = entry[1].trim();
					if ("name".equals(key)) {
						name = value;
					} else if ("email".equals(key)) {
						email = value;
					}
				}
			}
		}
		
		// 사용자 정보를 데이터베이스에 저장
		String provider = principal.getName();
		System.out.println("UserController 82 ↓");
		System.out.println("String provider = principal.getName(); " + provider);

		// 1. model
		UserSNS user = new UserSNS();
		user.setName(name);
		user.setEmail(email);
		user.setProvider(provider);

		// 저장
		userRepository.save(user);

		// 안에 모든 속성 가지고 올 때 사용하는 구문
		// model.addAttribute("name", principal.getAttribute("name"));
		// model.addAttribute("email", principal.getAttribute("email"));

		model.addAttribute("name", name);
		model.addAttribute("email", email);

	}
}
