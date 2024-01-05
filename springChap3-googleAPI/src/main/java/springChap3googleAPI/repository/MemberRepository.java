package springChap3googleAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springChap3googleAPI.model.MemberGoogle;

public interface MemberRepository extends JpaRepository <MemberGoogle, Long> {
	//추가적으로 필요한 메서드 작성
	Optional<MemberGoogle> findByUsername(String username);
}
