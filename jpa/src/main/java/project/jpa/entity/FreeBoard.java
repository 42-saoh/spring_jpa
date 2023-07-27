package project.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import project.jpa.entity.enums.FreeTap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Entity
@DiscriminatorValue("FREE")
public class FreeBoard extends Board {
    @Enumerated(EnumType.STRING)
    private FreeTap freeTap;

    public FreeBoard() {
        super();
    }

    @Builder
    public FreeBoard(String title, String content, FreeTap freeTap) {
        super(title, content);
        this.freeTap = freeTap;
    }
}
