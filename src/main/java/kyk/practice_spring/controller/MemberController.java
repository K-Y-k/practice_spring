package kyk.practice_spring.controller;

import kyk.practice_spring.domain.Member;
import kyk.practice_spring.repository.MemberRepository;
import kyk.practice_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller  // @Controller 선언 시 처음 실행 시 스프링 컨테이너라는 통이 생기는데 해당 클래스 객체를 자동으로 생성하여 스프링에 들어가고 관리한다. = 스프링 빈
             // 직접 자바 코드로 스프링 빈을 등록하는 방식도 @Controller는 사용
public class MemberController {

    private final MemberService memberService;

    // 생성자 주입 방식
    @Autowired // 스프링 컨테이너의 객체를 연결시켜준다.
    // 즉, 새로 new MemberController();로 할 필요 없이 기존의 것을 가져다 쓸 수 있다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    // 세터인젭션 방식(단점: 호출할 때 public으로 열려있어야한다. 중간에 바꿀 일이 없기에 별로)
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 필드 주입방식 (중간에 바꿀 수 없어서 별로임)
    // @Autowired private MemberService memberService;

    @GetMapping("/members/new")  // 조회 및 url 매핑할 때 GetMapping 사용
    public String createForm() {
        return "members/createMemberForm"; // members/createMemberForm을 반환하여 템플릿에서 이것을 찾는다.
    }

    @PostMapping("/members/new")  // html에서 데이터를 form 형식으로 받을 때 PostMapping 사용
    public String create(MemberForm form) { // MemberForm에 전달 받은 이름을 가져옴
        Member member = new Member();    // 맴버 객체 생성
        member.setName(form.getName());  // post로 받아온 이름으로 넣는다.

        memberService.join(member);      // 맴버 등록

        return "redirect:/";  // /로 리다렉트 -> 홈 화면으로 이동
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memberslist = memberService.findMembers();  // 모든 맴버 조회한 것을 리스트로 저장
        model.addAttribute("members", memberslist);  // memberslist의 내용을 model인 members에 담아 html로 넘긴다.
        return "members/memberList";
    }

}
