package com.example.project2;

public class Expense {
    public String amount;
    public String category;
    public String date;
    public String note;

    public Expense(String amount, String category, String date, String note) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }
}
