package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private ExpenseDao expenseDao;
    private TextView txtBalanceValue;
    // ... your other fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        expenseDao = new ExpenseDao(this);

        txtBalanceValue = findViewById(R.id.txtBalanceValue);
        TextView txtUser    = findViewById(R.id.txtDashUsername);
        Button   btnAdd     = findViewById(R.id.btnGoAddExpense);
        TextView btnHistory = findViewById(R.id.btnGoHistory);
        TextView btnProfile = findViewById(R.id.btnGoProfile);

        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            txtUser.setText(username);
        }

        btnAdd.setOnClickListener(v ->
                startActivity(new android.content.Intent(
                        DashboardActivity.this, AddExpenseActivity.class))
        );

        btnHistory.setOnClickListener(v ->
                startActivity(new android.content.Intent(
                        DashboardActivity.this, HistoryActivity.class))
        );

        // Profile button as you already have (optional)
    }

    @Override
    protected void onResume() {
        super.onResume();
        double total = expenseDao.getTotalAmount();
        txtBalanceValue.setText("$" + String.format(java.util.Locale.US, "%.2f", total));
    }
}

