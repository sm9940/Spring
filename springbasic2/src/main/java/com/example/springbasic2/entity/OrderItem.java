package com.example.springbasic2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false)
    private int orderPrice;
    @Column(nullable = false,columnDefinition = "bigint")
    private long itemId;
    @Column(nullable = false)
    private int count;
    @Column(nullable = false,columnDefinition = "bigint")
    private long orderId;
}
