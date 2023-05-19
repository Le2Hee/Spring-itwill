package com.itwill.spring2.dto;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter, toString, equals, hashCode
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 필드를 야규먼트로 갖는 생성자
@Builder
public class PostCreateDto {

    private String title;
    private String content;
    private String author;
    
    //PostCreateDto 타입의 객체를 Post 타입의 객체로 변환해서 리턴.
    public Post toEntity() {
        // return new Post(0, title, content, author, null, null); 와 같은 코드.
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
