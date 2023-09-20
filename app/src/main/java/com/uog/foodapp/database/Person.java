package com.uog.foodapp.database;

public class Person {
    private int id;
    private String name;
    private String address;
    private String phone;
    private int age;

    public Person() {
    }

    public Person(int id, String name, String address, String phone, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
