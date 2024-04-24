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
    @Value("${BoardImgLocation}")
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
    public void updateItemImg(Long boardImgId, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){ //첨부한 이미지 파일이 있으면
            //1. 서버에 있는 이미지를 가지고 와서 수정해준다.
            BoardImg saveItemImg = boardImgRepository.findById(boardImgId).orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일을 c:/shop/item 폴더에서 삭제
            if(!StringUtils.isEmpty(saveItemImg.getImgName())){
                fileService.deleteFile(boardImgLocation + "/"+saveItemImg.getImgName());
            }

            //수정된 이미지 파일을 경로에 업로드
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(boardImgLocation,oriImgName,itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;

            //2. item_img 테이블에 저장된 데이터를 수정해준다.
            //update (JPA가 자동감지)
            saveItemImg.updateBoardImg(oriImgName,imgName,imgUrl);

        }
    }
}
