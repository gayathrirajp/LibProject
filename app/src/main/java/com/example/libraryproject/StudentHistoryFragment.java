package com.example.libraryproject;

import static com.example.libraryproject.StudentActivity.StudentUsn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

import adapterlayer.HistoryAdapter;
import businesslayer.StudentLayer;

public class StudentHistoryFragment extends Fragment {
    TableLayout tbl;
    RecyclerView recyclerView;
    ArrayList<String> bookName,issuedOn, returnedOn, dueDate;
    HistoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student_history, container, false);
        bookName= new ArrayList<String>();
        issuedOn= new ArrayList<String>();
        returnedOn= new ArrayList<String>();
        dueDate= new ArrayList<String>();
        recyclerView= view.findViewById(R.id.recyclerHistory1);
        adapter = new HistoryAdapter(getActivity(), bookName , issuedOn, returnedOn, dueDate);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayData();
        return view;
    }

    void displayData(){
        ResultSet s= StudentLayer.getHistory(StudentUsn);
        if(s!=null){
            try{
                while(s.next()){
                    bookName.add("Title: "+s.getString(2));
                    issuedOn.add("Issued on: "+s.getString(4));
                    returnedOn.add("Returned on: "+s.getString(7));
                    dueDate.add("Due date: "+s.getString(5));
                }

            }
            catch(Exception e){
                Toast.makeText(getActivity(), "Error occured", Toast.LENGTH_LONG).show();
            }

        }
    }
}