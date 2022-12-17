package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import businesslayer.StudentLayer;

public class StudentProfileActivity extends AppCompatActivity {

    TextView txtName, txtUsn, txtBatch, txtBranch, txtPhone;
    Button bFind, bHistory;
    String StudentUsn;
    ImageView iv;
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
        iv=findViewById(R.id.imageView);

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

        bFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentProfileActivity.this, FindBookActivity.class);
                startActivity(i);
            }
        });
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
            MultiFormatWriter writer =new MultiFormatWriter();
            try {
                BitMatrix matrix=writer.encode(StudentUsn, BarcodeFormat.QR_CODE,350,350);
                BarcodeEncoder encoder =new BarcodeEncoder();
                Bitmap bitmap =encoder.createBitmap(matrix);
                iv.setImageBitmap(bitmap);
                InputMethodManager manager =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(txtName.getApplicationWindowToken(),0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}