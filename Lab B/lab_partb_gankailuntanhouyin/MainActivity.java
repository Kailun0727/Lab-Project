package com.example.lab_partb_gankailuntanhouyin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

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

    //call menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_ordering_menu,menu);
        return true;
    }

    //toast message if selected menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_shopping:
                Toast.makeText(this,"Shopping option selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_print:
                Toast.makeText(this,"Print option selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_userProfile:
                Toast.makeText(this,"User Profile option selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_FAQ:
                Toast.makeText(this,"FAQ option selected",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}