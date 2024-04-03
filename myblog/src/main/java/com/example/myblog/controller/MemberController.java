package com.example.myblog.controller;

import com.example.myblog.dto.Member;
import com.example.myblog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @GetMapping(value = "/login")
    public String login(){
            return "member/login";
    }
@PostMapping(value="/login")
    public String loginMember(Member member) {
    //   １.사용자가 입력한 로그인 데이터와 DB에 저장된 데이터가 맞는지 비교
    try {
        Member loginMember = memberService.loginMember(member);
        //　2.  데이터가 일치하면(로그인 성공시) index페이지로 이동
        if(loginMember != null){
            return "redirect:/";
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

        return "member/login"; //3. 로그인 실패시 login 페이지로 이동
    }
}
