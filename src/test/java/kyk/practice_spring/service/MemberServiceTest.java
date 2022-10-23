package kyk.practice_spring.service;

import kyk.practice_spring.domain.Member;
import kyk.practice_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {
    // 구현 코드와 같은 MemoryMemberRepository 사용법
    MemberService memberService; // 서비스 객체 생성
    MemoryMemberRepository memberRepository;

    @BeforeEach  // 매서드가 실행하기 전에 실행되는 동작
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 매서드가 실행이 끝날 때마다 저장소를 지움
    }

    @AfterEach  // 매서드가 실행이 끝날 때마다의 동작 (각 테스트의 메서드에 선언되었던 내용이 연관되어서는 안되기에 중요!)
    public void afterEach() {
        memberRepository.clearStore(); // 매서드가 실행이 끝날 때마다 저장소를 지움
    }
    
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

       /* // try catch 사용하기에는 애마할 때
        assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // -> memberService.join(member2)의 로직이 실행할 때 IllegalStateException 예외가 발생한다.
        */
        
        /* try catch 방법
        try {
            memberService.join(member2);
            fail(); // 실패로 처리
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        // then
    }

    
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}