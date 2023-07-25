package project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.jpa.entity.Board;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {
}
