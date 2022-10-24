package kyk.practice_spring;

import kyk.practice_spring.repository.MemberRepository;
import kyk.practice_spring.repository.MemoryMemberRepository;
import kyk.practice_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 빈을 직접 코드 등록 방식
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
