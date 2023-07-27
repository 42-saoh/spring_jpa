package project.jpa.utils;

import org.springframework.stereotype.Component;
import project.jpa.dto.BoardCreateDTO;
import project.jpa.entity.Board;
import project.jpa.entity.FreeBoard;
import project.jpa.entity.IdolBoard;
import project.jpa.entity.enums.FreeTap;
import project.jpa.entity.enums.IdolTap;

@Component
public class BoardFactory {
    private Board makeFreeBoard(BoardCreateDTO boardCreateDTO) {
        return FreeBoard.builder()
                .title(boardCreateDTO.getTitle())
                .content(boardCreateDTO.getContent())
                .freeTap(FreeTap.valueOf(boardCreateDTO.getTap()))
                .build();
    }

    private Board makeIdolBoard(BoardCreateDTO boardCreateDTO) {
        return IdolBoard.builder()
                .title(boardCreateDTO.getTitle())
                .content(boardCreateDTO.getContent())
                .idolTap(IdolTap.valueOf(boardCreateDTO.getTap()))
                .build();
    }

    public Board createBoard(BoardCreateDTO boardCreateDTO) {
        switch (boardCreateDTO.getBoardType()) {
            case FREE:
                return makeFreeBoard(boardCreateDTO);
            case IDOL:
                return makeIdolBoard(boardCreateDTO);
        }
        return null;
    }
}
