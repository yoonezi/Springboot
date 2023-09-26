package com.example.fisrtproject.api;

import com.example.fisrtproject.dto.ArticleForm;
import com.example.fisrtproject.entity.Article;
import com.example.fisrtproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로깅을 위한 어노테이션
////1. 먼저 RestController 선언하기
//@RestController
//public class ArticleApiController {
//
//    //    7. 따라서 articleRepository를 필드로 만들어준다.
//    @Autowired //8. 외부에서 가져올 수 있도록 오토와이어드 설정해주기
//    private ArticleRepository articleRepository;
//
//    //    GET
////    2. http://localhost:8080/articles 를 GET 했을 때 모든 articles 를 가져오고 싶음
////    3. 그렇다면 먼저 받아와야한다 > GetMapping
//    @GetMapping("/api/articles")
//    public Iterable<Article> index() { //4. 아티클 리스트를 반환할거니까 List<>
//        return articleRepository.findAll(); //5. 여기까지하면 아티클 임포트 안되어있고, 리스트 임포트 안되어있고, articleRepository 정의가 안되어있음
//    }
//
//    //    -------단일 article 만들기---------
//    @GetMapping("/api/articles/{id}")
//    public Article index(@PathVariable Long id) { //id 사용할거니 선언해주기, 또 여기선 리스트를 반환하는게 아닌 단일이니 Iterable 삭제
//        return articleRepository.findById(id).orElse(null); //조회할땐, findById(id).orElse(null); 추가해주기
//    }
//
//    //    POST
////    1. http://localhost:8080/api/articles에 josn으로 key=value 형식으로 POST하고싶음
////    2. 그렇다면 먼저 POST매핑 해주어야한다. > PostMapping
//    @PostMapping("/api/articles")
////    public Article create(ArticleForm dto) { //4. dto 데이터를 받아와주면 됨  = ArticleForm dto 추가 > 6. 여기까지해보고 json 바디에 데이터를 넣고 post 해보면 데이터 안들어가고 Null값 뜸
//    public Article create(@RequestBody ArticleForm dto) { //7. RequestBody를 추가해주어야함. : request body에서 데이터를 받아와라
//        Article article = dto.toEntity(); //5. dto를 엔티티로 변환하기
//        return articleRepository.save(article); //3. 클라이언트가 전송한 article을 save 해야한다. 하지만 아직 article이 없어서 오류가 날 것임 > dto 데이터를 받아와주면 됨
//    }
//
//    //    PATCH
////     1. http://localhost:8080/api/articles/1에 josn으로 key=value 형식으로 PATCH 수정하고싶음
////     2. PATCH는 PatchMapping을 통해 처리한다.
//    @PatchMapping("/api/articles/{id}")
////    public Article update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) { //return할때 Article을 보내면 안되고 Article을 담아서 보내줘야함. 따라서 ResponseEntity<>
////       딤아서 보내주면 상태코드(200, 400등)을 같이 실어서 보내줄 수가 있음
////        3. 수정용 엔티티 생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
////        4. 대상 엔티티를 조회 (가져옴)
//        Article target = articleRepository.findById(id).orElse(null);
//
////        5. 잘못된 요청 처리 ( 대상이 없거나, id가 다른경우)
//        if (target == null || id != article.getId()) {
//            //400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
////        6. 업데이트 및 정상 응답(200)
//        target.patch(article); // patch 할때, title과 content 두가지가 다 있을때에만 작동하도록 설정해줌 why? 하나만 넣고 보내면 나머지는 Null값이 되니까 예외처리
//        //how? entity-Article에 아래 코드 추가
////        public void patch(Article article) {
////            if (article.title != null )
////                this.title = article.title;
////            if (article.content != null)
////                this.content = article.content;
////        이렇게 해주면 만약 title을 뺀 상태로 content만 수정하는 json을 작성해서 보내도 title은 그대로 유지가 됨
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    //    DELETE
//    //     1. http://localhost:8080/api/articles/1을 DELETE 요청으로 삭제하고 싶음
//    //     2. DELETE는 DeleteMapping을 통해 처리한다.
//
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 대상 찾기
//        Article target =  articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (target ==  null){
////            log.info("잘못된 요청! id: {},", id);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//
//    }



//------- 서비스계층추가 -------
@RestController
public class ArticleApiController {

    @Autowired // 3. DI, 생성 객체를 가져와 연결
    private ArticleService articleservie; //1. ArticleService가 없으니 오류가 뜸 > service 패키지를 만듦


    //    6. 기존에는 리파지토리에서 가져왔는데, 이젠 서비스를 통해 가져올 거임
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleservie.index(); // 7. index 메소드로 가져오게 할 거임 > 없으니 오류 > 만들어주기
    }

//        -------단일 article 만들기---------
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleservie.show(id); // 8. index 메소드로 가져오게 할 거임 > 없으니 오류 > 만들어주기
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleservie.create(dto);
        return (created != null ) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
//
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {

        Article updated = articleservie.update(id, dto);

        return (updated != null ) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleservie.delete(id);
        return (deleted != null ) ? ResponseEntity.status(HttpStatus.OK).body(deleted) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // 트랜잭션 실패 -> 롤백 !
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) { // ResponseEntity에 List형식으로 Article을 던짐
//        RequestBody를 통해 ArticleForm을 받아오고 ArticleForm을 여러개 받으니 List 묶음으로 받아옴
        List<Article> createList = articleservie.createArticle(dtos);

        return (createList != null ) ? ResponseEntity.status(HttpStatus.OK).body(createList) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}

