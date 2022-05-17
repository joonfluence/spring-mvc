package spring.mvc.dalicious;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.mvc.dalicious.domain.MemoryMemberRepository;
import spring.mvc.dalicious.repository.MemberRepository;
import spring.mvc.dalicious.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
