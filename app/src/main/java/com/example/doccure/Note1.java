package com.example.doccure;

public class Note1 {
    private String name;
    private String dob;
    private String phone;
    private String city;
    private String state;
    private String time;

public Note1()
{

}
    public Note1(String name, String dob, String phone, String city, String state, String time) {
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }
}
