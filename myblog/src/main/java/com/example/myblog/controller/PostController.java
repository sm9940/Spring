package com.example.myblog.controller;

import com.example.myblog.dto.Post;
import com.example.myblog.service.PostService;
import com.example.myblog.util.PagingUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    PagingUtil pagingUtil;

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
    public String list(HttpSession session, HttpServletRequest request,Model model) {
        try {

            int memberId = (int) session.getAttribute("member_id");
            String searchKey = request.getParameter("searchKey");
            String searchValue = request.getParameter("searchValue");
            String pageNum= request.getParameter("pageNum");

            pagingUtil.setCurrentPage(1);
            if(pageNum != null){
                //현재 페이지의 값을 바꿔준다.
                pagingUtil.setCurrentPage(Integer.parseInt(pageNum));
            }
            if (searchValue == null) {
                //검색어가 없다면
                searchKey = "subject"; //검색 키워드의 디폴트는 subject
                searchValue = ""; //검색어의 디폴트는 빈문자열
            }



            Map map = new HashMap();
            map.put("memberId", memberId);
            map.put("searchKey", searchKey);
            map.put("searchValue", searchValue);

            // 1. 전체 게시물의 갯수를 가져온다(페이징 처리시 필요)
            int dataCount = postService.getDataCount(map);

            //2. 페이징 처리를 한다(준비단계)
            // numPerPage : 페이지당 보여줄 게시물 목록의 갯수
            pagingUtil.resetPaging(dataCount,5);
            map.put("start", pagingUtil.getStart());
            map.put("end", pagingUtil.getEnd());

            //3.페이징 처리할 리스트를 가지고 온다.
            List<Post> lists = postService.getPostList(map);
            //4. 검색어에 대한 쿼리스트링을 만든다.
            String param = "";
            String listUrl = "/list";
            String articleUrl = "/view?pageNum="+pagingUtil.getCurrentPage();
            if(searchValue != null && !searchValue.equals("")){
                param = "searchKey=" +searchKey;
                param += "&searchValue=" + URLEncoder.encode(searchValue,"UTF-8");
            }
            //검색어가 있다면
            if(!param.equals("")){
                //listUrl의 값: /list?searchKey=subject&searchValue=네번째
                listUrl += "?" +param;
                //articleUrl의 값: /view?pageNum=1&searchKey=subject&searchValue=네번째
                articleUrl += "&"+param;
            }

            //5. 페이징 버튼을 만들어준다.
            String pageIndexList = pagingUtil.pageIndexList(listUrl);

            model.addAttribute("lists", lists); //DB에서 가져온 전체 게시물리스트
            model.addAttribute("articleUrl",articleUrl); //상세페이지로 이동하기 위한 url
            model.addAttribute("pageIndexList",pageIndexList); //페이징 버튼
            model.addAttribute("dataCount",dataCount); // 게시물의 전체 갯수
            model.addAttribute("searchKey",searchKey); //검색키워드
            model.addAttribute("searchValue",searchValue); //검색어


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
