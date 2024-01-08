package springchap4signup.mapper;

import org.apache.ibatis.annotations.Mapper;

import springchap4signup.model.Member;

@Mapper
public interface MembersMapper {
	
	void insertMember(Member member);
	
}
