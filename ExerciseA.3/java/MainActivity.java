package com.example.lab2_gankailuntanhouyin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final static String KEY_STATUS="com.example.lab2_gankailuntanhouyin.status";
    private final static String KEY_QUES_ANS="com.example.lab2_gankailuntanhouyin.ques_ans";
    private final static String KEY_CORRECT_ANS="com.example.lab2_gankailuntanhouyin.correct_ans";
    private final static String KEY_REPLY="com.example.lab2_gankailuntanhouyin.reply";

    private TextView m_tv_question;
    private EditText m_et_answer;
    private Button m_btnSubmit;
    private Button m_btnNewQues;
    private int m_random1;
    private int m_random2;
    private int m_operator;
    private String question;
    private int answer;
    private int correctAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_tv_question =findViewById(R.id.tvQues);
        m_et_answer =findViewById(R.id.etAnswer);
        m_btnSubmit =findViewById(R.id.btnSubmit);
        m_btnNewQues =findViewById(R.id.btnNewQues);

        Random r = new Random();
        m_random1 = r.nextInt(999)+1;
        m_random2 = r.nextInt(999)+1;
        m_operator = r.nextInt(3);

        if (m_operator == 0) {
            question = m_random1 +" + " +m_random2+" =";
            correctAnswer = m_random1 + m_random2;
        }
        else if (m_operator == 1){
            question = m_random1 +" - " +m_random2+" =";
            correctAnswer = m_random1 - m_random2;
        }
        else{
            question = m_random1 +" * " +m_random2+" =";
            correctAnswer = m_random1 * m_random2;
        }

        m_tv_question.setText(question);

        if (savedInstanceState != null){
            question = savedInstanceState.getString("Question");
            m_tv_question.setText(question);
        }

    }

    public void btnNewQues_clicked(View view) {
        Random r = new Random();
        m_random1 = r.nextInt(999)+1;
        m_random2 = r.nextInt(999)+1;
        m_operator = r.nextInt(3);

        //Generate random number
        if (m_operator == 0) {
            question = m_random1 +" + " +m_random2+" = ";
            correctAnswer = m_random1 + m_random2;
        }
        else if (m_operator == 1){
            question = m_random1 +" - " +m_random2+" = ";
            correctAnswer = m_random1 - m_random2;
        }
        else{
            question = m_random1 +" * " +m_random2+" = ";
            correctAnswer = m_random1 * m_random2;
        }
        m_tv_question.setText(question);

    }

    public void btnSubmit_clicked(View view) {
        String answer = m_et_answer.getText().toString();

        //Check the input of user
        if (TextUtils.isEmpty(answer)){
            Toast.makeText(this,"Please enter answer",Toast.LENGTH_SHORT).show();
        }else{
            Intent sendIntent = new Intent(MainActivity.this,MainActivity2.class);
            question +=answer;
            //nested if statement to check answer correct or wrong
            if (Integer.parseInt(answer) == correctAnswer){
                sendIntent.putExtra(KEY_STATUS,1);
                sendIntent.putExtra(KEY_QUES_ANS,question);
                sendIntent.putExtra(KEY_CORRECT_ANS,correctAnswer);
                startActivity(sendIntent);
            }
            else{
                sendIntent.putExtra(KEY_STATUS,0);
                sendIntent.putExtra(KEY_QUES_ANS,question);
                sendIntent.putExtra(KEY_CORRECT_ANS,correctAnswer);
                startActivity(sendIntent);
            }
        }
    }

    //This function can save value to bundle
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Question",question);
        outState.putInt("correctAnswer",correctAnswer);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        question = savedInstanceState.getString("Question");
        correctAnswer= savedInstanceState.getInt("correctAnswer");
    }
}