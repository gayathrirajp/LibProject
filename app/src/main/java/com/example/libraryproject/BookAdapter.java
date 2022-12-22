package com.example.libraryproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder1> {
    private Context context;
    private ArrayList bookName, issuedOn, issuedBy;

    public BookAdapter(Context context, ArrayList bookName, ArrayList issuedOn, ArrayList issuedBy) {
        this.context = context;
        this.bookName = bookName;
        this.issuedOn = issuedOn;
        this.issuedBy = issuedBy;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where we inflate the layout (giving a look to our rows)
        View v = LayoutInflater.from(context).inflate(R.layout.book_entries, parent, false);
        return new MyViewHolder1(v);

    }




    @Override
    public void onBindViewHolder(@NonNull BookAdapter.MyViewHolder1 holder, int position) {
        //assigning values to the views we  created in the recycler view layout(history_entries)
        //based on the position of recycler view
        holder.tv_bookName.setText(String.valueOf(bookName.get(position)));
        holder.tv_issuedOn.setText(String.valueOf(issuedOn.get(position)));
        holder.tv_issuedBy.setText(String.valueOf(issuedBy.get(position)));
    }


    @Override
    public int getItemCount() {
        //number of items in total
        return bookName.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView tv_bookName, tv_issuedOn, tv_issuedBy;


        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            tv_bookName = itemView.findViewById(R.id.tv_bookName);
            tv_issuedOn = itemView.findViewById(R.id.tv_issuedOn);
            tv_issuedBy = itemView.findViewById(R.id.tv_IssuedBy);
        }
    }
}
