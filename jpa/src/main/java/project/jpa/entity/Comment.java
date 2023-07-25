package project.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import project.jpa.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Comment parent;
    private String parentName;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> children = new ArrayList<>();

    public Comment() {}

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void insertBoard(Board board) {
        this.board = board;
    }

    // board를 안 넣어주는것이 정규화에 더 좋다.
    // board_id 를 조인함으로써 조상 comment를 찾고 그 체인을 통해 나머지 comment를 찾는게 맞다.
    public void insertParent(Comment parent) {
        this.parent = parent;
        this.parentName = parent.getCreatedBy();
    }

    public void addChild(Comment child) {
        this.children.add(child);
        child.insertParent(this);
    }
}
