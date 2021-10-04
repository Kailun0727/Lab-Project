package com.example.lab2_gankailuntanhouyin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private final static String KEY_STATUS="com.example.lab2_gankailuntanhouyin.status";
    private final static String KEY_QUES_ANS="com.example.lab2_gankailuntanhouyin.ques_ans";
    private final static String KEY_REPLY="com.example.lab2_gankailuntanhouyin.reply";

    private TextView m_tv_message;
    private TextView m_tv_showQnA;
    private Button m_btnSend;
    private int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        m_tv_message = findViewById(R.id.tv_message);
        m_tv_showQnA = findViewById(R.id.tv_showQnA);
        m_btnSend = findViewById(R.id.btnSend);

        Intent receiveIntent = getIntent();

        status = receiveIntent.getIntExtra(KEY_STATUS, 0);
        //check status
        if(status == 0){
            m_tv_message.setText("Incorrect");
        }
        else {
            m_tv_message.setText("Correct, good job!");
        }

        //show the question and answer
        m_tv_showQnA.setText(receiveIntent.getStringExtra(KEY_QUES_ANS));

    }

    public void btnSend_clicked(View view) {
        //check status
        if(status == 0){
            m_tv_message.setText("Incorrect");
        }
        else {
            m_tv_message.setText("Correct, good job!");

            //disable send button
            m_btnSend.setClickable(false);
        }

        //set recipient to myself
        String recipient = "standard66m@gmail.com";

        //set subject of email to ans
        String subject = "Quiz Answer";
        String body = m_tv_showQnA.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        //ensure only email apps handle this
        intent.setData(Uri.parse("mailto:"));

        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void btnClose_clicked(View view) {
        finish();
    }
}