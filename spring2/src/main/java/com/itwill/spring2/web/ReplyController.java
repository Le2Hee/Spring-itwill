package com.itwill.spring2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.domain.Reply;
import com.itwill.spring2.dto.ReplyCreateDto;
import com.itwill.spring2.dto.ReplyReadDto;
import com.itwill.spring2.dto.ReplyUpdateDto;
import com.itwill.spring2.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // RestController를 사용하기 위해서는 jackson-databind 라이브러리가 필요하다
                // 뷰로 바로가는 컨트롤러
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    // 테스트용 코드 (Rest코드의 개념 설명)
//    @Data @AllArgsConstructor
//    public class Test {
//        private int age;
//        private String name;
//    }
//    
//    @GetMapping // "/api/reply" 가 호출되면 실행되야 한다.
//    public ResponseEntity<Test> hello() { 
//        log.info("hello()");
//        
//        return ResponseEntity.ok(new Test(85, "감자"));
//    }

    // @RestController는 return의 문자열로 jsp를 찾지 않는다. 응답이 문자열"Hello"가 온다.
    // -> 문자열을 보낸다.

    // 기본생성자를 생성하면 null이 나오기 때문에
    // 값을 채우는 방식이 js를 통해 값을 확인하고,
    @PostMapping // Jackson라이브러리가 파씽을 해 값을 채운다.
    public ResponseEntity<Integer> createReply(@RequestBody ReplyCreateDto dto) {
        log.info("ceateReply(dto{})", dto);

        int result = replyService.create(dto);

        // 실패했을때.
        // ResponseEntity.status(500).build(); // 어떤 메서드가 build()라는 메서드를 리턴한다.
        // build()가 ResponseEntity를 만들어주고 error가 나면 500번을 리턴한다.

        return ResponseEntity.ok(result);

        // return ResponseEntity.ok(1); 성공인지 실패인지 구별해서 전송하고, 1을 리턴.
    }
    // Response가 서버에 전달을 해주는 것.

    @GetMapping("/all/{postId}")
    public ResponseEntity<List<ReplyReadDto>> read(@PathVariable long postId) { // {}안 변수랑 맞춰서 설정
        log.info("read(postId={})", postId);
        
        List<ReplyReadDto> list = replyService.read(postId);
        log.info("# of replies = {}", list.size());
        
        return ResponseEntity.ok(list); //Response 데이터를 return하는 것이다.
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteReply(@PathVariable long id) {
        log.info("deleteReply(id = {})",id);
        
        int result = replyService.delete(id);
        
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ReplyReadDto> selectById(@PathVariable long id) {
        log.info("selcetById=({})", id);
        
        ReplyReadDto dto = replyService.readById(id);
        log.info("dto={}", dto);
        
        return ResponseEntity.ok(dto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(
            @PathVariable long id,
            @RequestBody ReplyUpdateDto dto) { //@RequestBody -> Request안에 있는 데이터를 사용.
        log.info("update(id={})", id);
        
        int result = replyService.update(id, dto);
        
        return ResponseEntity.ok(result);
    }
    
}
