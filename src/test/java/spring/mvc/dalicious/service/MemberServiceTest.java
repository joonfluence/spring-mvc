package spring.mvc.dalicious.service;

import org.junit.jupiter.api.Test;
import spring.mvc.dalicious.domain.Member;
import spring.mvc.dalicious.domain.MemoryMemberRepository;
import spring.mvc.dalicious.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private final MemberService memberService = new MemberService();

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("Joonho Yi");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void DulicatedMemberException(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        Long saveId = memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // try {
        //  memberService.join(member2);
        //  fail();
        // } catch (IllegalStateException e){
        //  assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        // }
    }
}