package com.example.lab_partb_gankailuntanhouyin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner m_SpMainCourse;
    private RadioButton m_RbTakeAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_SpMainCourse = findViewById(R.id.spMain);
        m_RbTakeAway = findViewById(R.id.rbTakeAway);

        //spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.mainCourse,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_SpMainCourse.setAdapter(adapter);

        //set take away button active
        m_RbTakeAway.setChecked(true);

    }
}