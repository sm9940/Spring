package com.example.springbasic2.repository;

import com.example.springbasic2.constant.ItemSellStatus;
import com.example.springbasic2.entity.Item;
import com.example.springbasic2.entity.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Test
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
}@Test
@DisplayName("JPQL @Query를 이용한 상품 조회 테스트")
public void findByItemDetail(){
        List<Item> itemList= itemRepository.findByItemDetail("테스트 상품 상세");
    for (Item item: itemList){
        System.out.println(item);
    }
}
@Test
@DisplayName("퀴즈2-1")
public void quiz2_1Test(){
List<Item> itemList = itemRepository.getPrice(50000);
    for (Item item: itemList){
        System.out.println(item);
    }
}
    @Test
    @DisplayName("퀴즈2-2")
    public void quiz2_2Test(){
        List<Item> itemList = itemRepository.findItemNmAndItemSellStatus("테스트 상품1",ItemSellStatus.SELL);
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    public void quiz2_3Test(){
        List<Item> itemList = itemRepository.findById(7L);
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("native query")
    public void  findByItemDetailByNative(){
        List<Item> itemList = itemRepository. findByItemDetailByNative("테스트 상품 상세");
        for (Item item: itemList){
            System.out.println(item);
        }
    }

    @PersistenceContext
    EntityManager em; //엔티티 매니저 객체(쿼리문 실행)
    @Test
    @DisplayName("querydsl 조회 테스트")
    public void queryDslTest(){
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QItem qItem= QItem.item; //Item 엔티티 객체
        // select * from item where item_seel_status= 'SELL' and
        // item_detail like '%테스트 상품 상세%' order by price desc;

        //쿼리문 실행했을때 결과값을 담을 엔티티 타입을 제네릭에 선언
        JPAQuery<Item> query = qf.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%테스트 상품 상세%"))
                .orderBy(qItem.price.desc());
        List<Item> itemList = query.fetch();
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈 3-1")
    public void quiz3_1Test(){
        JPAQueryFactory qf =new JPAQueryFactory(em);
        QItem qItem =QItem.item;

        JPAQuery<Item> query = qf.selectFrom(qItem)
                .where(qItem.itemNm.eq("테스트 상품1"))
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
        List<Item> itemList = query.fetch();
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈 3-2")
    public void quiz3_2Test(){
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QItem qItem = QItem.item;

        JPAQuery<Item> query =qf.selectFrom(qItem)
                .where(qItem.price.between(10000,80000));
        List<Item> itemList =query.fetch();
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈 3-3")
    public void quiz3_3Test(){
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QItem qItem =QItem.item;

        JPAQuery<Item> query = qf.selectFrom(qItem)
                .where(qItem.regTime.after(LocalDateTime.of(2023,1,1,12,12,44)));
        List<Item> itemList = query.fetch();
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈 3-4")
    public void quiz3_4Test(){
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QItem qItem =QItem.item;

        JPAQuery<Item> query = qf.selectFrom(qItem)
                .where(qItem.itemSellStatus.isNotNull());
        List<Item> itemList =query.fetch();
        for(Item item:itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("퀴즈 3-5")
    public void quiz3_5Test(){
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QItem qItem = QItem.item;

        JPAQuery<Item> query =qf.selectFrom(qItem)
                .where(qItem.itemDetail.endsWith("설명1"));
        List<Item> itemList = query.fetch();
        for (Item item: itemList){
            System.out.println(item);
        }
    }
    @Test
    @DisplayName("querydsl 조회 테스트2")
    public void queryDslTest2(){
        JPAQueryFactory qf = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        /*
        select * from Item
        where item_sell_status = 'SELL'
        and item_detail like '%테스트 상품 상세%'
        and price > 10003;
         */

        Pageable page = PageRequest.of(0,4);
        //of(시작페이지 번호(0부터 시작), 한페이지당 조회할 레코드의 갯수)
        JPAQuery<Item> query =qf.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%테스트 상품 상세%"))
                .where(qItem.price.gt(30000))
                .offset(page.getOffset())
                .limit(page.getPageSize());

        List<Item> itemList = query.fetch();

        for (Item item: itemList){
            System.out.println(item);
        }
    }
}
