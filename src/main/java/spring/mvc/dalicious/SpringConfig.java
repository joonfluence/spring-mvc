package spring.mvc.dalicious;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.mvc.dalicious.repository.JdbcMemberRepository;
import spring.mvc.dalicious.repository.MemoryMemberRepository;
import spring.mvc.dalicious.repository.MemberRepository;
import spring.mvc.dalicious.service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
    }
}
