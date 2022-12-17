package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

import businesslayer.StudentLayer;

public class HistoryActivity extends AppCompatActivity {

    TableLayout tbl;
    String StudentUsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Referencing TableLyout
        tbl=findViewById(R.id.tblHistory);

        //Calling method to display Student's transaction history
        setTableContents();
    }

    //Method to extract transaction history of a student and setting it on the TableLayout
    void setTableContents(){
        Intent i1=getIntent();
        StudentUsn=i1.getStringExtra("usn");
        ResultSet s= StudentLayer.getHistory(StudentUsn);
        if(s!=null){
            try{
                String k="Semiconductors";
                while(s.next()){
                    Log.i("someTag:  ", s.getString(2));
                    TableRow tr= new TableRow(this);
                    TextView bId=new TextView(this);TextView bName=new TextView(this);
                    TextView isBy=new TextView(this); TextView isOn=new TextView(this);
                    /*TextView due=new TextView(this);TextView retTo=new TextView(this);
                    TextView retOn=new TextView(this);*/
                    bId.setText(s.getString(1)); bName.setText(k);
                    isBy.setText(s.getString(3)); isOn.setText(s.getString(4));
                    /*due.setText(s.getString(5)); retTo.setText(s.getString(6));
                    retOn.setText(s.getString(7));*/
                    tr.addView(bId); tr.addView(bName);
                    tr.addView(isBy); tr.addView(isOn);
                    /*tr.addView(due); tr.addView(retTo);
                    tr.addView(retOn);*/
                    tbl.addView(tr);
                    k="Thermodynamics";
                }
            }
            catch(Exception e){
                Toast.makeText(HistoryActivity.this, "Error occured", Toast.LENGTH_LONG).show();
            }

        }
    }
}