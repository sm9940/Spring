package com.board.dto;

import com.board.constant.Category;
import com.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;



@Getter
@Setter
public class BoardFormDto {
    private Long id;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private Category category;

    private static ModelMapper modelMapper=new ModelMapper();
    public Board insertPost(){return modelMapper.map(this,Board.class);}
    public static BoardFormDto of(Board board){
        return modelMapper.map(board,BoardFormDto.class);
    }
}
