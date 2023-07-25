package project.jpa.dto;

import lombok.Getter;

@Getter
public class CommentCreateDTO {
    private String content;

    public CommentCreateDTO() {}

    public CommentCreateDTO(String content) {
        this.content = content;
    }
}
