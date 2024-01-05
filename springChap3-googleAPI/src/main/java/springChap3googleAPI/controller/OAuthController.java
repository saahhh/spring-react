package springChap3googleAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springChap3googleAPI.model.MemberGoogle;
import springChap3googleAPI.service.MemberGoogleService;

@RestController
@RequestMapping("/oauth")
@CrossOrigin(origins="http://localhost:3000")
public class OAuthController {
	@Autowired
	private MemberGoogleService memberGoogleService;
	
	@GetMapping("/loginSuccess")
	public ResponseEntity<String> loginSuccess(@AuthenticationPrincipal OAuth2User oauthUser /*Model model*/) {
		String email = oauthUser.getAttribute("email");
		MemberGoogle user = memberGoogleService.findByUsername(email);
		
		System.out.println("OAuth2User: " + oauthUser);
		System.out.println("이메일 속성: " + email);
		
		//service
		if(user == null) {
			user = new MemberGoogle();
			user.setUsername(email);
			user.setEmail(email);
			memberGoogleService.saveMember(user);

			// model.addAttribute("newUser", true);
			// ResponseEntity 를 사용하면 Model 필요x
			// return "loginSuccess";
		}
		return ResponseEntity.ok("loginSuccess");
	}
		
		/*
		if (!user.isPresent()) {
	        // Create a new MemberGoogle instance
	        MemberGoogle newMember = new MemberGoogle();
	        newMember.setUsername(email);
	        newMember.setEmail(email);
	        
	        // Save the new user
	        memberGoogleService.saveMember(newMember);
	        
	        // Add attribute to indicate a new user
	        model.addAttribute("newUser", true);
	    }
		*/

	
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("로그아웃 됐습니다.");
    }
}
