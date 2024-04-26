package com.board.dto;

import com.board.entity.Board;
import com.board.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private Member member;
    private Board board;
}
