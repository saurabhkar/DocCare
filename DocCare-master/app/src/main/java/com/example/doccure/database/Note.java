package com.example.doccure.database;

public class Note {
    private String title;
    private String description;
    private String priority;

    public Note(){
        //empty constructor
    }

    public Note(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }
}
