package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
// import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // method의 실행이 끝날 때 마다 특정 동작을 수행하는 callback method
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    // save 기능 잘 작동하지는지 Test
    public void save(){
        // 변수 선언
        Member member = new Member();
        member.setName("spring");

        repository.save(member); // 저장
        Member result = repository.findById(member.getId()).get(); // findById

        // 검증 : 내가 생성하여 저장한 것과 저장된 데이터가 동일한지
        // System.out.println("result = " + (result == member)); // 직접 프린트 해보는 방법
        // Assertions.assertEquals(member,result); // 오류 처리로 해결하는 방법
        // Assertions.assertEquals(member,null); // 오류로 처리되는 경우

        // assertj : 좀 더 직관적으로 편하게 사용할 수 있는 도구
        // Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
        // Assertions 에서 option+enter : static import 가능
        // static import 하는 경우 앞에 Assertions. 붙이지 않아도 됨
        // assertThat(member).isEqualTo(null); // 오류로 처리되는 경우
    }

    @Test
    // findByName 기능 잘 작동하지는지 Test
    public void findByName(){
        // 변수 선언 & save
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // findByName
        // Member result = repository.findByName("spring2").get(); // 오류로 처리되는 경우
        assertThat(result).isEqualTo(member1); // member1 == sprint1 이므로 Test Pass
    }
    // 좌측 파일리스트의 파일 우클릭, Run 하면 모든 Test를 동시에 수행 가능
    // 모든 Class에 대한 검증을 빠르게 할 수 있다는 장점

    @Test
    // findAll 기능 잘 작동하지는지 Test
    public void findAll(){
        // 변수선언 + save
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); // findByName
        assertThat(result.size()).isEqualTo(2); // 검증
        // assertThat(result.size()).isEqualTo(3); // 오류로 처리되는 경우
    }
    // 그러나 이 상태에서 file Run을 하면 findByName에서 에러가 난다
    // findAll이 먼저 수행되고, 데이터가 clear 되지 않은 채 findByName으로 넘어가기 때문
    // test 하나가 끝날때 마다 데이터를 clear 해줘야 한다.
    // MemoryMemberRepositoryTest 부분에 @AfterEach를 추가해주기

    // TDD : 테스트케이스 주도 개발. 테스트 케이스를 먼저 만들어 놓고 개발을 하는 방법
    // 테스트케이스는 소스코드의 양이 많아질 때, 여러명이 함께 개발할 때 필수적인 부분이다!


}
