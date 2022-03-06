package Hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA가 관리하는 Entity를 의미
public class Member {

    // 필요한 데이터 변수 선언
    // Identity 전략 : DB가 알아서 id number를 생성해 주는 것.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Getter, Setter 생성
    public Long getId(){ return id; }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){ return name; }
    public void setName(String name){
        this.name = name;
    }

}
