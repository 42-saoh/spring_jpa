package project.jpa.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jpa.dto.BoardCreateDTO;
import project.jpa.dto.BoardFindDTO;
import project.jpa.dto.CommentCreateDTO;
import project.jpa.entity.Board;
import project.jpa.service.BoardService;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody BoardCreateDTO boardCreateDTO) {
        boardService.save(boardCreateDTO);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/find")
    public ResponseEntity<Page<Board>> findBoardByTitle(@RequestParam Map<String, String> param, @PageableDefault Pageable pageable) {
        BoardFindDTO boardFindDTO = new BoardFindDTO(param.get("title"), param.get("content"), param.get("createdBy"));
        return ResponseEntity.ok(boardService.findBoardByTitle(boardFindDTO, pageable));
    }

    @PutMapping("/{id}/comment")
    public ResponseEntity<String> addComment(@PathVariable("id") Long id, @RequestBody CommentCreateDTO commentCreateDTO) {
        boardService.addComment(id, commentCreateDTO);
        return ResponseEntity.ok("success");
    }
}
