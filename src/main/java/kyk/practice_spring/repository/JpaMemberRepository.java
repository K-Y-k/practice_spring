package kyk.practice_spring.repository;

import kyk.practice_spring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // jpa는 EntityManager로 모든 것을 동작한다.
                                    // 즉 스프링이 자동으로 EntityManager를 주입 받아야한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);  // 이것만으로 JPA가 쿼리 다 만들고 맴버에 setid까지 모두 작업해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입과 식별자 pk 넣기
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // pk가 아닌 항목이나 리스트를 넣고 활용해야할 경우 sql을 넣어야함
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny(); }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();                                          // 객체를 대상으로 쿼리를 보내고 sql로 번역

    }
}
