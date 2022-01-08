package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEachTest() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("spring1");

        // when
        repository.save(member);

        // then
        Member foundMember = repository.findById(member.getId()).get();
        assertThat(foundMember).isEqualTo(member);
    }
    
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member foundMember = repository.findByName("spring1").get();
        assertThat(foundMember).isEqualTo(member1);
    }
    
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        List<Member> foundMembers = repository.findAll();
        assertThat(foundMembers.size()).isEqualTo(2);
    }
}