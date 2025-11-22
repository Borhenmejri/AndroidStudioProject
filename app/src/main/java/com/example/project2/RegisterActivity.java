package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtCin, edtFirstname, edtLastname, edtUsername, edtPwd, edtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtCin = findViewById(R.id.editTextCin);
        edtFirstname = findViewById(R.id.editTextFirstname);
        edtLastname = findViewById(R.id.editTextLastname);
        edtUsername = findViewById(R.id.editTextRegUsername);
        edtPwd = findViewById(R.id.editTextRegPassword);
        edtConfirm = findViewById(R.id.editTextRegConfirm);

        Button btnCreate = findViewById(R.id.btnCreateAccount);
        TextView txtBack = findViewById(R.id.txtBackToLogin);

        btnCreate.setOnClickListener(v -> {
            String cin = edtCin.getText().toString().trim();
            String fn = edtFirstname.getText().toString().trim();
            String ln = edtLastname.getText().toString().trim();
            String user = edtUsername.getText().toString().trim();
            String pwd = edtPwd.getText().toString().trim();
            String conf = edtConfirm.getText().toString().trim();

            if (cin.isEmpty() || fn.isEmpty() || ln.isEmpty()
                    || user.isEmpty() || pwd.isEmpty() || conf.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pwd.equals(conf)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: save user in SQLite later
            Toast.makeText(this, "Account created (demo)", Toast.LENGTH_SHORT).show();
            finish();   // back to login
        });

        txtBack.setOnClickListener(v -> finish());
    }
}
