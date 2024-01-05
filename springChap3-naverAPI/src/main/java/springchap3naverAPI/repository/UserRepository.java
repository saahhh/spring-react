package springchap3naverAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springchap3naverAPI.model.UserSNS;

public interface UserRepository extends JpaRepository <UserSNS, Long> {

}
