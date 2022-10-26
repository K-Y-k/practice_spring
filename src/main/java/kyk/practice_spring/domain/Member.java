package kyk.practice_spring.domain;

import javax.persistence.*;

@Entity  // jpa가 관리하는 매핑준비
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // jpa 매핑을 위한 어노테이션 IDENTITY전략은 db가 id를 자동 생성하는 전략을 뜻함
    private Long id;     // 데이터를 구분하기 위한 시스템이 저장하는 id

    // @Column(name = "username")  // DB의 컬러명이 있는 경우 이 어노테이션 사용
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
