package project.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.jpa.entity.Board;
import project.jpa.entity.Comment;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {
    // count 가 정상 작동하지 않는다. 맨 처음 루트 코멘트들의 갯수만 세진다.
    @Query(value = "select c from Comment c left join fetch c.children where c.board.id = ?1", countQuery = "select count(c) from Comment c left join c.children where c.board.id = ?1")
    Page<Comment> findById(Long id, Pageable pageable);
}
