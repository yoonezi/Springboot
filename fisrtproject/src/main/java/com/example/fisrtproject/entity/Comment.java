package com.example.fisrtproject.entity;

import com.example.fisrtproject.dto.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외처리
            if (dto.getId() != null)
                throw new IllegalArgumentException("댓글 생성 실패 ! 댓글의 id가 있어야합니다.");
            if (dto.getArticleId() != article.getId())
                throw new IllegalArgumentException("댓글 생성 실패 ! 게시글의 id가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getBody(),
                dto.getNickname()
        );
    }

    public void patch(CommentDto dto) {
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패 ! 잘못된 id가 입력되었습니다.");

        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();


    }
}
