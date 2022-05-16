package spring.mvc.dalicious.repository;

import org.junit.jupiter.api.Test;
import spring.mvc.dalicious.domain.Member;
import spring.mvc.dalicious.domain.MemoryMemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("(result == member) = " + (result == member));
        assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();

        List<Member> resultArr = repository.findAll();

        assertThat(result).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);

        assertThat(resultArr.size()).isEqualTo(2);
    }
}
