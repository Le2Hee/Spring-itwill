package com.itwill.spring1.dto;

import lombok.Data;

// DTO(Data Transfer Object): 데이터 전달을 위한 객체.
// DispatcherServlet <-- Data --> Controller
// Controller <-- Data --> Service

// VO(Value Object): 값을 저장하기 위한 객체.
// DB의 테이블 구조를 자바 클래스로 표현한 객체.

@Data // 필드에 데이터 객체만 생성하면 생성자, get, set, toString, ... 을 자동 생성한다.
public class UserDto {
    // 폼에서 전달한 요청 파라미터 값들을 저장하기 위한 클래스.
    
    private String username;
    private int age;
    
}
