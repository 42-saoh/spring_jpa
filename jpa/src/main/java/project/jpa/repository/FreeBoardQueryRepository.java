package project.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.jpa.dto.BoardFindDTO;
import project.jpa.entity.Board;
import project.jpa.entity.FreeBoard;
import project.jpa.entity.enums.FreeTap;


import static project.jpa.entity.QFreeBoard.*;
import static project.jpa.utils.BoardBoolean.*;

@Repository
public class FreeBoardQueryRepository extends QueryDSLRepository implements BoardQueryRepository {

    public FreeBoardQueryRepository() {
        super(FreeBoard.class);
    }

//    @Override
//    public Page<Board> findBoardByTitle(Pageable pageable,
//                                        Function<JPAQueryFactory, JPAQuery> contentQuery) {
//        return applyPagination(pageable, contentQuery);
//    }

    @Override
    public Page<Board> findBoardByTitle(Pageable pageable,
                                        BoardFindDTO boardFindDTO) {
        return applyPagination(pageable,
                query -> query.selectFrom(freeBoard)
                        .where(titleContains(boardFindDTO.getTitle(), freeBoard._super),
                                contentContains(boardFindDTO.getContent(), freeBoard._super),
                                createdByContains(boardFindDTO.getCreatedBy(), freeBoard._super),
                                tapEq(boardFindDTO.getTap())));
    }

    private BooleanExpression tapEq(String tap) {
        return tap != null ? freeBoard.freeTap.eq(FreeTap.valueOf(tap)) : null;
    }
}
