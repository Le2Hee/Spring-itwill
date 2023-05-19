package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // lombok에서 기본 생성자를 자동으로 만들어 주는 기능.
@AllArgsConstructor // lombok에서 모든 필드들을 아규먼트로 전달받는 생성자를 자동으로 만들어 준다.
@Builder //PostBuilder라는 내부class를 만들어준다. 그 안에 setter 역할을 하는 메서드를 만들어주고
         //Post 타입을 리턴하는 build()를 만들어준다.
@Getter // getter 메서드들을 추가 해준다.
@Setter // setter 메서드들을 추가 해준다.
@ToString // toString 메서드를 추가 해준다.
public class Post {
    
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    
    
    
}
