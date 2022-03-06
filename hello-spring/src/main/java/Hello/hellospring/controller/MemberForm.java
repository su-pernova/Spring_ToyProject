package Hello.hellospring.controller;

public class MemberForm {
    // members/{file}.html의 name과 이 name이 matching된다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
