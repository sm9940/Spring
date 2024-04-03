package com.example.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping(value = "/")
    public String index(Model model){
            return "index";
    }

    @GetMapping(value = "/view")
    public String view(){
        return "post/view";
    }
    @GetMapping(value = "/list")
    public String list(){
        return "post/list";
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
