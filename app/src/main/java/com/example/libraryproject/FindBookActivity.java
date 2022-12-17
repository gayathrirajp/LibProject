package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import java.util.LinkedList;

import businesslayer.StudentLayer;

public class FindBookActivity extends AppCompatActivity {

    ListView listBranches;
    ArrayAdapter<String> adp;
    ArrayList<String> arr;
    TableLayout tbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_book);

        listBranches=findViewById(R.id.listBranches);
        tbl= findViewById(R.id.tblBooks);

        String str="AIML CSE ISE EC EEE MECH BT MCA";
        arr= new ArrayList<String>(Arrays.asList(str.split(" ")));

        adp=new ArrayAdapter<String>(FindBookActivity.this, android.R.layout.select_dialog_item, arr);
        listBranches.setAdapter(adp);
        listBranches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info= ((TextView)view).getText().toString();
                TextView txt= findViewById(R.id.txtView);
                txt.setText(info);
                getBooks(info);
            }
        });

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
                    tbl.addView(tr);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}