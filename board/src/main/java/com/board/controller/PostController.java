package com.board.controller;

import com.board.dto.BoardFormDto;
import com.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;
    @GetMapping(value = "/post/list")
    public String list(){
        return "/post/list";
    }
    @GetMapping(value = "/post/view")
    public String view(){
        return "post/view";
    }
    @GetMapping(value = "/post/write")
    public String write(Model model){
        model.addAttribute("BoardFormDto",new BoardFormDto());

        return "post/write";
    }
    @PostMapping(value = "/insert")
    public String write(@Valid BoardFormDto boardFormDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) return "post/write";
        try {
            boardService.savePost(boardFormDto);
        } catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errorMessage","게시물 등록중 에러가 발생했습니다.");
            return "post/write";
        }
        return "redirect:/post/list";
    }
    @GetMapping(value = "/post/rewrite")
    public String rewrite(){
        return "post/rewrite";
    }
}
