package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PostDetailDto {
    
    private long id;
    private String title;
    private String content;
    private String author;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    
    //Post 타입 객체를 PostDetailDto 타입으로 변환해서 리턴.
    // static : PostDetailDto가 만들어지기 전에 만들어져 있어야하기 때문에
    public static PostDetailDto fromEntity(Post entity) {
        return PostDetailDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdTime(Timestamp.valueOf(entity.getCreated_time()))
                .modifiedTime(Timestamp.valueOf(entity.getModified_time()))
                .build();
        
    }
    
}
