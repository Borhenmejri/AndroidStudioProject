package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText editTextAmount, editTextCategory, editTextDate, editTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        editTextAmount   = findViewById(R.id.editTextAmount);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextDate     = findViewById(R.id.editTextDate);
        editTextNote     = findViewById(R.id.editTextNote);

        Button btnSave   = findViewById(R.id.btnSaveExpense);
        Button btnCancel = findViewById(R.id.btnCancelExpense);

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

            Expense e = new Expense(amount, category, date, note);
            ExpenseStorage.expenses.add(e);   // 👈 only this

            Toast.makeText(this, "Expense added", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
