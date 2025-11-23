package com.example.project2;

public class Expense {
    public long id;      // DB primary key
    public String amount;
    public String category;
    public String date;
    public String note;

    public Expense(long id, String amount, String category, String date, String note) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    // constructor without id when inserting
    public Expense(String amount, String category, String date, String note) {
        this(-1, amount, category, date, note);
    }
}
