package com.board.controller;

import com.board.dto.MemberFormDto;
import com.board.entity.Member;
import com.board.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    @GetMapping(value = "/")
    public String main(){
        return "index";
    }
    @GetMapping(value = "/members/login")
    public String login(){
        return "/member/loginMember";
    }
    @GetMapping(value = "/members/new")
    public String regist(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "/member/memberForm";
    }
    @PostMapping(value = "/members/new")
    public String regist(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) return "member/memberForm";

        try {
            Member member = Member.createMember(memberFormDto,passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }
    @GetMapping(value = "/members/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","이메일 또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }
}
