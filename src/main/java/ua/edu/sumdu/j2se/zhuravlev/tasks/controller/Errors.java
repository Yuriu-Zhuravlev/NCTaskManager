package ua.edu.sumdu.j2se.zhuravlev.tasks.controller;

public enum  Errors {
    OPEN_FILE("Could not open file"),
    USER_INPUT("Caught IllegalArgumentException caused by user's input"),
    CLONE("Cannot clone task"),
    DESERIALIZATION("Could not find class for deserialization")
    ;

    private String error;

    Errors(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
