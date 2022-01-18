package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Spring Container가 객체를 생성해서 관리한다.
@Controller
public class MemberController {
    // 다른 여러 컨트롤러들이 memberService 를 가져다 쓸 수 있다.
    // 회원은 여려군데에서 쓰이는데,, 서비스마다 다른 객체를 가지게 된다면?
    // 하나만 생성해서 공용으로 쓰면 된다. => SingleTon 패턴
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // Spring Bean 에 등록되어 있는 객체를 가져다 넣어준다.
    // Dependency Injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }
}
