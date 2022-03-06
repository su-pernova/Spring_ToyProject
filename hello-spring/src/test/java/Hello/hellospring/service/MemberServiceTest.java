// main/.../service/MemberService 의 클래스 안에서
// cmd + shift + t 를 누르면 test 파일을 자동적으로 생성할 수 있다.

package Hello.hellospring.service;

import Hello.hellospring.domain.Member;
import Hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() { // 테스트를 실행할 때 마다 repository가 생성된다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test // given, when, then 구조를 사용하면 체계적인 작성이 가능하다
    void 회원가입() { // test는 함수 이름을 한글로 작성해도 된다!
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // then
        // try-catch를 사용하는 방법
        /** try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            // 오류가 정상적으로 잡힌 경우 아래 실행
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        } **/

        // try-catch 보다 더 간단하게 사용할 수 있는 문법
        // 의미 : memberService.join(member2) 을 실행하면
        // IllegalStateException.class 가 발생해야 함
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 엉뚱한 에러(NullPointerException)를 집어넣는 경우
        // assertThrows(NullPointerException.class, () -> memberService.join(member2));

        // 에러 메시지를 검증하는 경우
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findOne() {
    }
}