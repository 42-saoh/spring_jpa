package project.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.dto.BoardCreateDTO;
import project.jpa.dto.BoardFindDTO;
import project.jpa.dto.CommentCreateDTO;
import project.jpa.entity.Board;
import project.jpa.entity.Comment;
import project.jpa.repository.BoardJpaRepository;
import project.jpa.repository.BoardQueryRepository;

import java.util.List;

@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryRepository boardQueryRepository;

    public BoardService(BoardJpaRepository boardJpaRepository, BoardQueryRepository boardQueryRepository) {
        this.boardJpaRepository = boardJpaRepository;
        this.boardQueryRepository = boardQueryRepository;
    }

    public void save(BoardCreateDTO boardCreateDTO) {
        boardJpaRepository.save(Board.builder()
                .title(boardCreateDTO.getTitle())
                .content(boardCreateDTO.getContent())
                .build());
    }

    public Page<Board> findBoardByTitle(BoardFindDTO boardFindDTO, Pageable pageable) {
        return boardQueryRepository.findBoardByTitle(boardFindDTO, pageable);
    }

    @Transactional
    public void addComment(Long id, CommentCreateDTO commentCreateDTO) {
        Board board = boardJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.addComment(Comment.builder()
                .content(commentCreateDTO.getContent())
                .build());
    }
}
