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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;
    @GetMapping(value = "/post/list")
    public String list(Model model){
        return "/post/list";
    }
    @GetMapping(value = "/post/view")
    public String view(){
        return "post/view";
    }
    @GetMapping(value = "/post/write")
    public String write(Model model){
        model.addAttribute("boardFormDto",new BoardFormDto());

        return "post/write";
    }
    @PostMapping(value = "/insert")
    public String write(@Valid BoardFormDto boardFormDto, BindingResult bindingResult,
                        @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList, Model model){
        if(bindingResult.hasErrors()) return "post/write";
        if(boardImgFileList.get(0).isEmpty()){
            model.addAttribute("errorMessage","첫번째 상품 이미지를 입력해주세요");
            return "post/write";
        }
        try {
            boardService.savePost(boardFormDto,boardImgFileList);
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
