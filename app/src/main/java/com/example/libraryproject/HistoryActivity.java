package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

import businesslayer.HistoryAdapter;
import businesslayer.StudentLayer;

public class HistoryActivity extends AppCompatActivity {

    TableLayout tbl;
    String StudentUsn;
    RecyclerView recyclerView;
    ArrayList<String> bookName,issuedOn, returnedOn, dueDate;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Referencing TableLayout
        //tbl=findViewById(R.id.tblHistory);

        //Calling method to display Student's transaction history
        //setTableContents();

        bookName= new ArrayList<String>();
        issuedOn= new ArrayList<String>();
        returnedOn= new ArrayList<String>();
        dueDate= new ArrayList<String>();
        recyclerView= findViewById(R.id.recyclerHistory);
        adapter = new HistoryAdapter(HistoryActivity.this, bookName , issuedOn, returnedOn, dueDate);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    //Method to extract transaction history of a student and setting it on the TableLayout
    void displayData(){
        Intent i1=getIntent();
        StudentUsn=i1.getStringExtra("usn");
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
                Toast.makeText(HistoryActivity.this, "Error occured", Toast.LENGTH_LONG).show();
            }

        }
    }
}