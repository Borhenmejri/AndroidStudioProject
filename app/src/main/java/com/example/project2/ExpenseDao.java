package com.example.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDao {

    private final SQLiteDatabase db;

    public ExpenseDao(Context context) {
        MyDatabaseHelper helper = new MyDatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    // CREATE
    public long insert(Expense e) {
        ContentValues cv = new ContentValues();
        cv.put(MyDatabaseHelper.COL_AMOUNT, Double.parseDouble(e.amount));
        cv.put(MyDatabaseHelper.COL_CATEGORY, e.category);
        cv.put(MyDatabaseHelper.COL_DATE, e.date);
        cv.put(MyDatabaseHelper.COL_NOTE, e.note);
        return db.insert(MyDatabaseHelper.TABLE_EXPENSE, null, cv);
    }

    // READ ALL
    public List<Expense> getAll() {
        List<Expense> list = new ArrayList<>();

        Cursor c = db.query(
                MyDatabaseHelper.TABLE_EXPENSE,
                null,
                null,
                null,
                null,
                null,
                MyDatabaseHelper.COL_ID + " DESC"
        );

        if (c != null) {
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_ID));
                double amount = c.getDouble(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_AMOUNT));
                String category = c.getString(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_CATEGORY));
                String date = c.getString(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_DATE));
                String note = c.getString(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_NOTE));

                list.add(new Expense(id, String.valueOf(amount), category, date, note));
            }
            c.close();
        }

        return list;
    }

    // READ ONE
    public Expense getById(long id) {
        Expense e = null;
        Cursor c = db.query(
                MyDatabaseHelper.TABLE_EXPENSE,
                null,
                MyDatabaseHelper.COL_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        if (c != null && c.moveToFirst()) {
            double amount = c.getDouble(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_AMOUNT));
            String category = c.getString(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_CATEGORY));
            String date = c.getString(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_DATE));
            String note = c.getString(c.getColumnIndexOrThrow(MyDatabaseHelper.COL_NOTE));
            e = new Expense(id, String.valueOf(amount), category, date, note);
        }
        if (c != null) c.close();
        return e;
    }

    // UPDATE
    public int update(Expense e) {
        ContentValues cv = new ContentValues();
        cv.put(MyDatabaseHelper.COL_AMOUNT, Double.parseDouble(e.amount));
        cv.put(MyDatabaseHelper.COL_CATEGORY, e.category);
        cv.put(MyDatabaseHelper.COL_DATE, e.date);
        cv.put(MyDatabaseHelper.COL_NOTE, e.note);
        return db.update(
                MyDatabaseHelper.TABLE_EXPENSE,
                cv,
                MyDatabaseHelper.COL_ID + "=?",
                new String[]{String.valueOf(e.id)}
        );
    }

    // DELETE
    public int delete(long id) {
        return db.delete(
                MyDatabaseHelper.TABLE_EXPENSE,
                MyDatabaseHelper.COL_ID + "=?",
                new String[]{String.valueOf(id)}
        );
    }

    // TOTAL for dashboard
    public double getTotalAmount() {
        double total = 0;
        Cursor c = db.rawQuery(
                "SELECT SUM(" + MyDatabaseHelper.COL_AMOUNT + ") AS total FROM " +
                        MyDatabaseHelper.TABLE_EXPENSE, null);

        if (c != null && c.moveToFirst()) {
            total = c.getDouble(c.getColumnIndexOrThrow("total"));
        }
        if (c != null) c.close();
        return total;
    }
}
