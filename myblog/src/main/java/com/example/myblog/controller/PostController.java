package com.example.myblog.controller;

import com.example.myblog.dto.Post;
import com.example.myblog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    PostService postService;


    @GetMapping(value = "/")
    public String index(Model model){
            return "index";
    }

    @GetMapping(value = "/view")
    public String view(){
        return "post/view";
    }
    @RequestMapping(value = "/list",method = {
            RequestMethod.GET,RequestMethod.POST})
    public String list(HttpSession session, HttpServletRequest request) {

        try {
            Map map = null;
            int memberId = (int) session.getAttribute("member_id");
            String searchKey = request.getParameter("searchKey");
            String searchValue = request.getParameter("searchValue");

            if (searchValue == null) {
                //검색어가 없다면
                searchKey = "subject"; //검색 키워드의 디폴트는 subject
                searchValue = ""; //검색어의 디폴트는 빈문자열
            }

            map = new HashMap();
            map.put("memberId", memberId);
            map.put("searchKey", searchKey);
            map.put("searchValue", searchValue);
            map.put("start", 1);
            map.put("end", 5);
            List<Post> lists = postService.getPostList(map);

            System.out.println(lists);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
