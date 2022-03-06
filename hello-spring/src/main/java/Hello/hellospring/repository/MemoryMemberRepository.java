package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; // 키 값(id값)을 생성해주는 역할

    // MemberRepository 에서 option+enter 후
    // implement method 하면 아래 항목 생성 가능
    public Member save(Member member) {
        member.setId(++sequence); // setting member ID. 자동으로 1씩 증가한다.
        store.put(member.getId(), member); // store(MAP)에 member 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 저장된 id 가 없는 경우를 대비하여 Optional.ofNullable로 감싸준다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // Map 인데 반환은 List 형태
        // values = Map<Long,Member> 의 Member
        return new ArrayList<>(store.values());
    }

    // Test 에서 데이터 초기화에 사용되는 부분
    public void clearStore(){
        store.clear();
    }
}
