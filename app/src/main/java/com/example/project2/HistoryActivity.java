package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout container;
    private TextView txtEmpty;
    private Button btnUpdateSelected, btnDeleteSelected;

    // index of selected row; -1 = none
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        container         = findViewById(R.id.containerHistory);
        txtEmpty          = findViewById(R.id.txtHistEmpty);
        btnUpdateSelected = findViewById(R.id.btnUpdateSelected);
        btnDeleteSelected = findViewById(R.id.btnDeleteSelected);

        // UPDATE button -> EditExpenseActivity
        btnUpdateSelected.setOnClickListener(v -> {
            if (selectedIndex < 0 || selectedIndex >= ExpenseStorage.expenses.size()) {
                Toast.makeText(this, "Select an expense first", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent i = new Intent(HistoryActivity.this, EditExpenseActivity.class);
            i.putExtra("expense_index", selectedIndex);
            startActivity(i);
        });

        // DELETE button -> DeleteExpenseActivity
        btnDeleteSelected.setOnClickListener(v -> {
            if (selectedIndex < 0 || selectedIndex >= ExpenseStorage.expenses.size()) {
                Toast.makeText(this, "Select an expense first", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent i = new Intent(HistoryActivity.this, DeleteExpenseActivity.class);
            i.putExtra("expense_index", selectedIndex);
            startActivity(i);
        });

        loadData();
    }

    private void loadData() {
        container.removeAllViews();
        selectedIndex = -1;

        if (ExpenseStorage.expenses.isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
            return;
        }

        txtEmpty.setVisibility(View.GONE);

        for (int i = 0; i < ExpenseStorage.expenses.size(); i++) {
            Expense e = ExpenseStorage.expenses.get(i);
            final int index = i;

            TextView row = new TextView(this);
            String line = "$" + e.amount + " • " + e.category;
            if (e.date != null && !e.date.isEmpty()) {
                line += " • " + e.date;
            }
            row.setText(line);
            row.setTextSize(14f);
            row.setPadding(0, 12, 0, 12);
            row.setGravity(Gravity.START);

            row.setBackgroundColor(0x00000000);

            // When user taps a row → select it
            row.setOnClickListener(v -> {
                selectedIndex = index;
                highlightSelectedRow(index);
            });

            container.addView(row);
        }
    }

    // Highlight selected row in the list
    private void highlightSelectedRow(int indexToHighlight) {
        int childCount = container.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = container.getChildAt(i);
            if (i == indexToHighlight) {
                child.setBackgroundColor(0x220066FF); // light blue
            } else {
                child.setBackgroundColor(0x00000000);  // transparent
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // refresh after edit/delete
    }
}
