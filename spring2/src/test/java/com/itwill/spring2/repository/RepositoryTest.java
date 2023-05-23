package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class RepositoryTest {
    
    @Autowired
    private PostRepository postRepository;
    
    // @Test
    public void testPostRepository() {
        Assertions.assertNotNull(postRepository);
        log.info("postRepository = {}", postRepository);
        
        Post post = Post.builder()
                .title("MyBatis 테스트")
                .content("MyBatis 이용한 insert")
                .author("admin")
                .build();
        
        log.info(post.toString());
        
        int result = postRepository.insert(post);
        Assertions.assertEquals(1, result);
        log.info("result={}", result);
    }
    
    //@Test
    public void testSelectOrderByIdDesc() {
        
        List<Post> list = postRepository.selectOrderByIdDesc();
        
        int count = 0;
        
        for (Post p : list) {
            log.info("post={}", p);
            count++;
        }
        
        Assertions.assertEquals(count, list);
    }
    
    //@Test
    public void testSelectById() {
        Post result = postRepository.selectById(26);
        Assertions.assertNotNull(result);
        log.info(result.toString());
        
        result = postRepository.selectById(-1);
        Assertions.assertNull(result);
        log.info("result={}", result); // result.toString()은 null을 출력할 수 없다.
        
    }
    
    //@Test
    public void testUpdateTilteAndContent() {
        Post post = Post.builder()
                .id(41) // 업데이트할 포스트 아이디
                .title("테레테레") // 업데이트할 제목
                .content("스트스트") // 업데이트할 내용
                .build();
        
        int result = postRepository.updateTilteAndContent(post);
        Assertions.assertEquals(1, result);
        log.info("result ={}", result);
    }
    
//    @Test
    public void testDeleteById() {
        int result = postRepository.deleteById(43);
        Assertions.assertEquals(1, result);
        log.info("result={}", result);
    }
    
    
}
