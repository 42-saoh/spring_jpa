package project.jpa.dto;

import lombok.Getter;
import project.jpa.entity.enums.BoardType;

@Getter
public class BoardCreateDTO {
    private String title;
    private String content;
    private BoardType boardType;
    private String tap;

    public BoardCreateDTO() {}

    public BoardCreateDTO(String title, String content, BoardType boardType, String tap) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.tap = tap;
    }
}
