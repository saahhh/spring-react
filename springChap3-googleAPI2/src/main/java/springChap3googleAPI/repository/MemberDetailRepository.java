package springChap3googleAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springChap3googleAPI.model.MemberGoogle;

public interface MemberDetailRepository extends JpaRepository <MemberGoogle, Long> {
	Optional<MemberGoogle> findByUsername(String username);
}
