package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import businesslayer.FindBookAdapter;
import businesslayer.StudentLayer;

public class FindBookActivity extends AppCompatActivity {

    ListView listBranches;
    ArrayAdapter<String> adp;
    ArrayList<String> arr, bookName, author, copiesAvailable;
    //TableLayout tbl;
    FindBookAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_book);

        listBranches=findViewById(R.id.listBranches);
        //tbl= findViewById(R.id.tblBooks);

        String str="AIML CSE ISE EC EEE MECH BT MCA";
        arr= new ArrayList<String>(Arrays.asList(str.split(" ")));

        adp=new ArrayAdapter<String>(FindBookActivity.this, android.R.layout.select_dialog_item, arr);
        listBranches.setAdapter(adp);
        listBranches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info= ((TextView)view).getText().toString();
                /*TextView txt= findViewById(R.id.txtView);
                txt.setText(info);*/
                //getBooks(info);
                bookName= new ArrayList<String>();
                author= new ArrayList<String>();
                copiesAvailable= new ArrayList<String>();
                recyclerView= findViewById(R.id.recyclerView);
                adapter = new FindBookAdapter(FindBookActivity.this, bookName , author, copiesAvailable);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(FindBookActivity.this));
                displayData(info);

            }
        });
    }

    void displayData(String info){
        ResultSet rSet=StudentLayer.getBooksOfBranch(info);
        if(rSet!= null){
            try{
                while(rSet.next()){
                    bookName.add("Title: "+rSet.getString(1));
                    author.add("Author: "+rSet.getString(2));
                    copiesAvailable.add("Copies Available: "+rSet.getString(3));
                }
            }catch (Exception e){
                Log.e("Error", e.getMessage());
            }
        }
    }

    void getBooks(String branchId){
        ResultSet s= StudentLayer.getBooksOfBranch(branchId);
        if(s != null){
            try {
                while(s.next()){
                    Log.i("someTag:  ", s.getString(2));
                    TableRow tr= new TableRow(this);
                    TextView bookName=new TextView(this);
                    TextView Author=new TextView(this);
                    TextView copiesAvailable=new TextView(this);
                    /*TextView due=new TextView(this);TextView retTo=new TextView(this);
                    TextView retOn=new TextView(this);*/
                    bookName.setText(s.getString(1)); Author.setText(s.getString(2));
                    copiesAvailable.setText(s.getString(3));
                    /*due.setText(s.getString(5)); retTo.setText(s.getString(6));
                    retOn.setText(s.getString(7));*/
                    tr.addView(bookName); tr.addView(Author);
                    tr.addView(copiesAvailable);
                    /*tr.addView(due); tr.addView(retTo);
                    tr.addView(retOn);*/
                    //tbl.addView(tr);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}