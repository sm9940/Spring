package com.example.springbasic2.entity;

import com.example.springbasic2.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Orders {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(nullable = false,columnDefinition = "bigint")
    private long memberId;
}
