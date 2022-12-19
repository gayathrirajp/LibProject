package com.example.libraryproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.ResultSet;
import java.sql.SQLException;

import businesslayer.TransactLayer;

public class TransactActivity extends AppCompatActivity {
    TextView name, usn, batch, branch;
    String scanUsn,  scanBookId;
    Button bCollect,bIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transact);
        bCollect = findViewById(R.id.bCollect);
        name = findViewById(R.id.tName);
        usn = findViewById(R.id.tUsn);
        batch = findViewById(R.id.tbatch);
        branch = findViewById(R.id.tbranch);
        bIssue=findViewById(R.id.bIssue);
        Intent iStudent = new Intent(TransactActivity.this, ScannerActivity.class);
        startActivityForResult(iStudent,1);
        bIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iBook=new Intent(TransactActivity.this,ScannerActivity.class);
                startActivityForResult(iBook,3);
            }
        });
        bCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCollect=new Intent(TransactActivity.this,ScannerActivity.class);
                startActivityForResult(iCollect,4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
         scanUsn=data.getStringExtra("variable");
            ResultSet set= businesslayer.TransactLayer.studentInfo(scanUsn);

            try {
                set.next();
                name.setText(set.getString(2));
                usn.setText(set.getString(1));
                batch.setText(set.getString(3));
                branch.setText(set.getString(4));

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
      else  if(requestCode==3&&resultCode==2){
            scanBookId=data.getStringExtra("variable");
            try {
                ResultSet set= TransactLayer.issueBook(scanUsn,scanBookId,"NM20LIB001");
             set.next();

                if(set.getString(1).equals("-1")){
                    Toast.makeText(this, "Book already issued earlier", Toast.LENGTH_SHORT).show();
                }
                else if(set.getString(1).equals("SUCCESS")){
                  //  Toast.makeText(TransactActivity.this, "heyy", Toast.LENGTH_SHORT).show();
                   Intent iIssue=new Intent(TransactActivity.this,LibraryActivity.class);
                   iIssue.putExtra("studentName",set.getString(2));
                   iIssue.putExtra("bookName",set.getString(3));
            iIssue.putExtra("TAG","Issue");
                   startActivity(iIssue);

                   finish();


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else  if(requestCode==4&&resultCode==2){
            scanBookId=data.getStringExtra("variable");
            try {
            ResultSet set= TransactLayer.collectBook(scanUsn,scanBookId,"NM20LIB001");
            set.next();

 if(set!=null){
                    //  Toast.makeText(TransactActivity.this, "heyy", Toast.LENGTH_SHORT).show();
                    Intent icCollect2Library=new Intent(TransactActivity.this,LibraryActivity.class);
     icCollect2Library.putExtra("studentName",set.getString(1));
     icCollect2Library.putExtra("bookName",set.getString(2));
     icCollect2Library.putExtra("TAG","Collect");
                    startActivity(icCollect2Library);
                    finish();


                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}