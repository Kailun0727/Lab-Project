package com.example.libraryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private EditText et_confirmPassword;
    private TextView tv_login;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = findViewById(R.id.registerUsername);
        et_email = findViewById(R.id.registerEmail);
        et_password = findViewById(R.id.registerPassword);
        et_confirmPassword = findViewById(R.id.registerConfirmPassword);
        tv_login = findViewById(R.id.alreadyHaveAccount);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
                String newUsername = et_username.getText().toString();
                String newEmail = et_email.getText().toString();
                String newPassword = et_password.getText().toString();
                String newConfirmPassword = et_confirmPassword.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(newUsername + newPassword + "data",  newUsername + "\n" + newEmail);
                editor.commit();

                Intent login = new Intent(Register.this, MainActivity.class);
                startActivity(login);
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Register.this, MainActivity.class);
                startActivity(login);
            }
        });

    }
}
