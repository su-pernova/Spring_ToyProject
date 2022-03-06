package Hello.hellospring.service;

import Hello.hellospring.domain.Member;
import Hello.hellospring.repository.MemberRepository;
import Hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional // JPA를 사용하려면 @Transactional을 꼭 걸어줘야 한다.
public class MemberService {
    private final MemberRepository memberRepository;

    // 외부에서 repository를 생성하여 넣어주도록 작성한 것
    // 무결성 제약조건을 위배하지 않기 위한 장치
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 1. 회원가입 **/
    // ctrl + t : method extract
    // 회원가입시 중복 회원 검증을 위한 함수
    private void validateDuplicateMember(Member member) {
        // cmd+option+v : 'Optional<Member> result =' load 가능
        // get의 반환값은 Optional member 이기 때문에 ifPresent를 사용할 수 있다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /** 2. 회원 조회 **/
    // 서비스 클래스는 비즈니스에 가까운 이름을 지어주는것이 좋다.
    // 서비스 : 비즈니스 의존적, 리포지토리 : 개발 의존적
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
