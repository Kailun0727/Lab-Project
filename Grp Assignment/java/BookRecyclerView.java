package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BookRecyclerView extends AppCompatActivity {
    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Book> mBookList;
    private BookListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_recycler_view);

        //Initialize the RecyclerView
        mRecyclerView = findViewById(R.id.rv_bookList);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize the ArrayLIst that will contain the data
        mBookList = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new BookListAdapter(this, mBookList);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
                )
                {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {

                        int from = viewHolder.getAdapterPosition();
                        int to = target.getAdapterPosition();

                        Collections.swap(mBookList,from,to);
                        mAdapter.notifyItemMoved(from,to);

                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        mBookList.remove(viewHolder.getAdapterPosition());
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                }
        );
        helper.attachToRecyclerView(mRecyclerView);

    }

    /**
     * Method for initializing the sports data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        String[] bookId = getResources().getStringArray(R.array.book_id);
        String[] bookTitle = getResources().getStringArray(R.array.book_titles);
        String[] bookAuthor = getResources().getStringArray(R.array.book_author);
        TypedArray bookImage = getResources().obtainTypedArray(R.array.book_images);
        String[] bookNumberCopies = getResources().getStringArray(R.array.book_numberCopies);


        //Clear the existing data (to avoid duplication)
        mBookList.clear();

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for(int i=0;i<bookId.length;i++){
            mBookList.add(new Book(bookId[i],
                    bookTitle[i],
                    bookAuthor[i],
                    bookImage.getResourceId(i,0),
                    bookNumberCopies[i]
            ));
        }

        bookImage.recycle();

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();


    }
}