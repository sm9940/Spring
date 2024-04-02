package com.example.springbasic.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Item { //상품
    private Long id;
    private String itemNm;
    private  Integer price;
    private String itemDetail;
    private String sellStateCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
