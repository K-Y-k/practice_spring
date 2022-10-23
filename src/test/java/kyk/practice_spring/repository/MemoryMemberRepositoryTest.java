package kyk.practice_spring.repository;

import kyk.practice_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


// 테스트 케이스는 여러 기능을 한번에 돌릴 수 있다.(장점)
class MemoryMemberRepositoryTest { // 테스트 케이스는 public 필요없음

    // 테스트할 MemoryMemberRepository로 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  // 매서드가 실행이 끝날 때마다의 동작 (각 테스트의 메서드에 선언되었던 내용이 연관되어서는 안되기에 중요!)
    public void afterEach() {
         repository.clearStore(); // 매서드가 실행이 끝날 때마다 저장소를 지움
    }


    @Test
    public void save() { // save 기능 테스트
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 글자를 가져오는 법 System.out.println("result=" + (result==member));
        assertThat(result).isEqualTo(member); // 저장한 member와 result가 같은지 확인(같으면 녹색불 오류시 빨간불)
    }

    @Test
    public void findByName() {         // findByName 기능 테스트
        Member member1 = new Member(); // Member 객체 생성
        member1.setName("spring1");    // member1 이름 설정
        repository.save(member1);      // member1을 repository에 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); //
        assertThat(result).isEqualTo(member1);  // 저장한 member1와 result가 같은지 확인(같으면 녹색불 오류시 빨간불)

    }

    @Test
    public void findAll() {            // findAll 기능 테스트
        Member member1 = new Member(); // Member 객체 생성
        member1.setName("spring1");    // member1 이름 설정
        repository.save(member1);      // member1을 repository에 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
