package com.example.hellosqlite;

public class User {
    private int id;
    private String name;
    private String date_of_birth;
    private String phone;

    public User() {
    }

    public User(String name, String date_of_birth, String phone) {
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
    }

    public User(int id, String name, String date_of_birth, String phone) {
        this.id = id;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
