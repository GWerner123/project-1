package com.revature.entity;

public class Manager {

    //private member variables
    private int id;
    private String username;
    private String password;

    //default constructor
    public Manager() {

    }

    //fully parameterized constructor
    public Manager(int id,String username,String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //constructor sans id
    public Manager(String username,String password) {
        this.username = username;
        this.password = password;
    }

    //public getters and setters for member variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
