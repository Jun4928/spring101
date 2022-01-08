package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Spring Container가 객체를 생성해서 관리한다.
@Controller
public class MemberController {
    // 다른 여러 컨트롤러들이 memberService 를 가져다 쓸 수 있다.
    // 회원은 여려군데에서 쓰이는데,, 서비스마다 다른 객체를 가지게 된다면?
    // 하나만 생성해서 공용으로 쓰면 된다. => SingleTon 패턴
    // private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // Spring Bean 에 등롥되어 있는 객체를 가져다 넣어준다.
    // Dependency Injection
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
