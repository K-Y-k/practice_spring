package kyk.practice_spring.repository;

import kyk.practice_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {  // 인터페이스는 인터페이를 받을 때 extends 상속(다중상속 또한 가능)
// 스프링 JPA가 보고 구현체를 자동으로 만들어준다.

    // 기본적인 CRUD가 내장되어 있다. 그렇기에 기존 CRUD 관련 기능이 없는 것
    @Override
    Optional<Member> findByName(String name); // = select m from Member m where m.name = ? 으로 짜준다.
    // Optional<Member> findByNameAAndId(String name, Long id); // 이중 검색

}
