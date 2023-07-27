package project.jpa.dto;

import lombok.Getter;
import project.jpa.entity.enums.BoardType;

@Getter
public class BoardFindDTO {
    private String title;
    private String content;
    private String createdBy;
    private BoardType boardType;
    private String tap;

    public BoardFindDTO() {}

    public BoardFindDTO(String title, String content, String createdBy, BoardType boardType, String tap) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.boardType = boardType;
        this.tap = tap;
    }
}
