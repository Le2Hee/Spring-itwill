package com.itwill.spring2.stream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// 스프링 컨텍스트(application-context.xml or servlet-context.xml)를 사용하지 않는 단위 테스트는
// @ExtendWith, @ContextConfiguration 애너테이션을 사용할 필요가 없다.
public class StreamTest {
    
    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println(numbers);
        
        System.out.println("=====================================================");
        
        // numbers에서 홀수들만 필터링한 결과
        List<Integer> odds = numbers.stream().filter((n) -> n % 2 == 1).toList();
        System.out.println(odds);
        
        System.out.println("=====================================================");
        
        // numbers에 원소들의 제곱
        List<Integer> squares = numbers.stream().map((n) -> n * n).toList();
        System.out.println(squares);
        
        System.out.println("=====================================================");
        
        // numbers의 원소들 중 홀수들의 제곱
        List<Integer> oddSquares = numbers.stream().
                filter((n) -> n % 2 == 1).map((n) -> n * n).toList();
        System.out.println(oddSquares);
        
        System.out.println("=====================================================");
        
        List<String> languages = Arrays.asList("Java", "SQL", "JavaScript");
        System.out.println(languages);
        
        System.out.println("=====================================================");
        
        // languages가 가지고 있는 문자열들의 길이를 원소로 갖는 리스트
        List<Integer> lengths = languages.stream().map(String::length).toList();
        System.out.println(lengths);
        
        System.out.println("=====================================================");
        
        List<LocalDateTime> times = Arrays.asList(
                LocalDateTime.of(2023, 5, 24, 11, 37, 33),
                LocalDateTime.of(2023, 2, 21, 8, 25, 3),
                LocalDateTime.of(2023, 5, 25, 3, 20, 37)
        );
        System.out.println(times);
        
        System.out.println("=====================================================");
        
        // LocalDateTime 타입을 Timestamp 타입으로 변환
        List<Timestamp> timestamp = times.stream().map(Timestamp::valueOf).toList();
        System.out.println(timestamp);
        
        
    }
    
}
