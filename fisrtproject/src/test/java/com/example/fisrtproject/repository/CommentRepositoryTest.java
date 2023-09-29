package com.example.fisrtproject.repository;

import com.example.fisrtproject.entity.Article;
import com.example.fisrtproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        {
//        Case 1: 4번 게시글의 모든 댓글 조회
            // 입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "굳 윌 헌팅", "Park");
            Comment b = new Comment(2L, article, "아이 엠 샘", "Kim");
            Comment c = new Comment(3L, article, "쇼생크의 탈출", "Choi");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 데이터 출력");
        }

        {
//        Case 2: 1번 게시글의 모든 댓글 조회
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
//        Comment expected = null; > 이게 아니네, 결과값도 비어있는 리스트로 나와야하구나
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 데이터 출력");
        }

        {
//        Case 3: 9번 게시글의 모든 댓글 조회
            // 입력 데이터 준비
            Long articleId = 9L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상하기
//        Comment expected = null; > 이게 아니네, 결과값도 비어있는 리스트로 나와야하구나
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 데이터 출력");
        }
    }
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        {
//        Case 1 : Park
            // 입력 데이터 준비
            String nickname = "Park";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상하기
            Comment a = new Comment(1L, new Article(4L,  "당신의 인생 영화는?", "댓글 ㄱ"), "굳 윌 헌팅", nickname);
            Comment b = new Comment(4L, new Article(5L, "당신의 취미는?", "댓글 ㄱㄱ"), "치킨", nickname);
            Comment c = new Comment(7L, new Article(6L, "당신의 소울 푸드는?", "댓글 ㄱㄱㄱ"), "조깅", nickname);
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글 출력");
        }
        {
//        Case 2 : Kim
            // 입력 데이터 준비
            String nickname = "Kim";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상하기
            Comment a = new Comment(2L, new Article(4L,  "당신의 인생 영화는?", "댓글 ㄱ"), "아이 엠 샘", nickname);
            Comment b = new Comment(5L, new Article(5L, "당신의 취미는?", "댓글 ㄱㄱ"), "샤브샤브", nickname);
            Comment c = new Comment(8L, new Article(6L, "당신의 소울 푸드는?", "댓글 ㄱㄱㄱ"), "유튜브", nickname);
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "Kim 모든 댓글 출력");
        }
    }
}