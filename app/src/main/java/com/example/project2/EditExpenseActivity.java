package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditExpenseActivity extends AppCompatActivity {

    private int expenseIndex;
    private EditText edtAmount, edtCategory, edtDate, edtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        TextView title = findViewById(R.id.txtEditTitle);
        title.setText("Edit Expense");

        edtAmount   = findViewById(R.id.editTextAmount);
        edtCategory = findViewById(R.id.editTextCategory);
        edtDate     = findViewById(R.id.editTextDate);
        edtNote     = findViewById(R.id.editTextNote);
        Button btnSave   = findViewById(R.id.btnSaveExpense);
        Button btnCancel = findViewById(R.id.btnCancelExpense);

        // Get which expense to edit
        expenseIndex = getIntent().getIntExtra("expense_index", -1);
        if (expenseIndex < 0 || expenseIndex >= ExpenseStorage.expenses.size()) {
            Toast.makeText(this, "Expense not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Pre-fill fields
        Expense e = ExpenseStorage.expenses.get(expenseIndex);
        edtAmount.setText(e.amount);
        edtCategory.setText(e.category);
        edtDate.setText(e.date);
        edtNote.setText(e.note);

        btnSave.setOnClickListener(v -> {
            String amount   = edtAmount.getText().toString().trim();
            String category = edtCategory.getText().toString().trim();
            String date     = edtDate.getText().toString().trim();
            String note     = edtNote.getText().toString().trim();

            if (amount.isEmpty() || category.isEmpty()) {
                Toast.makeText(this,
                        "Amount and category are required",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Expense updated = new Expense(amount, category, date, note);
            ExpenseStorage.expenses.set(expenseIndex, updated);

            Toast.makeText(this, "Expense updated", Toast.LENGTH_SHORT).show();
            finish(); // back to history
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
