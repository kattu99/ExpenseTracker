package com.example.rahulkataria.expensetracker;

public class Expense {
    public String location;
    public String phone_number;
    public double amount;

    public Expense(String location, String phone_number, double amount) {
        this.location = location;
        this.phone_number = phone_number;
        this.amount = amount;
    }
}
