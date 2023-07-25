package project.jpa.dto;

import lombok.Getter;

@Getter
public class BoardFindDTO {
    private String title;
    private String content;
    private String createdBy;

    public BoardFindDTO() {}

    public BoardFindDTO(String title, String content, String createdBy) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }
}
