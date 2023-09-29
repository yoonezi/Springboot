package com.example.fisrtproject.service;

import com.example.fisrtproject.dto.CommentDto;
import com.example.fisrtproject.entity.Article;
import com.example.fisrtproject.entity.Comment;
import com.example.fisrtproject.repository.ArticleRepository;
import com.example.fisrtproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service //4.
public class CommentService {

    @Autowired //5
    private CommentRepository commentRepository;
    @Autowired //6
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        // 게시글 처리 및 예외 발생
        Article article= articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시물이 없습니다."));


        // 댓글 엔티티 생성
         Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티를 DB로 저장
         Comment created = commentRepository.save(comment);

        // DTO로 변경하여 변환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 게시물이 없습니다."));


        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // DTO로 변경하여 변환
        return CommentDto.createCommentDto(updated);

    }
    
    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 게시물이 없습니다."));

        // DB로 갱신
        commentRepository.delete(target);

        // DTO로 변경하여 변환
        return CommentDto.createCommentDto(target);
    }
}
