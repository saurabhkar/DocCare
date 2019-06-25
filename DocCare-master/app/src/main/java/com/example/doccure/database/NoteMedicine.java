package com.example.doccure.database;

public class NoteMedicine {

    private String name;
    private String slno;

    public NoteMedicine(){
        //empty
    }

    public NoteMedicine(String name, String slno) {
        this.name = name;
        this.slno = slno;
    }

    public String getName() {
        return name;
    }

    public String getSlno() {
        return slno;
    }
}
