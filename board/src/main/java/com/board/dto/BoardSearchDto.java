package com.board.dto;

import com.board.constant.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDto {
    private String searchDateType;
    private Category searchCategory;
    private String searchBy;
    private String searchQuery = "";
}
