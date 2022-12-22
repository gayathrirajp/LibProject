package com.example.libraryproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private Context context;
    ArrayList usn,name,bookname,staffname;

    public HistoryAdapter(Context context, ArrayList usn, ArrayList name, ArrayList bookname, ArrayList staffname) {
        this.context = context;
        this.usn=usn;
        this.name=name;
        this.bookname=bookname;
        this.staffname=staffname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where we inflate the layout (giving a look to our rows)
        View v= LayoutInflater.from(context).inflate(R.layout.history_entries,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we  created in the recycler view layout(history_entries)
        //based on the position of recycler view
        holder.tv_USN.setText(String.valueOf(usn.get(position)));
        holder.tv_Name.setText(String.valueOf(name.get(position)));
        holder.tv_Book.setText(String.valueOf(bookname.get(position)));
        holder.tv_Staff.setText(String.valueOf(staffname.get(position)));
    }

    @Override
    public int getItemCount() {

        //number of items in total
        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_USN,tv_Name,tv_Book,tv_Staff;
        //grabbing the views from our layout file. it is like the onCreate method
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_USN=itemView.findViewById(R.id.tv_USN);
            tv_Name=itemView.findViewById(R.id.tv_Name);
            tv_Book=itemView.findViewById(R.id.tv_Book);
            tv_Staff=itemView.findViewById(R.id.tv_Staff);
        }
    }
}
