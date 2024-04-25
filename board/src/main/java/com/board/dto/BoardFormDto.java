package com.board.dto;

import com.board.constant.Category;
import com.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BoardFormDto {
    private Long id;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private Category category;
    private LocalDateTime regTime;
    private List<BoardImgDto> boardImgDtoList = new ArrayList<>();

    //상품 이미지 아이들을 저장 -> 수정시 이미지 아이들을 담아둘 용도
    private List<Long> BoardImgIds = new ArrayList<>();
    private static ModelMapper modelMapper=new ModelMapper();
    public Board insertPost(){return modelMapper.map(this,Board.class);}
    public static BoardFormDto of(Board board){
        return modelMapper.map(board,BoardFormDto.class);
    }
}
