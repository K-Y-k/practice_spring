package kyk.practice_spring.repository;

import kyk.practice_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);          // store에 넣기 전, sequence 값을 하나 올려주고 id 세팅
        store.put(member.getId(), member); // store에 저장
        return member;                     // 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store에서 id를 꺼내서 넣기, 값이 없으면 null이기에 Optional.ofNullable()로 감싸서 반환한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()                            // 루프를 돌려
                .filter(member -> member.getName().equals(name))  // 람다를 사용한 member의 name이 가져온 파라미터의 name과 같은 확인
                .findAny();    // 하나를 찾고 결과가 옵션으로 반환 끝까지 돌려도 없으면 Optional에 null을 넣어 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  // store의 member가 리스트로 반환
    }

    public void clearStore() {
        store.clear();
    }


}
