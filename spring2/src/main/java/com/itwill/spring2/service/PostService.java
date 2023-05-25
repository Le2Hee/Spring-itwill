package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.repository.PostRepository;
import com.itwill.spring2.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* 넣어주는 이유: 
    application-context.xml에서 <contextLcomponent-scan> 설정에서
    com.itwill.spring2.service 패키지와 그 하위 패키지를 스캔(검색) -> 어떤걸 검색?: 애너테이션. 
    애너테이션을 붙여주면 스프링 컨테이너에서 서비스 컴포넌트 객체를 생성하고 관리를 해준다.
                                                           ※ 관리 -> 필요한 곳에 주입을 해준다. */
@Service
@RequiredArgsConstructor // 2-(2), 클래스타입과 아규먼트를 가지고 있는 생성자를 만들어준다.
// final로 선언된 필드를 초기화하는 생성자.
@Slf4j
public class PostService {
    
    // 의존성 주입(DI: Dependency Injection): (둘 중에 하나만 사용)
    // 1 필드에 의한 의존성 주입. - @Autowired 애너테이션 사용.
    // 2 생성자에 의한 의존성 주입
    //    (1) 필드를 final로 선언
    //    (2) final 변수를 초기화할 수 있는 생성자를 작성 -> @RequiredArgsConstructor
    
//    @Autowired
//    private PostRepository postRepository; -> 1. 필드에 의한 의존성 주입
    
    private final PostRepository postRepository; // 2-(1)
    // @Autowired 애너테이션을 사용하지 않아도 final로 생성가능하다.
    // final은 값을 넣어줘야되지만 @RequiredArgsConstructor로 생성자에 의한 의존성 주입을 시킨다.
    //                              -> 싱글톤
    
    private final ReplyRepository replyRepository; // 하나의 service가 2개 table을 이용 가능.
    
    // 포스트 목록 페이지
    public List<PostListDto> read() {
        log.info("read()");
        
//        List<Post> list = postRepository.selectOrderByIdDesc();
        
//        List<PostListDto> result = new ArrayList<>();
//        for (Post p : list) {
//            result.add(PostListDto.fromEntity(p));
//        }
//        return result;
        
     // 윗 코드를 람다식으로 바꾼것이 밑에 return문 이다.
//        return list.stream().map(PostListDto::fromEntity).toList();
        
        // 댓글 시스템 활성화 후 코드
        
        return postRepository.selectWithReplyCount();
        
        
    }
    
    // 포스트 상세보기 페이지
    public PostDetailDto read(long id) {
        log.info("read(id={})", id);
        
        // DB POTST테이블에서 검색
        Post entity = postRepository.selectById(id);
        
        // 검색한 내용을 Dto로 변환
        PostDetailDto dto = PostDetailDto.fromEntity(entity);
        
        // DB REPLIES 테이블에서 댓글 개수를 검색.
        long count = replyRepository.selectReplyCountWithPostId(id);
        dto.setReplyCount(count);
        
        return dto;
    }
    
    // 새 포스트 작성 페이지
    public int create(PostCreateDto dto) {
        log.info("create({})", dto);
        
        // PostCreateDto 타입을 Post 타입으로 변환해서
        // 리포지토리 계층의 메서드를 호출 - DB Insert.
        return postRepository.insert(dto.toEntity());
    }
    
    // 포스트 업데이트 페이지
    public int update(Post post) {
        log.info("update({})", post);
        
        
        
        return postRepository.updateTilteAndContent(post);
    }
    
    // 포스트 삭제 페이지
    public int delete(long id) {
        log.info("delete({})", id);
        
        return postRepository.deleteById(id);
    }
    
    
    
}
