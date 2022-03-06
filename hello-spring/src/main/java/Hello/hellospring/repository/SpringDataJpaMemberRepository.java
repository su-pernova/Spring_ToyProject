package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스 끼리의 상속은 extends 를 사용해야 한다.
// 이렇게 작성해주면 Spring Data JPA가 자동으로 JpaRepository 구현체를 생성해준다.
// 우리는 생성된 구현체를 가져다가 사용하기만 하면 됨.
public interface SpringDataJpaMemberRepository
                 extends JpaRepository<Member,Long>, MemberRepository {
    // 놀랍게도 이것만 작성해줘도 기존에 구현하고자 했던 기능을 모두 사용할 수 있다.
    // 기본 CRUD,find 등의 기능은 Spring Data JPA에서 기존에 만들어져 있는 것을 가져다 사용할 수 있기 때문
    // 단, ID 값 같은 것들과 달리 Name 같은 값들은 정형화 되지 않은 데이터이므로, JPA 에서 제공하지 않는다.
    // 단 이러한 기능은 아래처럼 method 이름만으로 조회할 수 있다.
    @Override
    Optional<Member> findByName(String name);
}
