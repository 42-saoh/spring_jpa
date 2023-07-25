package project.jpa.dto;

import lombok.Getter;

@Getter
public class BoardCreateDTO {
    private String title;
    private String content;

    public BoardCreateDTO() {}

    public BoardCreateDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
