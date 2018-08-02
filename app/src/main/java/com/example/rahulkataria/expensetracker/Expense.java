package com.example.rahulkataria.expensetracker;

import java.util.Date;

public class Expense {
    public String location;
    public String phone_number;
    public double amount;
    public String type;
    public String date;

    public Expense(String location, String phone_number, double amount,String type,String date) {
        this.location = location;
        this.phone_number = phone_number;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public String getPlace() {
        return location;
    }

    public double getPrice() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
