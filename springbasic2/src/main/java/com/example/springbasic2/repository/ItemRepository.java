package com.example.springbasic2.repository;

import com.example.springbasic2.constant.ItemSellStatus;
import com.example.springbasic2.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

//JPA에서는  Repository클래스가 Model(데이터베이스와 대화) 역할을 한다.
//Repository클래스는 JpaRepository<사용할 엔티티 클래스, 해당 엔티티의 PK타입> 인터페이스를 반드시 상속받아야된다.
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemNm(String itemNm);

    //select * from item where item_nm = ? and item_sell_status = ?
    List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);

    //매개변수 이름은 엔티티 클래스의 필드명과 꼭 똑같이 작성하지 않아도 된다.
    //JPA에서는 매개변수 순서대로 쿼리 물음표에 값을 바인딩 한다.
    //select * from item where price between ? and ?
    List<Item> findByPriceBetween(int price1, int price2);
    List<Item> findByRegTimeAfter(LocalDateTime regTime);
    List<Item> findByItemSellStatusNotNull();
    List<Item> findByItemDetailEndingWith(String itemDeatil);
    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);
}
