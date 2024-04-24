package com.board.service;


import com.board.entity.BoardImg;
import com.board.repository.BoardImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class BoardImgService {
    @Value("${boardImgLocation}")
    private String boardImgLocation;

    private final BoardImgRepository boardImgRepository;
    private final FileService fileService;

    public void saveBoardImg(BoardImg boardImg, MultipartFile boardImgFile) throws Exception{
        String oriImgName= boardImgFile.getOriginalFilename();
        String imgName= "";
        String imgUrl= "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(boardImgLocation,oriImgName,boardImgFile.getBytes());

            imgUrl="/images/board/"+imgName;
        }

        boardImg.updateBoardImg(oriImgName,imgName,imgUrl);
        boardImgRepository.save(boardImg);
    }


}
