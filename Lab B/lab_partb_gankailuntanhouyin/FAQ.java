package com.example.lab_partb_gankailuntanhouyin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class FAQ extends AppCompatActivity {
    private final LinkedList<String> mFAQ = new LinkedList<>();
    private final LinkedList<String> mFAQQuestion = new LinkedList<>();
    private final LinkedList<String> mFAQAnswer = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private FAQListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);
        mRecyclerView = findViewById(R.id.rv_FAQ);

        mFAQ.addLast("How to make an order ?");
        mFAQ.addLast("Can I cancel my order ?");
        mFAQ.addLast("How to view my order status?");
        mFAQ.addLast("How long do I need to wait for my order ?");
        mFAQ.addLast("Any hidden cost for my order ?");

        mFAQQuestion.addLast("How to make an order ?");
        mFAQQuestion.addLast("Can I cancel my order ?");
        mFAQQuestion.addLast("How to view my order status?");
        mFAQQuestion.addLast("How long do I need to wait for my order ?");
        mFAQQuestion.addLast("Any hidden cost for my order ?");


        mFAQAnswer.addLast("Select your order type, main course option, snack option to place your order");
        mFAQAnswer.addLast("Yes, you can cancel your order");
        mFAQAnswer.addLast("You can view order status in user profile ");
        mFAQAnswer.addLast("30 - 60 minutes");
        mFAQAnswer.addLast("No, there is no hidden cost for your order");

        //Create adapter object
        mAdapter = new FAQListAdapter(this,mFAQ,mFAQQuestion,mFAQAnswer);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}