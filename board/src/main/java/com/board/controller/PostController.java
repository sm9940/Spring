package com.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    @GetMapping(value = "/post/list")
    public String list(){
        return "/post/list";
    }
    @GetMapping(value = "/post/view")
    public String view(){
        return "post/view";
    }
    @GetMapping(value = "/post/write")
    public String write(){
        return "post/write";
    }
    @GetMapping(value = "/post/rewrite")
    public String rewrite(){
        return "post/rewrite";
    }
}
