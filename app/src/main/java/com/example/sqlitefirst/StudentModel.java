package com.example.sqlitefirst;

import java.io.Serializable;

public class StudentModel implements Serializable {

    int id;
    String address;
    int age;
    String name;

    public StudentModel(String address, int age, String name) {
        this.address = address;
        this.age = age;
        this.name = name;
    }

    public StudentModel(int id, String address, int age, String name) {
        this.id = id;
        this.address = address;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
