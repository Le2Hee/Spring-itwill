package com.itwill.spring2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.PostCreateDto;
import com.itwill.spring2.dto.PostDetailDto;
import com.itwill.spring2.dto.PostListDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그
@RequiredArgsConstructor // 생성자에 의한 의존성 주입.
@RequestMapping("/post") // 컨트롤러 메서드에서 맵핑을 했지만 클래스에도 줄 수 있다.
                         // PostController 클래스의 메서드들은 요청 주소가 "/post"로 시작한다.
                         // "/post" 뒤에 주소만 쓰면 된다.
@Controller // DispatcherServlet에게 컨트롤러 컴포넌트로 등록.
public class PostController {

    private final PostService postService;

    @GetMapping("/list") // GET 방식의 /post/list 요청 주소를 처리하는 메서드.
    public void list(Model model) {
        log.info("list()");

        // 컨트롤러는 서비스 계층의 메서드를 호출해서 서비스 기능을 수행.
        List<PostListDto> post = postService.read();

        // 뷰에 보여줄 데이터를 Model에 저장.
        model.addAttribute("posts", post);

        // 리턴 값이 없는 경우 뷰의 이름은 요청 주소와 같다.
        // /WEB-INF/views/post/list.jsp
        
        
    }

    @GetMapping("/create")
    public void create() {
        log.info("GET: create()");

    }
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.info("POST: create({})", dto);
        
        // 서비스 계층의 메서드를 호출 - 새 포스트 등록
        int result = postService.create(dto);
        log.info("포스트 등록 결과 = {}", result);
        
        //Post - Redirect - Get
        return "redirect:/post/list";
    }
    
    @GetMapping("/detail")
    public void readById(long id, Model model) {
        log.info("readById({})", id);
        
        // 서비스 계층의 메서드를 호출해서 화면에 보여줄 PostDetailDto를 가져옴.
        PostDetailDto dto = postService.read(id);
        log.info("dto={}", dto);
        
        model.addAttribute("post", dto);
    }
    
    @GetMapping("/modify")
    public void modify(long id, Model model) {
        log.info("modify ({})", id);
        
        PostDetailDto dto = postService.read(id);
        
        model.addAttribute("post", dto);
    }
    
    @PostMapping("/delete")
    public String delete(long id) {
        log.info("delete({})", id);
        
        int result = postService.delete(id);
        log.info("삭제 결과 확인= {}", result);
        
        return "redirect:/post/list";
    }
    
    @PostMapping("/update")
    public String update(Post post, Model model) {
        log.info("update({})", post);
        
        int result = postService.update(post);
        log.info("수정 결과 확인={}", result);
        
        PostDetailDto dto = postService.read(post.getId());
        model.addAttribute("post", dto);
        
        return "redirect:/post/detail?id=" + post.getId();
    }
    
}
