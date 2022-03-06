package Hello.hellospring.controller;

import Hello.hellospring.domain.Member;
import Hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// 이렇게 선언하면 MemberController 객체가 스프링 내부적으로 생성되어 스프링이 관리하게 된다
// 이를 스프링 컨테이너에서 스프링 빈이 관리된다고 이야기한다.
// 생성자를 통해 memberService 가 MemberController 에 주입된다 : 생성자 주입
public class MemberController {
    // @ Autowired private MemberService memberService;
    // 이렇게 하는 방법도 있는데, 이를 필드 주입이라고 부른다. But 별로 추천하지 않는 방법.

    // @Autowired
    // public void setMemberService(MemberService memberService){
    //     this.memberService = memberService;
    // }
    // setter 주입 방법 : public 하게 노출이 되어있어야 한다는 단점이 있다.
    // 노출 되어있는 것이 안좋은 이유 : 외부에서 변경시킬 수 있어서 보호가 안된다.
    // 생성 시점 이후에는 변경되지 않도록 막아주는것이 좋다.

    private final MemberService memberService;

    // 생성자. 스프링 컨테이너가 뜰 때 아래 생성자를 호출한다
    // @Autowired : 생성자 내부에서 memberService가 호출될 때
    // 여기에 스프링 컨테이너에 있는 memberService 객체를 연결시켜준다
    // 스프링이 관리하게 되면 객체를 스프링 컨테이너에 등록 후 받아서 쓰도록 바꿔줘야 한다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록을 위한 껍데기를 만드는 과정
    // 회원 등록을 control 하기 위해서 새로운 class MemberForm 을 만들자.
    // url로 들어오는 방식 : get 방식
    // 아래 함수는 createMemberForm 파일을 찾는 작업만 하고
    // thymeleaf 엔진이 creatMemberForm 파일 안에 있는 내용을 렌더링 한다.
    @GetMapping("/members/new")
    public String creatForm(){
        return "members/createMemberForm";
    }

    // 단, 만들어진 멤버는 메모리에 저장되기 때문에 : 서버를 내렸다 다시 올리면 초기화 된다.
    // 따라서 이 데이터들을 파일이나 데이터베이스에 저장해야 한다.
    @PostMapping(value = "members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName()); // 멤버가 만들어진다

        // MemberService에서 만들었던 join 함수 이용하기
        memberService.join(member); // 만들어진 멤버를 넘겨준다
        return "redirect:/"; // 홈 화면으로 돌려준다

    }
    
    @GetMapping("/members")
    public String list(Model model){
        // findMembers 함수를 통해 members 목록을 불러온다.
        List<Member> members = memberService.findMembers();
        // name과 value를 model의 attribute로 담아 넘길 것이다.
        // members 라는 attribute 안에는 모든 회원 리스트가 저장되어 있다.
        model.addAttribute("members", members);
        return "members/memberList";
     }

}
