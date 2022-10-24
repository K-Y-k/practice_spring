package kyk.practice_spring.controller;

public class MemberForm {  // Post로 전달 받기 위한 생성자
    private String name; // createMemberForm.html의 input태그의 name속성의 "name"이 private이므로 setName을 통해서 전달되었다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
