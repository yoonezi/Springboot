package com.example.fisrtproject.service;

import com.example.fisrtproject.dto.ArticleForm;
import com.example.fisrtproject.entity.Article;
import com.example.fisrtproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service // 2. 해당 클래스를 서비스로 인식하여 스프링 부트에 객체르 생성(등록)
public class ArticleService {
    @Autowired //5. DI
    private ArticleRepository articleRepository; //4. 해당 ArticleService가 ArticleRepository와 협업할 수 있게 필드 추가해주기

    public Iterable<Article> index() { // 8. articleRepository애서 모든 아티클 가져오게 만듦
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) { //dto를 받았으면 entity로 바꿔주기
        Article article = dto.toEntity();
        if (article.getId() != null ) { // 이미 있는 아이디에 post를 하면 기존 내용이 수정됨 > 방지하기 위해 조건처리
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target =  articleRepository.findById(id).orElse(null);
        if (target ==  null){
            log.info("잘못된 요청! id: {},", id);
            return null;
        }
        articleRepository.delete(target);
        return target;
    }
}
