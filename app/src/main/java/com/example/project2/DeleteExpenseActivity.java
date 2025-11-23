package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class DeleteExpenseActivity extends AppCompatActivity {

    private int expenseIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_expense);

        TextView txtMessage = findViewById(R.id.txtDeleteMessage);
        Button btnConfirm   = findViewById(R.id.btnConfirmDelete);
        Button btnCancel    = findViewById(R.id.btnCancelDelete);

        expenseIndex = getIntent().getIntExtra("expense_index", -1);
        if (expenseIndex < 0 || expenseIndex >= ExpenseStorage.expenses.size()) {
            Toast.makeText(this, "Expense not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Expense e = ExpenseStorage.expenses.get(expenseIndex);
        txtMessage.setText(
                "Delete this expense?\n$" + e.amount + " • " + e.category +
                        ((e.date != null && !e.date.isEmpty()) ? " • " + e.date : "")
        );

        btnConfirm.setOnClickListener(v -> {
            ExpenseStorage.expenses.remove(expenseIndex);
            Toast.makeText(this, "Expense deleted", Toast.LENGTH_SHORT).show();
            finish(); // back to history
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
