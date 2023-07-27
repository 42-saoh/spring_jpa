package project.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.jpa.dto.BoardFindDTO;
import project.jpa.entity.Board;
import project.jpa.entity.IdolBoard;
import project.jpa.entity.enums.IdolTap;

import static project.jpa.entity.QIdolBoard.*;
import static project.jpa.utils.BoardBoolean.*;

@Repository
public class IdolBoardQueryRepository extends QueryDSLRepository implements BoardQueryRepository {

    public IdolBoardQueryRepository() {
        super(IdolBoard.class);
    }

    // 마찬가지의 흔적
//    @Override
//    public Page<Board> findBoardByTitle(Pageable pageable,
//                                        Function<JPAQueryFactory, JPAQuery> contentQuery) {
//        return applyPagination(pageable, contentQuery);
//    }

    @Override
    public Page<Board> findBoardByTitle(Pageable pageable,
                                        BoardFindDTO boardFindDTO) {
        return applyPagination(pageable,
                query -> query.selectFrom(idolBoard)
                        .where(titleContains(boardFindDTO.getTitle(), idolBoard._super),
                                contentContains(boardFindDTO.getContent(), idolBoard._super),
                                createdByContains(boardFindDTO.getCreatedBy(), idolBoard._super),
                                tapEq(boardFindDTO.getTap())));
    }

    private BooleanExpression tapEq(String tap) {
        return tap != null ? idolBoard.idolTap.eq(IdolTap.valueOf(tap)) : null;
    }
}
