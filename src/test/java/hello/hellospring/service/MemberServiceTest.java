package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long savedMemberId = memberService.join(member);

        // then
        Member foundMember = memberService.findOne(savedMemberId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    void duplicatedMember() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        try {
//            memberService.join(member2);
//        } catch (IllegalStateException e ) {
//            assertThat(e.getMessage()).isEqualTo("Already Exists!");
//        }

        // then
        assertThat(e.getMessage()).isEqualTo("Already Exists");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}