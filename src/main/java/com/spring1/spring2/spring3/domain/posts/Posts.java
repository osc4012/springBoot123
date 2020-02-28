package com.spring1.spring2.spring3.domain.posts;

import com.spring1.spring2.spring3.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // JPA
public class Posts extends BaseTimeEntity {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id;

    // 기본 값 외에 추가로 변경이 필요한 옵션
    // 기본값 : varchar(255)
    // nullable = false : not null
    // @NotNull : 유효성 검사까지 한다고 한다
    @Column(length = 500, nullable = false)
    private String title;

    // columnDefinition = "TEXT" : varchar -> text
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
