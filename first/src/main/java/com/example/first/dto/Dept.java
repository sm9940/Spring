package com.example.first.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dept {
    private String deptno; //부서번호
    private String dept; //부서이름
    private String loc; //지역
}
