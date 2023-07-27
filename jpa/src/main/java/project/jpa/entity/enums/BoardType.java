package project.jpa.entity.enums;

public enum BoardType {
    FREE("FREE"), IDOL("IDOL");

    private final String value;

    BoardType(String value) {
        this.value = value;
    }
}
