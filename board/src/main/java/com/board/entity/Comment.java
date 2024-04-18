package com.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @Column
    @GeneratedValue
    private Long id;
    private String title;
    @Lob

    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
