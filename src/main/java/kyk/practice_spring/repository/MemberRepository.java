package kyk.practice_spring.repository;

import kyk.practice_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 회원 객체를 저장할 저장소
public interface MemberRepository {
    Member save(Member member); // 저장된 회원 반환
    Optional<Member> findById(Long id); // 저장한 회원 id 찾기  (데이터가 null을 가질 수 있기에 최근 Optional로 감싸는 방법을 선호)
    Optional<Member> findByName(String name); // 저장한 회원 이름 찾기
    List<Member> findAll(); // 저장된 모든 히원 리스트 반환

}
