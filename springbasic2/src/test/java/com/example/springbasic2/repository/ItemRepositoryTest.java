package com.example.springbasic2.repository;

import com.example.springbasic2.constant.ItemSellStatus;
import com.example.springbasic2.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest //테스트용 클래스
@TestPropertySource(locations = "classpath:application-test.properties")
public class ItemRepositoryTest  {
    @Autowired
    ItemRepository itemRepository;

    //item 테이블에 insert
    @Test //테스트용 junit 메소드
    @DisplayName("상품 저장 테스트") //테스트 코드 실행시 테스트명을 지정해준다
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        itemRepository.save(item);
        //save는 insert한 엔티티 객체를 그대로 return해준다.
        Item savedItem = itemRepository.save(item); //insert
        System.out.println("insert한 엔티티 객체: "+savedItem);
    }
}
