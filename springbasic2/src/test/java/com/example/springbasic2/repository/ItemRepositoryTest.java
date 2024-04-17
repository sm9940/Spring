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
import java.util.List;

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

    // 데이터 10개 저장
    public void createItemList(){
        for (int i = 1; i <=10 ; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품"+i);
            item.setPrice(i*10000);
            item.setItemDetail("테스트 상품 상세 설명"+i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());

            itemRepository.save(item);
            //save는 insert한 엔티티 객체를 그대로 return해준다.
        }
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNmTest(){
        //데이터 10개 insert
//        createItemList();
        //select * from item where item_nm = '테스트 상품3'
        //find  + (엔티티이름) + By + 필드 이름
//       List<Item> itemList = itemRepository.findByItemNm("테스트 상품3");

        //select * from item
        List<Item> itemList = itemRepository.findAll();
       for (Item item: itemList){
           System.out.println(item);
       }
    }
    @Test
    @DisplayName("퀴즈1-1")
    public void quiz1_1Test(){
        List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1",ItemSellStatus.SELL);
        for (Item item:itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈1-2")
    public void quiz1_2Test(){
        List<Item> itemList = itemRepository.findByPriceBetween(10000,40000);
        for(Item item:itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈1-3")
public void quiz1_3Test(){
        List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023,1,1,12,12,44));
        for (Item item:itemList){
            System.out.println(item);
        }
}
@Test
@DisplayName("퀴즈1-4")
public void quiz1_4Test(){
        List<Item> itemList = itemRepository. findByItemSellStatusNotNull();
        for (Item item: itemList){
            System.out.println(item);
        }
}
@Test
@DisplayName("퀴즈1-5")
public void quiz1_5Test(){
       List<Item> itemList = itemRepository.findByItemDetailEndingWith("설명1");
       for (Item item: itemList){
           System.out.println(item);
       }
}
@Test
@DisplayName("퀴즈1-6")
public void quiz1_6Test(){
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명5");
    for (Item item: itemList){
        System.out.println(item);
    }
}
@Test
@DisplayName("퀴즈1-7")
public void quiz1_7Test(){
        List<Item> itemList= itemRepository.findByPriceLessThanOrderByPriceDesc(50000);
    for (Item item: itemList){
        System.out.println(item);
    }
}
}
