package project.jpa.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import project.jpa.entity.QBoard;

public class BoardBoolean {
    // 흔적...
//    public static Predicate defaultWhere(BoardFindDTO boardFindDTO) {
//        BooleanBuilder builder = new BooleanBuilder();
//        if (boardFindDTO.getTitle() != null) {
//            builder.and(board.title.contains(boardFindDTO.getTitle()));
//        }
//        if (boardFindDTO.getContent() != null) {
//            builder.and(board.content.contains(boardFindDTO.getContent()));
//        }
//        if (boardFindDTO.getCreatedBy() != null) {
//            builder.and(board.createdBy.contains(boardFindDTO.getCreatedBy()));
//        }
//        return builder;
//    }

    public static <T extends QBoard> BooleanExpression titleContains(String title, T board) {
        return title != null ? board.title.contains(title) : null;
    }

    public static <T extends QBoard> BooleanExpression contentContains(String content, T board) {
        return content != null ? board.content.contains(content) : null;
    }

    public static <T extends QBoard> BooleanExpression createdByContains(String createdBy, T board) {
        return createdBy != null ? board.createdBy.contains(createdBy) : null;
    }
}
