package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditExpenseActivity extends AppCompatActivity {

    private int expenseIndex;
    private EditText editTextAmount, editTextCategory, editTextDate, editTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        TextView txtTitle = findViewById(R.id.txtEditTitle);
        txtTitle.setText("Edit Expense");

        editTextAmount   = findViewById(R.id.editTextAmount);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextDate     = findViewById(R.id.editTextDate);
        editTextNote     = findViewById(R.id.editTextNote);

        Button  btnSave   = findViewById(R.id.btnSaveExpense);
        TextView btnCancel = findViewById(R.id.btnCancelExpense);   // 👈 TextView, not Button

        expenseIndex = getIntent().getIntExtra("expense_index", -1);
        if (expenseIndex < 0 || expenseIndex >= ExpenseStorage.expenses.size()) {
            Toast.makeText(this, "Expense not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Expense e = ExpenseStorage.expenses.get(expenseIndex);
        editTextAmount.setText(e.amount);
        editTextCategory.setText(e.category);
        editTextDate.setText(e.date);
        editTextNote.setText(e.note);

        btnSave.setOnClickListener(v -> {
            String amount   = editTextAmount.getText().toString().trim();
            String category = editTextCategory.getText().toString().trim();
            String date     = editTextDate.getText().toString().trim();
            String note     = editTextNote.getText().toString().trim();

            if (amount.isEmpty() || category.isEmpty()) {
                Toast.makeText(this,
                        "Amount and category are required",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Expense updated = new Expense(amount, category, date, note);
            ExpenseStorage.expenses.set(expenseIndex, updated);

            Toast.makeText(this, "Expense updated", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
