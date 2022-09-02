package com.revature.entity;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Ticket {

    //private member variables
    private int id;
    private String status = "pending";
    private BigDecimal amount;
    private String description;

    private int employeeId;
    private String type;

    //default constructor
    public Ticket() {

    }

    //fully parameterized constructor
    public Ticket(int id, String status, BigDecimal amount, String description, int employeeId, String type) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.description = description;
        this.employeeId = employeeId;
        this.type = type;
    }

    //constructor sans id
    public Ticket(String status, BigDecimal amount, String description, int employeeId, String type) {
        this.status = status;
        this.amount = amount;
        this.description = description;
        this.employeeId = employeeId;
        this.type = type;
    }

    //public getters and setters for member variables
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    @Override
    public String toString() {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        String s = n.format(amount);
        return "TICKET {" +
                "ID: " + id +
                "     Employee ID: " + employeeId +
                "     Status: " + status +
                "     Amount: " + s +
                "     Type: " + type +
                "     Description: '" + description + '\'' +
                '}';
    }

}
