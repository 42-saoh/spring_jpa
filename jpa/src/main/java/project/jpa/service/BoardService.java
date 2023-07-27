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
import project.jpa.repository.*;
import project.jpa.utils.BoardFactory;

@Service
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    private final FreeBoardQueryRepository freeboardQueryRepository;
    private final IdolBoardQueryRepository idolboardQueryRepository;
    private final BoardFactory boardFactory;

    public BoardService(BoardJpaRepository boardJpaRepository,
                        FreeBoardQueryRepository freeboardQueryRepository,
                        IdolBoardQueryRepository idolboardQueryRepository,
                        BoardFactory boardFactory) {
        this.boardJpaRepository = boardJpaRepository;
        this.freeboardQueryRepository = freeboardQueryRepository;
        this.idolboardQueryRepository = idolboardQueryRepository;
        this.boardFactory = boardFactory;
    }

    public void save(BoardCreateDTO boardCreateDTO) {
        Board board = boardFactory.createBoard(boardCreateDTO);
        if (board == null) throw new IllegalArgumentException("잘못된 게시판입니다.");
        boardJpaRepository.save(board);
    }

    // 억지로 하나로 합친 코드이다. 이렇게 하면 안된다. 분리해야 한다. 그게 맞다. 그게 유지보수가 된다.
//    public Page<Board> findBoardByTitle(BoardFindDTO boardFindDTO, Pageable pageable) {
//        BoardQueryRepository boardQueryRepository = null;
//        switch (boardFindDTO.getBoardType()) {
//            case FREE:
//                boardQueryRepository = freeboardQueryRepository;
//                break;
//            case IDOL:
//                boardQueryRepository = idolboardQueryRepository;
//                break;
//            default:
//                throw new IllegalArgumentException("잘못된 게시판입니다.");
//        }
//        BoardQueryRepository finalBoardQueryRepository = boardQueryRepository;
//        return finalBoardQueryRepository.findBoardByTitle(pageable,
//                query -> query.selectFrom(idolBoard)
//                        .where(defaultWhere(boardFindDTO), finalBoardQueryRepository.tapEq(boardFindDTO.getTap())));
//    }
    public Page<Board> findBoardByTitle(BoardFindDTO boardFindDTO, Pageable pageable) {
        switch (boardFindDTO.getBoardType()) {
            case FREE:
                return freeboardQueryRepository.findBoardByTitle(pageable, boardFindDTO);
            case IDOL:
                return idolboardQueryRepository.findBoardByTitle(pageable, boardFindDTO);
        }
        throw new IllegalArgumentException("잘못된 게시판입니다.");
    }

    @Transactional
    public void addComment(Long id, CommentCreateDTO commentCreateDTO) {
        Board board = boardJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.addComment(Comment.builder()
                .content(commentCreateDTO.getContent())
                .build());
    }
}
