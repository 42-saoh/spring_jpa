package project.jpa.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.jpa.dto.BoardFindDTO;
import project.jpa.entity.Board;

import java.util.List;

import static project.jpa.entity.QBoard.*;

@Repository
public class BoardQueryRepository extends QueryDSLRepository {

    public BoardQueryRepository() {
        super(Board.class);
    }

    public Page<Board> findBoardByTitle(BoardFindDTO boardFindDTO, Pageable pageable) {
        return applyPagination(pageable,
                query -> query.selectFrom(board)
                        .where(titleContains(boardFindDTO.getTitle()),
                                contentContains(boardFindDTO.getContent()),
                                createByContains(boardFindDTO.getCreatedBy())));
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? board.title.contains(title) : null;
    }

    private BooleanExpression contentContains(String content) {
        return content != null ? board.content.contains(content) : null;
    }

    private BooleanExpression createByContains(String createBy) {
        return createBy != null ? board.createdBy.contains(createBy) : null;
    }
}
