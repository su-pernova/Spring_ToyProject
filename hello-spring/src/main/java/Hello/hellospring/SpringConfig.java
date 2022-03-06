// 스프링에 직접 스프링빈을 등록해주기 위해 필요한 스프링 설정파일

package Hello.hellospring;

import Hello.hellospring.repository.MemberRepository;
import Hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // * for Spring Data JPA
    // injection 받으면 SpringDataJPA가 만들어놓은 구현체가 아래에 등록됨
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // * for JPA
    // private final EntityManager em;
    // @Autowired
    // public SpringConfig(EntityManager em) {
    //     this.em = em;
    // }

    // * for JDBC Template
    // private final DataSource dataSource;
    // public SpringConfig(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }

    @Bean
    public MemberService memberService(){
        // 스프링 빈에 등록되어 있는 memberRepository 를 memberService 에 넣어준다.
        // return new MemberService(memberRepository()); // not used for SpringDataJPA
        return new MemberService(memberRepository); // for SpringDataJPA
    }

    // @Bean // not used for Spring Data JPA
    // public MemberRepository memberRepository(){ // 인터페이스
        // return new MemoryMemberRepository(); // 구현체
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    //}
}
