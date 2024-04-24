package com.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board_img")
@Getter
@Setter
@ToString
public class BoardImg extends BaseEntity{
}
