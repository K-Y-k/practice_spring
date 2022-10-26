package kyk.practice_spring;

import kyk.practice_spring.aop.TimeTraceAop;
import kyk.practice_spring.repository.*;
import kyk.practice_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 빈을 직접 코드 등록 방식
@Configuration
public class SpringConfig {

//    private DataSource dataSource; // 스프링 빈이 데이터 베이스와 연동되어 있는지 확인하고 있으면 스프링이 자체적으로 만들어서 제공하는 데이터 소스
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    
//    // Jpa를 사용하기 위한 EntityManager 생성
//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean  // aop를 스프링 빈 등록
    public TimeTraceAop TimeTraceAop() {
        return new TimeTraceAop();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean  스프링 JPA 인터페이스 사용시 필요 없음
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository(); // 메모리 레포지토리일 때
///*
//        return new JdbcMemberRepository(dataSource); // Jdbc로 연결한 리포지토리일 때
//        // 스프링이 지원하는 DI때문에 이것만 수정하면 되게끔 할 수 있었던 것 과거에는 서비스 함수에 일일히 수정했어야함
//*/
////        return new JdbcTemplateMemberRepository(dataSource); // Jdbc 템플릿
//
////        return new JpaMemberRepository(em); // jpa
//       
//    }
}
