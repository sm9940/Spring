package com.board.repository;

import com.board.constant.Category;
import com.board.dto.BoardSearchDto;
import com.board.entity.Board;
import com.board.entity.QBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{
private JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime= LocalDateTime.now();

        if(StringUtils.equals("all",searchDateType)|| searchDateType== null){
            return null;
        } else if (StringUtils.equals("1d",searchDateType)) {
           dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w",searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m",searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m",searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }
        return QBoard.board.regTime.after(dateTime);
    }
    private BooleanExpression searchCategoryEq(Category searchCategory){
        return searchCategory == null ? null: QBoard.board.category.eq(searchCategory);
    }
    private BooleanExpression searchByLike(String searchBy,String searchQuery){
        if(StringUtils.equals("title",searchBy)){
            return QBoard.board.title.like("%"+searchQuery+"%");
        } else if(StringUtils.equals("content",searchBy)){
            return QBoard.board.content.like("%"+searchQuery+"%");
        }else if(StringUtils.equals("createdBy",searchBy)){
            return QBoard.board.createdBy.like("%"+searchQuery+"%");
        }
        return null;
    }
    @Override
    public Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable) {
        List<Board> content =queryFactory.selectFrom(QBoard.board)
                .where(regDtsAfter(boardSearchDto.getSearchDateType()))
                .where(searchCategoryEq(boardSearchDto.getSearchCategory()))
                .where(searchByLike(boardSearchDto.getSearchBy(), boardSearchDto.getSearchQuery()))
                .orderBy(QBoard.board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = queryFactory
                .select(Wildcard.count).from(QBoard.board)
                .where(regDtsAfter(boardSearchDto.getSearchDateType()))
                .where(searchCategoryEq(boardSearchDto.getSearchCategory()))
                .where(searchByLike(boardSearchDto.getSearchBy(), boardSearchDto.getSearchQuery()))
                .fetchOne();
        return new PageImpl<>(content,pageable,total);
    }
}
