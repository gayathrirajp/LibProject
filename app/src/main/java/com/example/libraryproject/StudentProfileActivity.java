package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

import businesslayer.StudentLayer;

public class StudentProfileActivity extends AppCompatActivity {

    TextView txtName, txtUsn, txtBatch, txtBranch, txtPhone;
    Button bFind, bHistory;
    String StudentUsn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Fetching Usn of student from the LoginActivity
        Intent i=getIntent();
        StudentUsn=i.getStringExtra("key");

        //Just for my reference
        Toast.makeText(StudentProfileActivity.this, "Reached "+ StudentUsn, Toast.LENGTH_LONG).show();

        //Referencing TextViews within the card
        txtName=findViewById(R.id.txtCardName);
        txtUsn=findViewById(R.id.txtCardUsn);
        txtBatch=findViewById(R.id.txtCardBatch);
        txtBranch=findViewById(R.id.txtCardBranch);
        txtPhone=findViewById(R.id.txtCardPhone);

        //TENTATIVE: Referencing Buttons
        bFind=findViewById(R.id.btnFindBook);
        bHistory=findViewById(R.id.btnHistory);

        //Fetching Details of student and setting them on the CardView
        getStudentDetails();

        //TENTATIVE: Button leading to the history activity
        bHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentProfileActivity.this, HistoryActivity.class);
                i.putExtra("usn", StudentUsn);
                startActivity(i);
            }
        });

        /*bFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentProfileActivity.this, FindBook.class);
                startActivity(i);
            }
        });*/
    }

    //Method to set student details on the CardView
    //Todo Detailing
    void getStudentDetails(){
        ResultSet s= StudentLayer.getProfile(StudentUsn);
        if(s!=null){
            try {
                s.next();
                txtName.setText(s.getString(2));
                txtUsn.setText(s.getString(1));
                txtBatch.setText(s.getString(3));
                txtBranch.setText(s.getString(4));
                txtPhone.setText(s.getString(5));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}