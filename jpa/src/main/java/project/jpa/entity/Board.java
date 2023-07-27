package project.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import project.jpa.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
// 게시판의 수가 적으면 SINGLE_TABLE 을 고려
// 게시판의 수가 많아지고 공통된 게시판의 특성이 많다면 JOINED 을 고려
// 게시판의 수가 많아지고 각 게시판의 특성이 많다면 TABLE_PER_CLASS 을 고려
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "board_type")
public abstract class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    protected Board() {}

    protected Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.insertBoard(this);
    }
}
