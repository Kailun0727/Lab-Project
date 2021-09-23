package com.example.exercisea1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button m_submit;
    private ImageView img_emoji;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_submit = findViewById(R.id.btnSubmit);
        img_emoji = findViewById(R.id.img_emoji);
        txt = findViewById(R.id.tv_status);


    }

    public void btnSubmit_onClick(View view) {
        String text = (String) txt.getText();

        //change to happy emoji
        img_emoji.setImageResource(R.drawable.happy);

        //change the text
        txt.setText("I'm so full");

        //change button text
        m_submit.setText("Done");

        if(text == txt.getText().toString()){
            finish();
        }

    }
}