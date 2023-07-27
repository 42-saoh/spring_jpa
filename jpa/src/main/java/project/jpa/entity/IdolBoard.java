package project.jpa.entity;

import lombok.Builder;
import lombok.Getter;
import project.jpa.entity.enums.IdolTap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Entity
@DiscriminatorValue("IDOL")
public class IdolBoard extends Board {
    @Enumerated(EnumType.STRING)
    private IdolTap idolTap;

    public IdolBoard() {
        super();
    }

    @Builder
    public IdolBoard(String title, String content, IdolTap idolTap) {
        super(title, content);
        this.idolTap = idolTap;
    }
}
