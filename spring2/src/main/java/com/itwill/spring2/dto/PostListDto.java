package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

// postlist를 보여줄 dto, 읽기 전용
@AllArgsConstructor // 필드들을 전부다 아규먼트로 가지고 있는 생성자를 생성.
@Builder
@Getter
@ToString
public class PostListDto {
    
    private long id;
    private String title;
    private String author;
    private Timestamp createdTime;
    // JSTL에서는 LocalDateTime 객체를 사용하지 못하기 때문에 TimeStamp 타입으로 선언.
    
    // Post 타입의 객체를 PostListDto 타입의 객체로 변환해서 리턴하는 메서드.
    // Post는 6개짜리 객체인데 4개로 만들어서 리턴. (list UI)
    public static PostListDto fromEntity(Post entity) {
        
        return PostListDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .createdTime(Timestamp.valueOf(entity.getCreated_time()))
                .build();
    }
    
}
