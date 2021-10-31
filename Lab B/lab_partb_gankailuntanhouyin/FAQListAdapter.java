package com.example.lab_partb_gankailuntanhouyin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class FAQListAdapter extends RecyclerView.Adapter<FAQListAdapter.FAQViewHolder>{
    private LinkedList<String> mFAQ;
    private LinkedList<String> mFAQQuestion;
    private LayoutInflater mInflater;
    private LinkedList<String> mFAQAnswer;



    FAQListAdapter(Context context, LinkedList<String> FAQ,LinkedList<String> FAQQuestion,LinkedList<String> FAQAnswer){
        this.mFAQ =FAQ;
        this.mFAQAnswer = FAQAnswer;
        this.mFAQQuestion = FAQQuestion;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mFaqView = mInflater.inflate(R.layout.faq_list_layout,parent,false);
        return new FAQViewHolder(mFaqView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        String mCurrent = mFAQ.get(position);
        holder.faqItem.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mFAQ.size();
    }


    class FAQViewHolder extends RecyclerView.ViewHolder {
        TextView faqItem;
        final FAQListAdapter mAdapter;

        public FAQViewHolder(@NonNull View itemView, FAQListAdapter adapter){
            super(itemView);
            faqItem = itemView.findViewById(R.id.faq);
            this.mAdapter = adapter;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mPosition = getLayoutPosition();
                    String quesAns = mFAQ.get(mPosition);
                    String questionOnly = mFAQQuestion.get(mPosition);
                    String answer = mFAQAnswer.get(mPosition);


                    if(!quesAns.endsWith(answer)){
                        mFAQ.set(mPosition,quesAns +"\nAnswer :"+ answer);
                    }else{
                        mFAQ.set(mPosition,questionOnly);
                    }

                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
