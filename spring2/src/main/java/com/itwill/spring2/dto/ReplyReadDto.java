package com.itwill.spring2.dto;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReplyReadDto {
    
    private long id;
    private long postId;
    private String replyText;
    private String writer;
    private Timestamp modifiedTime;
    
    // 변수가 만들어지기 전에 메서드를 호출해서 dto를 만들기 때문에 static으로 선언한다.
    // DB에서 selcet한 Reply 타입 객체를 ReplyReadDto 타입 객체로 변환해서 리턴.
    public static ReplyReadDto fromEntity(Reply entity) {
        return ReplyReadDto.builder()
                .id(entity.getId())
                .postId(entity.getPost_id())
                .replyText(entity.getReply_text())
                .writer(entity.getWriter())
                .modifiedTime(Timestamp.valueOf(entity.getModified_time()))
                .build();
        }
    
    
}
