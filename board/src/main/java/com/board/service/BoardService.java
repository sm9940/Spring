package com.board.service;

import com.board.dto.BoardFormDto;
import com.board.dto.BoardImgDto;
import com.board.dto.BoardSearchDto;
import com.board.entity.Board;
import com.board.entity.BoardImg;
import com.board.repository.BoardImgRepository;
import com.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardImgService boardImgService;
    private final BoardImgRepository boardImgRepository;

    public Long savePost(BoardFormDto boardFormDto, List<MultipartFile>boardImgFileList) throws Exception{
        Board board= boardFormDto.insertPost();
        boardRepository.save(board);

        for (int i = 0; i<boardImgFileList.size(); i++){
            BoardImg boardImg = new BoardImg();
            boardImg.setBoard(board); //★itemImg가 item을 참조하므로 insert하기전 반드시 item 객체를 넣어준다.

            //첫번째 이미지 일때 대표 이미지 지정

            boardImgService.saveBoardImg(boardImg,boardImgFileList.get(i));
        }

        return board.getId();
    }

    public Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable){
        Page<Board> boardPage = boardRepository.getBoardPage(boardSearchDto,pageable);
        return boardPage;
    }
    public BoardFormDto getBoardView(Long boardId){
        List<BoardImg> boardImgList = boardImgRepository.findByBoardIdOrderByIdAsc(boardId);

        List<BoardImgDto> boardImgDtoList =new ArrayList<>();
        for(BoardImg boardImg: boardImgList){
            BoardImgDto boardImgDto= BoardImgDto.of(boardImg);
            boardImgDtoList.add(boardImgDto);
        }
        Board board = boardRepository.findById(boardId).orElseThrow(EntityNotFoundException::new);
        BoardFormDto boardFormDto = BoardFormDto.of(board);
        boardFormDto.setBoardImgDtoList(boardImgDtoList);
        return boardFormDto;
    }

    public Long updateBoard(BoardFormDto boardFormDto,List<MultipartFile> boardImgFileList) throws Exception{
        Board board = boardRepository.findById(boardFormDto.getId()).orElseThrow(EntityNotFoundException::new);

        board.updateBoard(boardFormDto);

        List<Long> boardImgIds= boardFormDto.getBoardImgIds();

        for (int i = 0; i <boardImgFileList.size() ; i++) {
            boardImgService.updateBoardImg(boardImgIds.get(i),boardImgFileList.get(i));
        }
        return board.getId();
    }
}
