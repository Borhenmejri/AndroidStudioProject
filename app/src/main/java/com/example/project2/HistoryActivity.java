package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        LinearLayout container = findViewById(R.id.containerHistory);
        TextView txtEmpty = findViewById(R.id.txtHistEmpty);

        // If no expenses, keep "No expenses recorded yet."
        if (ExpenseStorage.expenses.isEmpty()) {
            txtEmpty.setVisibility(TextView.VISIBLE);
            return;
        }

        // There are expenses → hide empty text and display rows
        txtEmpty.setVisibility(TextView.GONE);

        for (Expense e : ExpenseStorage.expenses) {
            TextView row = new TextView(HistoryActivity.this);
            // simple line: $12.00 • Food • 2025-11-22
            String line = "$" + e.amount + " • " + e.category;
            if (e.date != null && !e.date.isEmpty()) {
                line += " • " + e.date;
            }
            row.setText(line);
            row.setTextSize(14f);
            row.setPadding(0, 12, 0, 12);
            row.setGravity(Gravity.START);
            container.addView(row);
        }
    }
}
