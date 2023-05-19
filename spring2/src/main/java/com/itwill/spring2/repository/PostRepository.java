package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.domain.Post;

// application-context.xml에서 scan하는 패키지에 있기 때문에
// 인터페이스를 구현하는 클래스가 MyBatis에 의해서 자동으로 만들어짐.

/* MyBatis는 구현을 어떻게 할까?

    resources파일 밑 post-mapper.xml 파일에서 설정된 id와 메서드 이름이 같으면,
    해당 아이디에 SQL 문장을 실행하는 구현 메서드를 만들어주기 때문이다.  
    
    메서드의 풀네임은 com.itwill.spring2.repository.PostRepository.insert 이다.
    mapper xml파일에 namespace="com.itwill.spring2.repository.PostRepository"로 위치가 선정되어 있고
    insert태그의 id="insert"가 메서드의 이름이고 그 안에 SQL 문장을 실행시키는 메서드를 만들어 준다.
*/

public interface PostRepository {
    
    // 메서드의 풀네임
    // com.itwill.spring2.repository.PostRepository.insert 이다.(앞에 접두사가 붙어있다.)
    //          패키지             /     클래스   / 메서드
    int insert(Post post);
    
    List<Post> selectOrderByIdDesc();
    
    Post selectById(long id);
    
    int updateTilteAndContent(Post post);
    
    int deleteById(long id);
    
}
