package com.shopmax.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //문의하기
    @GetMapping(value = "/members/qa")
    public String qa(){
        return "member/qa";
    }
    //로그인 화면
    @GetMapping(value = "/members/login")
    public String loginMember (){ return "member/memberLoginForm";}

    @GetMapping(value = "/members/new")
    public String memberForm(){
        return "member/memberForm";
    }

    //로그인 실패시
    @GetMapping(value = "/members/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm"; //로그인 페이지로 그대로 이동
    }
}
