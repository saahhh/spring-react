package springChap3googleAPI.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springChap3googleAPI.model.MemberGoogle;
import springChap3googleAPI.repository.MemberRepository;

@Service
// MemberDetailServiceImpl : 개발자 약속 UserDetailsService : JPA제공
public class MemberDetailServiceImpl implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberGoogle user = memberRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("일치하는 유저 정보를 찾을 수 없습니다." + username));
		
		return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                "", // "{noop}", Oauth 인증에서는 패스워드를 사용하지 않습니다.
                Collections.emptyList());
    }
}
		/*return new org.springframework.security.userdetails.User(
			user.getUsername(),
			"", // 비밀번호를 Oauth 인증에서는 사용하지 않음
			Collections.emptyList());
		*/
