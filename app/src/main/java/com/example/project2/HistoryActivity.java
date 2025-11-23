package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ExpenseDao expenseDao;
    private LinearLayout container;
    private TextView txtEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        expenseDao = new ExpenseDao(this);
        container  = findViewById(R.id.containerHistory);
        txtEmpty   = findViewById(R.id.txtHistEmpty);

        loadData();
    }

    private void loadData() {
        container.removeAllViews();

        List<Expense> expenses = expenseDao.getAll();

        if (expenses.isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
            return;
        }

        txtEmpty.setVisibility(View.GONE);

        for (Expense e : expenses) {
            TextView row = new TextView(this);

            String line = "$" + e.amount + " • " + e.category;
            if (e.date != null && !e.date.isEmpty()) {
                line += " • " + e.date;
            }

            row.setText(line);
            row.setTextSize(14f);
            row.setPadding(0, 12, 0, 12);
            row.setGravity(Gravity.START);

            // 👉 Tap row: show Edit / Delete menu
            row.setOnClickListener(v -> {
                Intent i = new Intent(HistoryActivity.this, EditExpenseActivity.class);
                i.putExtra("expense_id", e.id);
                startActivity(i);
            });

        }
    }

    private void showOptionsDialog(Expense e) {
        String[] options = {"Edit", "Delete", "Cancel"};

        new AlertDialog.Builder(this)
                .setTitle("Expense options")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0: // Edit
                            Intent i = new Intent(HistoryActivity.this, EditExpenseActivity.class);
                            i.putExtra("expense_id", e.id);
                            startActivity(i);
                            break;

                        case 1: // Delete
                            expenseDao.delete(e.id);
                            Toast.makeText(this, "Expense deleted", Toast.LENGTH_SHORT).show();
                            loadData(); // refresh
                            break;

                        case 2: // Cancel
                        default:
                            dialog.dismiss();
                            break;
                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();   // refresh after editing
    }
}
