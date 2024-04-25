package com.board.dto;

import com.board.entity.BoardImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class BoardImgDto {
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;

    private static ModelMapper modelMapper= new ModelMapper();

    public static BoardImgDto of(BoardImg boardImg){
        return modelMapper.map(boardImg,BoardImgDto.class);
    }
}
