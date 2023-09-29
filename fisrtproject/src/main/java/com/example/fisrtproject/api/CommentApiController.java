package com.example.fisrtproject.api;

import com.example.fisrtproject.dto.CommentDto;
import com.example.fisrtproject.entity.Comment;
import com.example.fisrtproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //1
public class CommentApiController {

    @Autowired //2
    private CommentService commentService; //3. CommentService 만들어주기

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/commnets")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
//        DTO는 클라이언트와 컨트롤러사이에서 엔티티는 서버 내부에서 사용하는게 일반적인 실무 원칙

//        이전 게시글(Article)의 경우 간단히 Article 엔티티를 반환하였으나,
//        댓글(Comment)부터는 엔티티가 아닌 댓글 DTO 즉, CommentDto를 반환하도록 적용
        // 1. 서비스에게 위임
        List<CommentDto> dtos =  commentService.comments(articleId);

        // 2. 결과를 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/commnets")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        // 1. 서비스에게 위임
        CommentDto createDto =  commentService.create(articleId, dto);

        // 2. 결과를 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        // 1. 서비스에게 위임
        CommentDto updateDto =  commentService.update(id, dto);

        // 2. 결과를 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);

    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 1. 서비스에게 위임
        CommentDto deleteDto =  commentService.delete(id);

        // 2. 결과를 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);

    }

}
