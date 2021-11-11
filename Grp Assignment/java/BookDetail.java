package com.example.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class BookDetail extends AppCompatActivity {

    private TextView mIdText;
    private TextView mTitleText;
    private TextView mAuthorText;
    private ImageView ivBook;
    private TextView mNumberCopies;
    private Button mBtnBorrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIdText =  (TextView) findViewById (R.id.id);
        mTitleText = (TextView) findViewById (R.id.title);
        mAuthorText = (TextView)findViewById (R.id.author);
        ivBook = (ImageView) findViewById (R.id.iv_book);
        mNumberCopies = (TextView) findViewById (R.id.numberCopies);
        mBtnBorrow = findViewById(R.id.btn_borrow);

        //Retrieve data from viewholder
        Intent i = getIntent();
        mIdText.setText("ID :" + getIntent().getStringExtra("id"));
        mTitleText.setText("Title :" +getIntent().getStringExtra("title"));
        mAuthorText.setText("Author :" +getIntent().getStringExtra("author"));
        Glide.with(this).load(getIntent().getIntExtra("image",0)).into(ivBook);
        mNumberCopies.setText("Number Copies :" +getIntent().getStringExtra("numberCopies"));


    }
}
