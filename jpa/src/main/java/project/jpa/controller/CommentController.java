package project.jpa.controller;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.jpa.dto.CommentCreateDTO;
import project.jpa.entity.Comment;
import project.jpa.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PutMapping("/{id}/add")
    public void addComment(@PathVariable("id") Long id, @RequestBody CommentCreateDTO commentCreateDTO) {
        commentService.addComment(id, commentCreateDTO);
    }

    @GetMapping("/{id}/find")
    public ResponseEntity<Page<Comment>> findComment(@PathVariable("id") Long id, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(commentService.find(id, pageable));
    }
}
