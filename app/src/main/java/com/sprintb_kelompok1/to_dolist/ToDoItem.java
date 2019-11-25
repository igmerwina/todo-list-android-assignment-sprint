package com.sprintb_kelompok1.to_dolist;

public class ToDoItem {
    private final String id;
    private final String message;

    public ToDoItem(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
