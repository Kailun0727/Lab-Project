package com.example.libraryapp;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookList extends AppCompatActivity {


    //Book 1
    private TextView mId;
    private TextView mTitle;
    private TextView mAuthor;
    private ImageView mImage;
    private TextView mCopies;
    private Button btn_borrow;
    private Button btn_return;
    private TextView mDate;
    private TextView mPenalty;

    //Book 2
    private TextView mId2;
    private TextView mTitle2;
    private TextView mAuthor2;
    private ImageView mImage2;
    private TextView mCopies2;
    private Button btn_borrow2;
    private Button btn_return2;
    private TextView mDate2;
    private TextView mPenalty2;

    //Book 3
    private TextView mId3;
    private TextView mTitle3;
    private TextView mAuthor3;
    private ImageView mImage3;
    private TextView mCopies3;
    private Button btn_borrow3;
    private Button btn_return3;
    private TextView mDate3;
    private TextView mPenalty3;

    //Book 4
    private TextView mId4;
    private TextView mTitle4;
    private TextView mAuthor4;
    private ImageView mImage4;
    private TextView mCopies4;
    private Button btn_borrow4;
    private Button btn_return4;
    private TextView mDate4;
    private TextView mPenalty4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mId = findViewById(R.id.id);
        mId2 = findViewById(R.id.id2);
        mId3 = findViewById(R.id.id3);
        mId4 = findViewById(R.id.id4);


        mTitle = findViewById(R.id.title);
        mTitle2 = findViewById(R.id.title2);
        mTitle3 = findViewById(R.id.title3);
        mTitle4 = findViewById(R.id.title4);


        mAuthor = findViewById(R.id.author);
        mAuthor2 = findViewById(R.id.author2);
        mAuthor3 = findViewById(R.id.author3);
        mAuthor4 = findViewById(R.id.author4);


        mImage = findViewById(R.id.iv_book);
        mImage2 = findViewById(R.id.iv_book2);
        mImage3 = findViewById(R.id.iv_book3);
        mImage4 = findViewById(R.id.iv_book4);


        mCopies = findViewById(R.id.numberCopies);
        mCopies2 = findViewById(R.id.numberCopies2);
        mCopies3 = findViewById(R.id.numberCopies3);
        mCopies4 = findViewById(R.id.numberCopies4);

        mDate = findViewById(R.id.date);
        mDate2 = findViewById(R.id.date2);
        mDate3 = findViewById(R.id.date3);
        mDate4 = findViewById(R.id.date4);


        btn_borrow = findViewById(R.id.btn_borrow);
        btn_borrow2 = findViewById(R.id.btn_borrow2);
        btn_borrow3 = findViewById(R.id.btn_borrow3);
        btn_borrow4 = findViewById(R.id.btn_borrow4);


        btn_return = findViewById(R.id.btn_return);
        btn_return2 = findViewById(R.id.btn_return2);
        btn_return3 = findViewById(R.id.btn_return3);
        btn_return4 = findViewById(R.id.btn_return4);

        mPenalty = findViewById(R.id.penalty);
        mPenalty2 = findViewById(R.id.penalty2);
        mPenalty3 = findViewById(R.id.penalty3);
        mPenalty4 = findViewById(R.id.penalty4);


        try {
            initialize();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //This is for book 1 button, copy code below and paste it into book 2, 3 and 4,
    // remember change 1 -> 2 for book 2, change 1 -> for book 3, change 1 -> 4 for book 4
    public void btnBorrow_clicked(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");
        alert.setMessage("Are you sure want to borrow?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Intent userDetail =getIntent();
                String username=userDetail.getStringExtra("username");
                //Execute after Yes clicked

                //Calculate borrow date and return date
                final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date dateOfBorrow = new Date();
                int noOfDays = 7; // 1 week

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateOfBorrow);

                String strBorrowDate =  new SimpleDateFormat("yyyy/MM/dd").format(dateOfBorrow);

                //Store borrow date to shared preferences
                editor.putString(username+"borrowDate1",strBorrowDate);

                calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
                Date returnDate = calendar.getTime();

                Date currentDate= new Date();
                String strReturnDate = new SimpleDateFormat("yyyy/MM/dd").format(returnDate);

                //Store return date to shared preferences
                editor.putString(username+"returnDate1",strReturnDate);

                String details = "Borrow Date :"+strBorrowDate+"\nReturn Date :"+strReturnDate;

                mDate.setVisibility(View.VISIBLE);
                mDate.setText(details);

                editor.putString(username+"details1",details);

                String numberCopies = preferences.getString("book1Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow -= 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book1Copies", numberCopiesAfter);

                mCopies.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked1",true);
                editor.putBoolean(username+"returnClicked1",false);

                editor.commit();

                btn_borrow.setEnabled(false);
                btn_return.setEnabled(true);

                //Toast.makeText(MainActivity.this,"Order submitted",Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    //This is for book 1 button, copy code below and paste it into book 2, 3 and 4,
    // remember change 1 -> 2 for book 2, change 1 -> for book 3, change 1 -> 4 for book 4
    public void btnReturn_clicked(View view) throws ParseException {
        Intent userDetail =getIntent();
        String username=userDetail.getStringExtra("username");

        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");

        String returnDate = preferences.getString(username+"returnDate1",null);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date returnDate1 = (Date)dateFormat.parse(returnDate);


        //Convert date
        Date currentDate= new Date();
        String fDate = dateFormat.format(currentDate);
        Date currentDate1 = (Date)dateFormat.parse(fDate);

        //Calculate the days between two date
        long diff = currentDate1.getTime() - returnDate1.getTime();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        //Convert float to int
        int dayCountInt = (int)dayCount;
        if(dayCountInt > 0 ){
            int penalty =  dayCountInt * 2;
            alert.setMessage("Are you sure want to return?\nPenalty : RM"+penalty);
        }else{
            String str_day = String.valueOf(dayCount);
            alert.setMessage("Are you sure want to return?");
        }

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after Yes clicked



                mDate.setVisibility(View.GONE);
                String numberCopies = preferences.getString("book1Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow += 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book1Copies", numberCopiesAfter);

                mCopies.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked1",false);
                editor.putBoolean(username+"returnClicked1",true);
                editor.commit();

                btn_borrow.setEnabled(true);
                btn_return.setEnabled(false);
                mPenalty.setVisibility(View.GONE);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();





    }

    //Book 2 button
    //Paste to here
    public void btnBorrow_clicked2(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");
        alert.setMessage("Are you sure want to borrow?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Intent userDetail =getIntent();
                String username=userDetail.getStringExtra("username");
                //Execute after Yes clicked

                //Calculate borrow date and return date
                final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date dateOfBorrow = new Date();
                int noOfDays = 7; // 1 week

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateOfBorrow);

                String strBorrowDate =  new SimpleDateFormat("yyyy/MM/dd").format(dateOfBorrow);


                //Store borrow date to shared preferences
                editor.putString(username+"borrowDate2",strBorrowDate);

                calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
                Date returnDate = calendar.getTime();

                Date currentDate= new Date();
                String strReturnDate = new SimpleDateFormat("yyyy/MM/dd").format(returnDate);

                //Store return date to shared preferences
                editor.putString(username+"returnDate2",strReturnDate);

                String details = "Borrow Date :"+strBorrowDate+"\nReturn Date :"+strReturnDate;

                mDate2.setVisibility(View.VISIBLE);
                mDate2.setText(details);

                editor.putString(username+"details2",details);

                String numberCopies = preferences.getString("book2Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow -= 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book2Copies", numberCopiesAfter);

                mCopies2.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked2",true);
                editor.putBoolean(username+"returnClicked2",false);

                editor.commit();

                btn_borrow2.setEnabled(false);
                btn_return2.setEnabled(true);

                //Toast.makeText(MainActivity.this,"Order submitted",Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    //Book 2 button
    //Paste to here
    public void btnReturn_clicked2(View view) throws ParseException {
        Intent userDetail =getIntent();
        String username=userDetail.getStringExtra("username");

        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");


        String returnDate = preferences.getString(username+"returnDate2",null);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date returnDate2 = (Date)dateFormat.parse(returnDate);

        //Convert date
        Date currentDate= new Date();
        String fDate = dateFormat.format(currentDate);
        Date currentDate2 = (Date)dateFormat.parse(fDate);

        //Calculate the days between two date
        long diff = currentDate2.getTime() - returnDate2.getTime();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        //Convert float to int
        int dayCountInt = (int)dayCount;
        if(dayCountInt > 0 ){
            int penalty =  dayCountInt * 2;
            alert.setMessage("Are you sure want to return?\nPenalty : RM"+penalty);
        }else{
            String str_day = String.valueOf(dayCount);
            mDate.setText("Are you sure want to return?");
        }

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after Yes clicked
                mDate2.setVisibility(View.GONE);
                String numberCopies = preferences.getString("book2Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow += 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book2Copies", numberCopiesAfter);

                mCopies2.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked2",false);
                editor.putBoolean(username+"returnClicked2",true);
                editor.commit();

                btn_borrow2.setEnabled(true);
                btn_return2.setEnabled(false);
                mPenalty2.setVisibility(View.GONE);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();





    }

    //Book 3 button
    //Paste to here
    public void btnBorrow_clicked3(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");
        alert.setMessage("Are you sure want to borrow?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Intent userDetail =getIntent();
                String username=userDetail.getStringExtra("username");
                //Execute after Yes clicked

                //Calculate borrow date and return date
                final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date dateOfBorrow = new Date();
                int noOfDays = 7; // 1 week

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateOfBorrow);

                String strBorrowDate =  new SimpleDateFormat("yyyy/MM/dd").format(dateOfBorrow);

                //Store borrow date to shared preferences
                editor.putString(username+"borrowDate3",strBorrowDate);

                calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
                Date returnDate = calendar.getTime();

                Date currentDate= new Date();
                String strReturnDate = new SimpleDateFormat("yyyy/MM/dd").format(returnDate);

                //Store return date to shared preferences
                editor.putString(username+"returnDate3",strReturnDate);

                String details = "Borrow Date :"+strBorrowDate+"\nReturn Date :"+strReturnDate;

                mDate3.setVisibility(View.VISIBLE);
                mDate3.setText(details);

                editor.putString(username+"details3",details);

                String numberCopies = preferences.getString("book3Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow -= 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book3Copies", numberCopiesAfter);

                mCopies3.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked3",true);
                editor.putBoolean(username+"returnClicked3",false);

                editor.commit();

                btn_borrow3.setEnabled(false);
                btn_return3.setEnabled(true);

                //Toast.makeText(MainActivity.this,"Order submitted",Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    //Book 3 button
    //Paste to here
    public void btnReturn_clicked3(View view) throws ParseException {
        Intent userDetail =getIntent();
        String username=userDetail.getStringExtra("username");

        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");


        String returnDate = preferences.getString(username+"returnDate3",null);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date returnDate3 = (Date)dateFormat.parse(returnDate);

        //Convert date
        Date currentDate= new Date();
        String fDate = dateFormat.format(currentDate);
        Date currentDate3 = (Date)dateFormat.parse(fDate);

        //Calculate the days between two date
        long diff = currentDate3.getTime() - returnDate3.getTime();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        //Convert float to int
        int dayCountInt = (int)dayCount;
        if(dayCountInt > 0 ){
            int penalty =  dayCountInt * 2;
            alert.setMessage("Are you sure want to return?\nPenalty : RM"+penalty);
        }else{
            String str_day = String.valueOf(dayCount);
            mDate.setText("Are you sure want to return?");
        }


        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after Yes clicked
                mDate3.setVisibility(View.GONE);
                String numberCopies = preferences.getString("book3Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow += 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book3Copies", numberCopiesAfter);

                mCopies3.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked3",false);
                editor.putBoolean(username+"returnClicked3",true);
                editor.commit();

                btn_borrow3.setEnabled(true);
                btn_return3.setEnabled(false);
                mPenalty3.setVisibility(View.GONE);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();





    }

    //Book 4 button
    //Paste to here
    public void btnBorrow_clicked4(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");
        alert.setMessage("Are you sure want to borrow?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Intent userDetail =getIntent();
                String username=userDetail.getStringExtra("username");
                //Execute after Yes clicked

                //Calculate borrow date and return date
                final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date dateOfBorrow = new Date();
                int noOfDays = 7; // 1 week

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateOfBorrow);

                String strBorrowDate =  new SimpleDateFormat("yyyy/MM/dd").format(dateOfBorrow);

                //Store borrow date to shared preferences
                editor.putString(username+"borrowDate4",strBorrowDate);

                calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
                Date returnDate = calendar.getTime();

                Date currentDate= new Date();
                String strReturnDate = new SimpleDateFormat("yyyy/MM/dd").format(returnDate);


                //Store return date to shared preferences
                editor.putString(username+"returnDate4",strReturnDate);

                String details = "Borrow Date :"+strBorrowDate+"\nReturn Date :"+strReturnDate;

                mDate4.setVisibility(View.VISIBLE);
                mDate4.setText(details);

                editor.putString(username+"details4",details);

                String numberCopies = preferences.getString("book4Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow -= 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book4Copies", numberCopiesAfter);

                mCopies3.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked4",true);
                editor.putBoolean(username+"returnClicked4",false);

                editor.commit();

                btn_borrow4.setEnabled(false);
                btn_return4.setEnabled(true);

                //Toast.makeText(MainActivity.this,"Order submitted",Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    //Book 4 button
    //Paste to here
    public void btnReturn_clicked4(View view) throws ParseException {
        Intent userDetail =getIntent();
        String username=userDetail.getStringExtra("username");

        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation");

        String returnDate = preferences.getString(username+"returnDate4",null);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date returnDate4 = (Date)dateFormat.parse(returnDate);

        //Convert date
        Date currentDate= new Date();
        String fDate = dateFormat.format(currentDate);
        Date currentDate4 = (Date)dateFormat.parse(fDate);

        //Calculate the days between two date
        long diff = currentDate4.getTime() - returnDate4.getTime();
        float dayCount = (float) diff / (24 * 60 * 60 * 1000);
        //Convert float to int
        int dayCountInt = (int)dayCount;
        if(dayCountInt > 0 ){
            int penalty =  dayCountInt * 2;
            alert.setMessage("Are you sure want to return?\nPenalty : RM"+penalty);
        }else{
            String str_day = String.valueOf(dayCount);
            mDate.setText("Are you sure want to return?");
        }

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after Yes clicked
                mDate4.setVisibility(View.GONE);
                String numberCopies = preferences.getString("book4Copies",null);
                int numberCopiesAfterBorrow = Integer.parseInt(numberCopies);
                numberCopiesAfterBorrow += 1;

                String numberCopiesAfter = Integer.toString(numberCopiesAfterBorrow);

                editor.putString("book4Copies", numberCopiesAfter);

                mCopies4.setText("Copies :"+numberCopiesAfter);

                editor.putBoolean(username+"borrowClicked4",false);
                editor.putBoolean(username+"returnClicked4",true);
                editor.commit();

                btn_borrow4.setEnabled(true);
                btn_return4.setEnabled(false);
                mPenalty4.setVisibility(View.GONE);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Execute after No clicked
                Toast.makeText(BookList.this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();





    }



    public void initialize() throws ParseException {
        Intent userDetail =getIntent();
        String username = userDetail.getStringExtra("username");
        SharedPreferences preferences = getSharedPreferences("spLibrary", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        mId.setText("Id :"+"1");
        mId2.setText("Id :"+"2");
        mId3.setText("Id :"+"3");
        mId4.setText("Id :"+"4");

        mTitle.setText("Title :"+"To Kill a Mockingbird");
        mTitle2.setText("Title :"+"The Backwoods Mist");
        mTitle3.setText("Title :"+"When the Stars Align");
        mTitle4.setText("Title :"+"The Lone Planet");

        mAuthor.setText("Author :"+"Harper Lee");
        mAuthor2.setText("Author :"+"J.Patterson");
        mAuthor3.setText("Author :"+"Eleanor Fitzgerald");
        mAuthor4.setText("Author :"+"Paul Parker");

        //Book 1
        if(preferences.contains("book1Copies")){
            String book1Copies = preferences.getString("book1Copies",null);
            mCopies.setText("Copies :"+book1Copies);

            //if copies == 0 , not available for user to borrow
            if(book1Copies.equals("0")){
                btn_borrow.setEnabled(false);
            }
        }
        else{
            mCopies.setText("Copies : 1");
            editor.putString("book1Copies","1");
            editor.commit();
        }

        //Book 2
        if(preferences.contains("book2Copies")){
            String book2Copies = preferences.getString("book2Copies",null);
            mCopies2.setText("Copies :"+book2Copies);
            //if copies == 0 , not available for user to borrow
            if(book2Copies.equals("0")){
                btn_borrow2.setEnabled(false);
            }
        }
        else{
            mCopies2.setText("Copies : 13");
            editor.putString("book2Copies","13");
            editor.commit();
        }

        //Book 3
        if(preferences.contains("book3Copies")){
            String book3Copies = preferences.getString("book3Copies",null);
            mCopies3.setText("Copies :"+book3Copies);
            //if copies == 0 , not available for user to borrow
            if(book3Copies.equals("0")){
                btn_borrow3.setEnabled(false);
            }
        }
        else{
            mCopies3.setText("Copies : 9");
            editor.putString("book3Copies","9");
            editor.commit();
        }

        //Book 4
        if(preferences.contains("book4Copies")){
            String book4Copies = preferences.getString("book4Copies",null);
            mCopies4.setText("Copies :"+book4Copies);
            //if copies == 0 , not available for user to borrow
            if(book4Copies.equals("0")){
                btn_borrow4.setEnabled(false);
            }
        }
        else{
            mCopies4.setText("Copies : 3");
            editor.putString("book4Copies","3");
            editor.commit();
        }


        //Check book is borrowed by user or not
        //Book 1
        if(preferences.contains(username+"borrowClicked1")){
            boolean borrowClicked1 = preferences.getBoolean(username+"borrowClicked1",false);
            boolean returnClicked1 = preferences.getBoolean(username+"returnClicked1",true);

            if(borrowClicked1 == false && returnClicked1 == true){
                btn_borrow.setEnabled(true);
                btn_return.setEnabled(false);
            }
            if(borrowClicked1 ==true && returnClicked1 == false){
                btn_borrow.setEnabled(false);
                btn_return.setEnabled(true);
            }
        }

        //Book 2
        if(preferences.contains(username+"borrowClicked2")){
            boolean borrowClicked2 = preferences.getBoolean(username+"borrowClicked2",false);
            boolean returnClicked2 = preferences.getBoolean(username+"returnClicked2",true);

            if(borrowClicked2 == false && returnClicked2 == true){
                btn_borrow2.setEnabled(true);
                btn_return2.setEnabled(false);
            }
            if(borrowClicked2 ==true && returnClicked2 == false){
                btn_borrow2.setEnabled(false);
                btn_return2.setEnabled(true);
            }
        }

        //Book 3
        if(preferences.contains(username+"borrowClicked3")){
            boolean borrowClicked3 = preferences.getBoolean(username+"borrowClicked3",false);
            boolean returnClicked3 = preferences.getBoolean(username+"returnClicked3",true);

            if(borrowClicked3 == false && returnClicked3 == true){
                btn_borrow3.setEnabled(true);
                btn_return3.setEnabled(false);
            }
            if(borrowClicked3 ==true && returnClicked3 == false){
                btn_borrow3.setEnabled(false);
                btn_return3.setEnabled(true);
            }
        }

        //Book 4
        if(preferences.contains(username+"borrowClicked4")){
            boolean borrowClicked4 = preferences.getBoolean(username+"borrowClicked4",false);
            boolean returnClicked4 = preferences.getBoolean(username+"returnClicked4",true);

            if(borrowClicked4 == false && returnClicked4 == true){
                btn_borrow4.setEnabled(true);
                btn_return4.setEnabled(false);
            }
            if(borrowClicked4 ==true && returnClicked4 == false){
                btn_borrow4.setEnabled(false);
                btn_return4.setEnabled(true);
            }
        }




        //Book 1 borrow date and return date details
        //Copy this part and change (Example : borrowClicked1 -> borrowClicked2 for book 2 ),
        //everything related to 1 change 2 for book 2, change 1 to 3 for book 3 , change 1 to 4 for book 4
        boolean borrowClicked1 = preferences.getBoolean(username+"borrowClicked1",true);
        boolean returnClicked1 = preferences.getBoolean(username+"returnClicked1",false);
        if( borrowClicked1 ==true && returnClicked1 ==false){

            //Set text view become visible
            String details1 = preferences.getString(username+"details1",null);
            mDate.setVisibility(View.VISIBLE);
            mDate.setText(details1);

            String returnDate = preferences.getString(username+"returnDate1",null);
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date returnDate1 = (Date)dateFormat.parse(returnDate);

            //Convert date
            Date currentDate= new Date();
            String fDate = dateFormat.format(currentDate);
            Date currentDate1 = (Date)dateFormat.parse(fDate);

            long diff = currentDate1.getTime() - returnDate1.getTime();

            float dayCount = (float) diff / (24 * 60 * 60 * 1000);
            int dayCountInt = (int)dayCount;
            if(dayCountInt > 0 ){
                int penalty =  dayCountInt * 2;

                mDate.setText(details1);
                mPenalty.setVisibility(View.VISIBLE);
                mPenalty.setText("Penalty : RM"+penalty);

            }else{
                mPenalty.setVisibility(View.GONE);
                mDate.setText(details1);
            }


        }


        //here for book 2
        boolean borrowClicked2 = preferences.getBoolean(username+"borrowClicked2",true);
        boolean returnClicked2 = preferences.getBoolean(username+"returnClicked2",false);
        if( borrowClicked2 ==true && returnClicked2 ==false){

            //Set text view become visible
            String details2 = preferences.getString(username+"details2",null);
            mDate2.setVisibility(View.VISIBLE);
            mDate2.setText(details2);

            String returnDate = preferences.getString(username+"returnDate2",null);
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date returnDate2 = (Date)dateFormat.parse(returnDate);

            //Convert date
            Date currentDate= new Date();
            String fDate = dateFormat.format(currentDate);
            Date currentDate2 = (Date)dateFormat.parse(fDate);

            long diff = currentDate2.getTime() - returnDate2.getTime();

            float dayCount = (float) diff / (24 * 60 * 60 * 1000);
            int dayCountInt = (int)dayCount;
            if(dayCountInt > 0 ){
                int penalty =  dayCountInt * 2;
                mDate2.setText(details2);
                mPenalty2.setVisibility(View.VISIBLE);
                mPenalty2.setText("Penalty : RM"+penalty);

            }else{
                mPenalty2.setVisibility(View.GONE);
                mDate2.setText(details2);
            }


        }


        //here for book 3
        boolean borrowClicked3 = preferences.getBoolean(username+"borrowClicked3",true);
        boolean returnClicked3 = preferences.getBoolean(username+"returnClicked3",false);
        if( borrowClicked3 ==true && returnClicked3 ==false){

            //Set text view become visible
            String details3 = preferences.getString(username+"details3",null);
            mDate3.setVisibility(View.VISIBLE);
            mDate3.setText(details3);

            String returnDate = preferences.getString(username+"returnDate3",null);
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date returnDate3 = (Date)dateFormat.parse(returnDate);

            //Convert date
            Date currentDate= new Date();
            String fDate = dateFormat.format(currentDate);
            Date currentDate3 = (Date)dateFormat.parse(fDate);

            long diff = currentDate3.getTime() - returnDate3.getTime();

            float dayCount = (float) diff / (24 * 60 * 60 * 1000);
            int dayCountInt = (int)dayCount;
            if(dayCountInt > 0 ){
                int penalty =  dayCountInt * 2;
                mDate2.setText(details3);
                mPenalty3.setVisibility(View.VISIBLE);
                mPenalty3.setText("Penalty : RM"+penalty);

            }else{
                mPenalty3.setVisibility(View.GONE);
                mDate3.setText(details3);
            }

        }

        //here for book 4
        boolean borrowClicked4 = preferences.getBoolean(username+"borrowClicked4",true);
        boolean returnClicked4 = preferences.getBoolean(username+"returnClicked4",false);
        if( borrowClicked4 ==true && returnClicked4 ==false){

            //Set text view become visible
            String details4 = preferences.getString(username+"details4",null);
            mDate4.setVisibility(View.VISIBLE);
            mDate4.setText(details4);


            String returnDate = preferences.getString(username+"returnDate4",null);
            final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date returnDate4 = (Date)dateFormat.parse(returnDate);

            //Convert date
            Date currentDate= new Date();
            String fDate = dateFormat.format(currentDate);
            Date currentDate4 = (Date)dateFormat.parse(fDate);

            long diff = currentDate4.getTime() - returnDate4.getTime();

            float dayCount = (float) diff / (24 * 60 * 60 * 1000);
            int dayCountInt = (int)dayCount;
            if(dayCountInt > 0 ){
                int penalty =  dayCountInt * 2;
                mDate4.setText(details4);
                mPenalty4.setVisibility(View.VISIBLE);
                mPenalty4.setText("Penalty : RM"+penalty);

            }else{
                mPenalty4.setVisibility(View.GONE);
                mDate4.setText(details4);
            }
        }







    }


}
