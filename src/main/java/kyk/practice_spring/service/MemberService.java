package kyk.practice_spring.service;

import kyk.practice_spring.domain.Member;
import kyk.practice_spring.repository.MemberRepository;
import kyk.practice_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service // 스프링이 스프링 컨테이너에 넣을 때 서비스로 인식해서 넣어준다.
public class MemberService {
    // MemberRepository는 인터페이스이기에 MemoryMemberRepository을 new로 생성한 것이다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 외부에서 넣어줄 수 있게 하는 방법
    private final MemberRepository memberRepository;

   //  @Autowired // 새로 생성하지 않고 스프링 컨테이너에 있는 MemberRepository를 가져다 연결한다.
    public MemberService(MemberRepository memberRepository) { // memberRepository를 외부에서 넣어준다. = Dependency Injection(DI)
        this.memberRepository = memberRepository;
    }

    // service의 메서드명은 비즈니스 관련명으로 짓는다.
    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 저장소에 저장
        return member.getId();         // 임의로 id 반환
    }

    private void validateDuplicateMember(Member member) { // 같은 이름이 있는 중복 회원 X 메서드
        //        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(m -> {  // member의 값이 있으면 원래 기존에는 if result !=null 같이 사용하는데 Optional이 null을 감싸서 이렇게 작성 가능
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        memberRepository.findByName(member.getName())  // 반환형태가 Optional<Member>이므로 이렇게 바로 작성가능
                        .ifPresent(m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {  // 받아온 id의 맴버 찾기
        return memberRepository.findById(memberId);
    }


}
