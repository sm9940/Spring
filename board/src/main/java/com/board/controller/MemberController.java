package com.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @GetMapping(value = "/")
    public String main(){
        return "index";
    }
    @GetMapping(value = "/members/login")
    public String login(){
        return "/member/loginMember";
    }
    @GetMapping(value = "/members/new")
    public String regist(){
        return "/member/memberForm";
    }
}
