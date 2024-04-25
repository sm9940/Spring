package com.board.controller;

import com.board.dto.BoardFormDto;
import com.board.dto.BoardSearchDto;
import com.board.entity.Board;
import com.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;

    @GetMapping(value = {"/post/list","/post/list/{page}"})
    public String list(BoardSearchDto boardSearchDto, Model model, @PathVariable("page")Optional<Integer> page){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0,5);
        Page<Board> boards =boardService.getBoardPage(boardSearchDto,pageable);
        model.addAttribute("boards",boards);
        model.addAttribute("boardSearchDto",boardSearchDto );
        model.addAttribute("maxPage",5);
        return "/post/list";
    }
    @GetMapping(value = "/post/view/{boardId}")
    public String viewDetail(Model model,@PathVariable("boardId")Long boardId)
    { BoardFormDto boardFormDto=boardService.getBoardView(boardId);
        model.addAttribute("board",boardFormDto);
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
