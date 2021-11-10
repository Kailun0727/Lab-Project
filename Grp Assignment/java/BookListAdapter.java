package com.example.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder>{
    private ArrayList<Book> mBookList;
    private LayoutInflater mInflater;
    private Context mContext;

    BookListAdapter(Context context, ArrayList<Book> bookList){
        this.mBookList = bookList;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(mContext).inflate(R.layout.booklist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        //Get current sport
        Book currentBook = mBookList.get(position);
        //Populate the textviews with data
        holder.bindTo(currentBook);

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }


    class BookViewHolder extends RecyclerView.ViewHolder{

        //Member Variables for the TextViews
        private TextView mIdText;
        private TextView mTitleText;
        private TextView mAuthorText;
        private ImageView mBookImage;
        private TextView mNumberCopies;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        BookViewHolder(View itemView) {
            super(itemView);

            //Initialize the views
            mIdText = (TextView)itemView.findViewById(R.id.id);
            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mAuthorText = (TextView)itemView.findViewById(R.id.author);
            mBookImage = itemView.findViewById(R.id.iv_book);
            mNumberCopies = (TextView)itemView.findViewById(R.id.numberCopies);

        }

        void bindTo(Book currentBook){
            //Populate the text views with data
            mIdText.setText("ID : "+currentBook.getId());
            mTitleText.setText("Title : "+currentBook.getTitle());
            mAuthorText.setText("Author : "+currentBook.getAuthor());
            Glide.with(mContext).load(currentBook.getImageResource()).into(mBookImage);
            mNumberCopies.setText("Number Copies : "+currentBook.getNumberCopies());

        }


    }

}
