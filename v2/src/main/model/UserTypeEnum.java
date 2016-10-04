package main.model;


public enum UserTypeEnum {
    USER("user"), WORKER("worker"), MANAGER("manager"), ADMIN("admin");

    private String databaseRepresentation;

    private UserTypeEnum(String databaseRepresentation) {
        this.databaseRepresentation = databaseRepresentation;
    }

    public String toString() {
        return databaseRepresentation;
    }
}
