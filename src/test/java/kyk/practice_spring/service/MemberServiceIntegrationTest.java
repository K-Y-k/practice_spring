package kyk.practice_spring.service;

import kyk.practice_spring.domain.Member;
import kyk.practice_spring.repository.MemberRepository;
import kyk.practice_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 데이터베이스는 쿼리 후 커밋이 이루어져야 저장되는 구조인데 롤백을 해버리면 작업한 내용이 지워지므로
               // @Trasactional은 테스트 시작하기 전에 트랜젝션을 걸어 테스트가 끝날 때 롤백해주어 반영이 안되게 하여 DB에는 실제로 적용이 안됨
               // 즉 다음 테스트를 이어서 할 수 있게함

class MemberServiceIntegrationTest {
    @Autowired MemberService memberService; // test는 필요한 것만 인젝션하여 쓰고 끝이므로 필드 세션으로 간단히 작성
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given : 주어진 상황
        // 회원생성
        Member member = new Member(); // 맴버 객체 생성
        member.setName("spring");

        // when : 실행했을 때
        Long saveId = memberService.join(member); // 회원가입 기능 사용 및 반환된 id saveId로 저장

        // then : 나와야하는 결과
        Member findMember = memberService.findOne(saveId).get(); // 저장했던 saveId로 member 가져오기
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 가져온 member의 이름과 비교
    }

    @Test // 예외일 때도 봐야함 위는 반쪽짜리 코드
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

}