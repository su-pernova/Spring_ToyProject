package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA는 EntityManager 라는 것을 통해 모든 것이 동작한다.
    // data JPA 라이브러리를 import 하면 Spring Boot가 EntityManager를 생성해준다.
    // Spring Boot가 생성한 EntityManager를 여기에 주입해주면 되는 것
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // 이렇게만 써주면 JPA가 insert쿼리 만들기,setID 등 모두 자동으로 해준다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        // return 값이 null 일수도 있기 때문
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // PK는 위와 같은 방식으로 조회할 수 있으나 findByName은 방식이 조금 다르다
        // JPQL 이라는 객체지향 쿼리 언어를 사용해야 한다(SQL과 거의 동일)
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        // stream().findAny() : findByName 으로는 1개만 찾기 때문에
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // table 대상이 아닌 객체(Entity)를 대상으로 query를 날리는 방식(객체지향)
        // 그럼 그것이 SQL 로 번역이 된다.
        // Member m = Member as m 을 줄여쓴 것
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
