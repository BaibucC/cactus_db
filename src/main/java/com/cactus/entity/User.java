package com.cactus.entity;

import javax.validation.constraints.NotEmpty;

public class User {

    private int id;
    private String name;
    private String employee;
    private String date1;
    private String date2;

    public User() {
    }

    public User(String name, String employee, String address, String telephone) {
        this.name = name;
        this.employee = employee;
        this.date1 = address;
        this.date2 = telephone;
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

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

}
