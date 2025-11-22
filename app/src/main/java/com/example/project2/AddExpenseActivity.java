package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        EditText edtAmount   = findViewById(R.id.editTextAmount);
        EditText edtCategory = findViewById(R.id.editTextCategory);
        EditText edtDate     = findViewById(R.id.editTextDate);
        EditText edtNote     = findViewById(R.id.editTextNote);

        Button btnSave   = findViewById(R.id.btnSaveExpense);
        Button btnCancel = findViewById(R.id.btnCancelExpense);

        btnSave.setOnClickListener(v -> {
            String amount   = edtAmount.getText().toString();
            String category = edtCategory.getText().toString();
            String date     = edtDate.getText().toString();
            String note     = edtNote.getText().toString();

            if (amount.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Amount and category are required", Toast.LENGTH_SHORT).show();
                return;
            }

            ExpenseStorage.expenses.add(new Expense(amount, category, date, note));

            Toast.makeText(this, "Expense saved", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
