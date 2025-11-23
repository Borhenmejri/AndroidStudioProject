package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);   // ✅ must exist

        // Get views
        TextView txtUser = findViewById(R.id.txtDashUsername);
        Button btnAdd = findViewById(R.id.btnGoAddExpense);
        TextView btnHistory = findViewById(R.id.btnGoHistory);
        TextView btnProfile = findViewById(R.id.btnGoProfile);


        // Set username coming from MainActivity
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            txtUser.setText(username);
        }

        // Go to Add Expense
        btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(DashboardActivity.this, AddExpenseActivity.class);
            startActivity(i);
        });

        // Go to History
        btnHistory.setOnClickListener(v -> {
            Intent i = new Intent(DashboardActivity.this, HistoryActivity.class);
            startActivity(i);
        });

        // Profile – you can fill this later
        btnProfile.setOnClickListener(v -> {
            // TODO: open ProfileActivity later
        });
    }
}
