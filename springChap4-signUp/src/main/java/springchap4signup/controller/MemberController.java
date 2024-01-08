package springchap4signup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springchap4signup.model.Member;
import springchap4signup.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("member", new Member());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerMember(Member member) {
		memberService.signUpMember(member);
		return "redirect:/";
		//return "redirect:../../"; //메인으로 돌아가기
	}
	
	@GetMapping("/membersInfo")
	public String getAllMember(Model model) {
		List<Member> members = memberService.getAllMember();
		model.addAttribute("members", members);
		return "membersInfo";
	}
	
	@GetMapping("/login")
	public String showLoingFrom(Model model) {
		model.addAttribute("members", new Member());
		return "login";
	}

	@GetMapping("/update/{memberId}")
	public String updateMemberForm(@PathVariable Long memberId, Model model) {
		Member members = memberService.getMemberById(memberId);
		model.addAttribute("members", members);
		return "memberUpdate";
	}
	
	@PostMapping("/update/{memberId}")
	public String updateMember(@PathVariable Long MemberId, @ModelAttribute("members") @Validated Member member, BindingResult result) {
		member.setMemberID(MemberId);
		memberService.updateMember(member);
		return "redirect:/membersInfo";
	}
	

}
