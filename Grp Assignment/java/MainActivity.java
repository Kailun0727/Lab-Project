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

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Book> mBookList;
    private BookListAdapter mAdapter;

    //login page members
    private TextView mLogo;
    private TextView tvRegister;
    private TextView mErrorMsg;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        mLogo = findViewById(R.id.logo);
        mErrorMsg = findViewById(R.id.errorMsg);
        tvRegister = findViewById(R.id.tv_doNotHaveAccount);
        etUsername = findViewById(R.id.inputUsername);
        etPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);

                String userDetails = preferences.getString(user + password + "data", "Username or password is Incorrect. ");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display", userDetails);
                editor.commit();

                Intent displayScreen = new Intent(MainActivity.this, DisplayScreen.class);
                startActivity(displayScreen);

            }
        });*/









        /*tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerPage = new Intent(MainActivity.this, Register.class);
                startActivity(registerPage);
            }
        });*/

    }




    public void haveAcc_clicked(View view) {
        Intent loginPage = new Intent(MainActivity.this, MainActivity.class);
        startActivity(loginPage);
    }


    public void noAcc_clicked(View view) {
        Intent registerPage = new Intent(MainActivity.this, Register.class);
        startActivity(registerPage);
        //setContentView(R.layout.activity_main);
    }

    public void btnLogin_clicked(View view) {
        String user = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);

        String userDetails = preferences.getString(user + password + "data", "Username or password is Incorrect. ");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("display", userDetails);
        editor.commit();

        String value = preferences.getString(user,null);
        if(value == null){
            // the key does not exist
            mErrorMsg.setText("Username or password is Incorrect.");
            mErrorMsg.setVisibility(View.VISIBLE);
        }
        else{
            Intent bookListPage = new Intent(MainActivity.this, BookRecyclerView.class);
            startActivity(bookListPage);
        }

    }


}