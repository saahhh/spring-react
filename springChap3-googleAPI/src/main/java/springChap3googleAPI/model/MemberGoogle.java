package springChap3googleAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MemberGoogle {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mg_seq")
	@SequenceGenerator(name="mg_seq", sequenceName="mg_seq", allocationSize=1)
	private Long id; // 기본키
	private String email;
	private String username;
	
	public MemberGoogle() {

	}
	
	public MemberGoogle(String email, String username) {
		this.email = email;
		this.username = username;
	}
	
}
