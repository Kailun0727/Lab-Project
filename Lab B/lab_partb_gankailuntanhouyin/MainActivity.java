package com.example.lab_partb_gankailuntanhouyin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double mainTotal =0;
    private double snackTotal =0;
    private double totalAmount=0;
    private Spinner m_SpMainCourse;
    private RadioButton m_RbTakeAway;
    private TextView m_tvTotal;
    private CheckBox m_ckbSalad;
    private CheckBox m_ckbFries;
    private CheckBox m_ckbPotato;
    private CheckBox m_ckbColeslaw;
    private CheckBox m_ckbPie;
    private RadioGroup m_radioGroup;
    private RadioButton m_radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_SpMainCourse = findViewById(R.id.spMain);
        m_RbTakeAway = findViewById(R.id.rbTakeAway);
        m_tvTotal = findViewById(R.id.tvTotal);
        m_radioGroup=findViewById(R.id.rgOption);
        m_ckbSalad = findViewById(R.id.ckbSalad);
        m_ckbFries = findViewById(R.id.ckbFries);
        m_ckbPotato = findViewById(R.id.ckbPotato);
        m_ckbColeslaw = findViewById(R.id.ckbColeslaw);
        m_ckbPie = findViewById(R.id.ckbPie);


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

        m_SpMainCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //find the position of current selected item
                int pos = m_SpMainCourse.getSelectedItemPosition() + 1;
                switch (pos){
                    case 1:
                        //calculate
                        mainTotal = 7.00;
                        totalAmount = mainTotal +snackTotal;
                        m_tvTotal.setText(String.format("RM%.2f",totalAmount));
                        break;
                    case 2:
                        mainTotal = 12.00;
                        totalAmount =mainTotal +snackTotal;
                        m_tvTotal.setText(String.format("RM%.2f",totalAmount));
                        break;
                    case 3:
                        mainTotal = 8.00;
                        totalAmount = mainTotal +snackTotal;;
                        m_tvTotal.setText(String.format("RM%.2f",totalAmount));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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
                Intent i = new Intent(MainActivity.this,FAQ.class);
                startActivity(i);
                break;

        }
        return true;
    }

    public void ckbSalad_clicked(View view) {
        CheckBox c = (CheckBox) view;
        double snackPrice = 2.50;

        if (c.isChecked()){
            snackTotal +=  snackPrice;
            totalAmount  = mainTotal + snackTotal;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
        else{
            snackTotal -=  snackPrice;
            totalAmount  -= snackPrice;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
    }

    public void ckbFries_clicked(View view) {
        CheckBox c = (CheckBox) view;
        double snackPrice = 4.60;

        if (c.isChecked()){
            snackTotal +=  snackPrice;
            totalAmount  = mainTotal + snackTotal;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
        else{
            snackTotal -=  snackPrice;
            totalAmount -=  snackPrice;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }

    }

    public void ckbPotato_clicked(View view) {
        CheckBox c = (CheckBox) view;
        double snackPrice = 2.90;

        if (c.isChecked()){
            snackTotal +=  snackPrice;
            totalAmount  = mainTotal + snackTotal;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
        else{
            snackTotal -=  snackPrice;
            totalAmount -=  snackPrice;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
    }

    public void ckbColeslaw_clicked(View view) {
        CheckBox c = (CheckBox) view;
        double snackPrice = 2.60;

        if (c.isChecked()){
            snackTotal +=  snackPrice;
            totalAmount  = mainTotal + snackTotal;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
        else{
            snackTotal -=  snackPrice;
            totalAmount -=  snackPrice;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
    }

    public void ckbPie_clicked(View view) {
        CheckBox c = (CheckBox) view;
        double snackPrice = 3.50;

        if (c.isChecked()){
            snackTotal +=  snackPrice;
            totalAmount  = mainTotal + snackTotal;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
        else{
            snackTotal -=  snackPrice;
            totalAmount -=  snackPrice;
            m_tvTotal.setText(String.format("RM%.2f",totalAmount));
        }
    }

    public void btnSubmit_clicked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");


        // get selected radio button from radioGroup
        int selectedId = m_radioGroup.getCheckedRadioButtonId();
        // find the radio button by returned id
        m_radioButton = findViewById(selectedId);

        //Get all details
        String orderType =m_radioButton.getText().toString();
        String mainCourse = m_SpMainCourse.getSelectedItem().toString();

        String ckb="";
        if (m_ckbSalad.isChecked()){
            ckb+=m_ckbSalad.getText().toString()+"\n";
        }
        if(m_ckbFries.isChecked()){
            ckb+= m_ckbFries.getText().toString()+"\n";
        }
        if (m_ckbPotato.isChecked()){
            ckb += m_ckbPotato.getText().toString()+"\n";
        }
        if (m_ckbColeslaw.isChecked()){
            ckb += m_ckbColeslaw.getText().toString()+"\n";
        }
        if (m_ckbPie.isChecked()){
            ckb += m_ckbPie.getText().toString()+"\n";
        }



        String details = "Order Type : "+orderType +"\n"+"Main Course : "+mainCourse+"\n"+"Snack Option : "+"\n"+ckb+"\n"+"Total Amount : RM"+totalAmount;

        alert.setMessage(details);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after Yes clicked
                Toast.makeText(MainActivity.this,"Your order has been sent",Toast.LENGTH_SHORT).show();

            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(MainActivity.this,"Order is not confirmed",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();

    }
}