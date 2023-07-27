package project.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.jpa.dto.BoardFindDTO;
import project.jpa.entity.Board;

public interface BoardQueryRepository {
    Page<Board> findBoardByTitle(Pageable pageable, BoardFindDTO boardFindDTO);
}
