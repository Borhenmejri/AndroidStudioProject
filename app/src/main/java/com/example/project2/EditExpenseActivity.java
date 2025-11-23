package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditExpenseActivity extends AppCompatActivity {

    private ExpenseDao expenseDao;
    private long expenseId;
    private EditText edtAmount, edtCategory, edtDate, edtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        // small label just to be sure screen is loaded
        TextView title = findViewById(R.id.txtEditTitle);
        title.setText("Edit Expense");

        expenseDao = new ExpenseDao(this);

        edtAmount   = findViewById(R.id.editTextAmount);
        edtCategory = findViewById(R.id.editTextCategory);
        edtDate     = findViewById(R.id.editTextDate);
        edtNote     = findViewById(R.id.editTextNote);
        Button btnSave   = findViewById(R.id.btnSaveExpense);
        Button btnCancel = findViewById(R.id.btnCancelExpense);

        // 1) Get id from intent
        expenseId = getIntent().getLongExtra("expense_id", -1);
        if (expenseId == -1) {
            Toast.makeText(this, "No expense id passed", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 2) Load from DB
        Expense e = expenseDao.getById(expenseId);
        if (e == null) {
            Toast.makeText(this, "Expense not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 3) Fill fields
        edtAmount.setText(e.amount);
        edtCategory.setText(e.category);
        edtDate.setText(e.date);
        edtNote.setText(e.note);

        // 4) Save changes
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

            Expense updated = new Expense(expenseId, amount, category, date, note);
            expenseDao.update(updated);

            Toast.makeText(this, "Expense updated", Toast.LENGTH_SHORT).show();
            finish();   // go back to history
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
