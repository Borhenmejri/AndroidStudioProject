package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtUser, edtPwd;
    private TextView txtUserError, txtPwdError, txtCreateAccount;
    private Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   // ✅ no EdgeToEdge, no R.id.main

        // ---- get views from XML ----
        edtUser = findViewById(R.id.editTextUsername);
        edtPwd = findViewById(R.id.editTextPassword);
        txtUserError = findViewById(R.id.txtUsernameError);
        txtPwdError = findViewById(R.id.txtPasswordError);
        txtCreateAccount = findViewById(R.id.txtCreateAccount);
        btnConnect = findViewById(R.id.btnConnect);

        // ---- "Create a new account" → RegisterActivity ----
        txtCreateAccount.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        // ---- Login button → simple validation then DashboardActivity ----
        btnConnect.setOnClickListener(v -> {
            txtUserError.setVisibility(View.GONE);
            txtPwdError.setVisibility(View.GONE);

            String user = edtUser.getText().toString().trim();
            String pwd = edtPwd.getText().toString().trim();
            boolean hasError = false;

            if (user.isEmpty()) {
                txtUserError.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (pwd.isEmpty()) {
                txtPwdError.setVisibility(View.VISIBLE);
                hasError = true;
            }
            if (hasError) return;

            // TODO later: check user from SQLite
            Intent i = new Intent(MainActivity.this, DashboardActivity.class);
            i.putExtra("username", user);
            startActivity(i);
        });
    }
}
