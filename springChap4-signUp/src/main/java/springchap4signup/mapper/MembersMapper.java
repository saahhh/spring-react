package springchap4signup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springchap4signup.model.Member;

@Mapper
public interface MembersMapper {
	
	void insertMember(Member member);

	void loginMember(String username, String password);
	
	void updateMember(Member member);

	List<Member> getAllMember();
	
	Member getMemberById(Long memberId);
	
}
