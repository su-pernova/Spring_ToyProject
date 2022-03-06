package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 정보 저장
    Optional<Member> findById(Long id); // 회원 ID로 찾기
    Optional<Member> findByName(String name); // 회원 이름으로 찾기
    List<Member> findAll(); // 저장된 모든 회원 리스트 반환
}
