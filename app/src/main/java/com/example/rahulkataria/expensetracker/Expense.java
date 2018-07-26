package com.example.rahulkataria.expensetracker;

import java.util.Date;

public class Expense {
    public String location;
    public String phone_number;
    public double amount;
    public String type;
    public Date date;

    public Expense(String location, String phone_number, double amount,String type,Date date) {
        this.location = location;
        this.phone_number = phone_number;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }
}
