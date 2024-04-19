package com.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    @GetMapping(value = "/list")
    public String list(){
        return "/post/list";
    }
    @GetMapping(value = "/view")
    public String view(){
        return "post/view";
    }
    @GetMapping(value = "/write")
    public String write(){
        return "post/write";
    }
    @GetMapping(value = "/rewrite")
    public String rewrite(){
        return "post/rewrite";
    }
}
