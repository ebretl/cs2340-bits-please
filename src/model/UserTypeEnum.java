package model;


public enum UserTypeEnum {
    USER("user"), WORKER("worker"), MANAGER("manager"), ADMIN("admin");

    private final String databaseRepresentation;

    UserTypeEnum(String databaseRepresentation) {
        this.databaseRepresentation = databaseRepresentation;
    }

    public String toString() {
        return databaseRepresentation;
    }
}
