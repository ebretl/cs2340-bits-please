package model;

/**
 * Created by Abhay Dalmia on 9/24/2016.
 */
public enum UserType {
    USER("user"), WORKER("worker"), MANAGER("manager"), ADMIN("admin");

    private String databaseRepresentation;

    private UserType(String databaseRepresentation) {
        this.databaseRepresentation = databaseRepresentation;
    }

    public String getDatabaseRepresentation() {
        return databaseRepresentation;
    }
}
