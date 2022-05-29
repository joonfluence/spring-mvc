package spring.mvc.dalicious;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.mvc.dalicious.repository.JdbcMemberRepository;
import spring.mvc.dalicious.repository.JdbcTemplateMemberRepository;
import spring.mvc.dalicious.repository.JpaMemberRepository;
import spring.mvc.dalicious.repository.MemberRepository;
import spring.mvc.dalicious.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

}
