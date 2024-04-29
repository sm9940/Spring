package com.board.entity;

import com.board.constant.Category;
import com.board.dto.BoardFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="board")
@Getter
@Setter
@ToString
public class Board extends BaseEntity {
    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    @Column(columnDefinition = "longtext")
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardImg> boardImages;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id asc")
    private List<Comment> comments;
    public  void updateBoard(BoardFormDto itemFormDto){
        this.title = itemFormDto.getTitle();
        this.content =itemFormDto.getContent();
        this.category=itemFormDto.getCategory();
    }

}