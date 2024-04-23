package com.shopmax.service;

import com.shopmax.dto.ItemFormDto;
import com.shopmax.entity.Item;
import com.shopmax.entity.ItemImg;
import com.shopmax.repository.ItemImgRepository;
import com.shopmax.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class ItemService {
    private  final ItemRepository itemRepository;
    private  final ItemImgRepository itemImgRepository;
    private  final ItemImgService itemImgService;

    //item 테이블에 상품 등록(insert)
    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //1. 상품 등록(insert)
        Item item = itemFormDto.createItem(); //dto -> entity
        itemRepository.save(item);
        //2. 이미지 등록
        for (int i = 0; i<itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item); //★itemImg가 item을 참조하므로 insert하기전 반드시 item 객체를 넣어준다.

            //첫번째 이미지 일때 대표 이미지 지정
            if(i == 0){
                itemImg.setRepImgYn("Y");
            } else {
                itemImg.setRepImgYn("N");
            }
            itemImgService.saveItemImg(itemImg,itemImgFileList.get(i));
        }
        return item.getId(); //등록한 상품의 아이디를 리턴
    }
}
