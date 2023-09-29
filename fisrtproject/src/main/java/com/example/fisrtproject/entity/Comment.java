package com.example.fisrtproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 여러개의 엔티티가, 하나의 아티클에 해당된다
    @JoinColumn(name = "article_id") //article_id에 article의 대표값을 저장
    private Article article;

    @Column
    private String body;

    @Column
    private String nickname;
}
