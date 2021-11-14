package com.example.libraryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity {
    //login page members

    private TextView mErrorMsg;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tv_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        mErrorMsg = findViewById(R.id.errorMsg);
        etUsername = findViewById(R.id.inputUsername);
        etPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tv_register = findViewById(R.id.tv_doNotHaveAccount);




    }




}
