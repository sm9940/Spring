package com.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="board_img")
@Getter
@Setter
@ToString
public class BoardImg extends BaseEntity{
    @Id
    @Column(name = "board_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void updateBoardImg(String oriImgName,String imgName,String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
