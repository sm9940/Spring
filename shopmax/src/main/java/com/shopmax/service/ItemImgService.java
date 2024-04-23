package com.shopmax.service;

import com.shopmax.entity.ItemImg;
import com.shopmax.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class ItemImgService {
    @Value("${itemImgLocation}") // C:/shop/item
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    //이미지 저장
    //1. 파일을 itemImgLocation에 저장(서버에 저장) -> FileService를 이용
    //2. item_img 테이블에 이미지 정보 insert

    public  void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename(); //파일 이름 -> 이미지1.jpg
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            //oriImgName 빈문자열이 아니라면 이미지 파일 업로드 진행
            imgName = fileService.uploadFile(itemImgLocation,
                    oriImgName,itemImgFile.getBytes());
            //itemImgfile.getBytes() : 이미지 파일을 byte배열로 만들어준다.

            imgUrl = "/images/item/"+imgName; // /images/item/ss.jpg
        }
        //DB에 insert를 하기 전 유저가 직접 입력하지 못한 값들은 개발자가 넣어준다.
        itemImg.updateItemImg(oriImgName,imgName,imgUrl);
        itemImgRepository.save(itemImg); //insert
    }

}
