package com.board.service;

import com.board.dto.BoardFormDto;
import com.board.entity.Board;
import com.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long savePost(BoardFormDto boardFormDto) throws Exception{
        Board board= boardFormDto.insertPost();
        boardRepository.save(board);
        return board.getId();
    }
}
