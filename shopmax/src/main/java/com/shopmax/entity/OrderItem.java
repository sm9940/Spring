package com.shopmax.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_item") //db에서 order by 예약어를 사용하므로 orders라고 지정
@Getter
@Setter
@ToString
public class OrderItem extends BaseEntity{
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orderPrice; //주문가격

    private  int count; //주문 수량

    @ManyToOne
    @JoinColumn(name ="item_id")
    private  Item item; //OrderItem이 Item 을 참조한다

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}
