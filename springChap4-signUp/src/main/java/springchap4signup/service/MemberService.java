package springchap4signup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springchap4signup.mapper.MembersMapper;
import springchap4signup.model.Member;

@Service
public class MemberService {
	@Autowired
	private MembersMapper membersMapper;
	
	//회원 정보 저장하기
	public void signUpMember(Member member) {
		membersMapper.insertMember(member);
	}
	
	public void updateMember(Member member) {
		membersMapper.updateMember(member);
	}
}
