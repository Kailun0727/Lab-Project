package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //login page members
    private TextView mLogo;
    private TextView tvRegister;
    private TextView mId;
    private TextView mNumberCopies;
    private TextView mErrorMsg;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //Initialize the ArrayLIst that will contain the data
        mLogo = findViewById(R.id.logo);
        mErrorMsg = findViewById(R.id.errorMsg);
        mNumberCopies =findViewById(R.id.numberCopies);
        mId = findViewById(R.id.id);
        tvRegister = findViewById(R.id.tv_doNotHaveAccount);
        etUsername = findViewById(R.id.inputUsername);
        etPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);

    }



    public void haveAcc_clicked(View view) {
        Intent loginPage = new Intent(MainActivity.this, MainActivity.class);
        startActivity(loginPage);
    }


    public void noAcc_clicked(View view) {
        Intent registerPage = new Intent(MainActivity.this, Register.class);
        startActivity(registerPage);
    }


    public void btnLogin_clicked(View view) {
        String user = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);

        String userDetails = preferences.getString(user + password + "data", "Username or password is Incorrect. ");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("display", userDetails);


        editor.commit();

        String username = preferences.getString(user,null);
        String userPass = preferences.getString(password,null);
        if(username == null || userPass == null){
            // the key does not exist
            mErrorMsg.setText("Username or password is Incorrect.");
            mErrorMsg.setVisibility(View.VISIBLE);

        }
        else{
            Intent bookListPage = new Intent(MainActivity.this, BookList.class);
            bookListPage.putExtra("username",username);
            startActivity(bookListPage);
        }
    }


}