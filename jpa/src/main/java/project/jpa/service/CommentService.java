package project.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.jpa.dto.CommentCreateDTO;
import project.jpa.entity.Comment;
import project.jpa.repository.CommentJpaRepository;

@Service
public class CommentService {
    private final CommentJpaRepository commentJpaRepository;

    public CommentService(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }

    public Page<Comment> find(long id, Pageable pageable) {
        return commentJpaRepository.findById(id, pageable);
    }

    @Transactional
    public void addComment(Long id, CommentCreateDTO commentCreateDTO) {
        Comment parent = commentJpaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        parent.addChild(Comment.builder()
                .content(commentCreateDTO.getContent())
                .build());
    }
}
