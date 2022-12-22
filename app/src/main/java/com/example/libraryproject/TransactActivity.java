package com.example.libraryproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;

import businesslayer.TransactLayer;

public class TransactActivity extends AppCompatActivity {
    TextView name, usn, batch, branch;
    String scanUsn,  scanBookId;
    Button bCollect,bIssue;
    ArrayList<String> bookName,issuedOn,issuedBy;
    BookAdapter adapter;
  
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
void getDataForStudentBooks() throws SQLException {
   ResultSet set1 = TransactLayer.getStudentBookDetails(scanUsn);
    while(set1.next()){
        bookName.add(set1.getString(1));
        issuedOn.add(set1.getString(2));
        issuedBy.add(set1.getString(3));
        Toast.makeText(this, set1.getString(1), Toast.LENGTH_SHORT).show();
    }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==2){
         scanUsn=data.getStringExtra("variable");
            ResultSet set= businesslayer.TransactLayer.studentInfo(scanUsn);
            bookName=new ArrayList<String>();
            issuedOn=new ArrayList<String>();
            issuedBy=new ArrayList<String>();
            RecyclerView recyclerViewBooks;
            recyclerViewBooks=findViewById(R.id.recyclerView2);


            adapter=new BookAdapter(this,bookName,issuedOn,issuedBy);
            recyclerViewBooks.setAdapter(adapter);
            recyclerViewBooks.setLayoutManager(new LinearLayoutManager(TransactActivity.this));
            try {
                getDataForStudentBooks();
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
                ResultSet set= TransactLayer.issueBookDetails(scanUsn,scanBookId);
             set.next();

                if(set.getString(1).equals("Already Issued")){
                    Toast.makeText(this, "Book already issued", Toast.LENGTH_SHORT).show();
                }
                else if(set.getString(1).equals("not available")){
                    Toast.makeText(this, "Book not available", Toast.LENGTH_SHORT).show();
                }
                else {
                  //  Toast.makeText(TransactActivity.this, "heyy", Toast.LENGTH_SHORT).show();
                   Intent iIssue=new Intent(TransactActivity.this,LibraryActivity.class);
                   iIssue.putExtra("studentName",set.getString(2));
                   iIssue.putExtra("bookName",set.getString(1));
                    iIssue.putExtra("USN",scanUsn);
                    iIssue.putExtra("BookId",scanBookId);

            iIssue.putExtra("TAG","Issue");
                    iIssue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   startActivity(iIssue);



                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else  if(requestCode==4&&resultCode==2){
            scanBookId=data.getStringExtra("variable");
            try {
            ResultSet set= TransactLayer.collectBookDetails(scanUsn,scanBookId);
                /*set.next();*/
                   if(!set.next()){
                    Toast.makeText(this, "not issued", Toast.LENGTH_SHORT).show();
                }

            else if(set!=null ){

                    //  Toast.makeText(TransactActivity.this, "heyy", Toast.LENGTH_SHORT).show();
                    Intent icCollect2Library=new Intent(TransactActivity.this,LibraryActivity.class);
     icCollect2Library.putExtra("studentName",set.getString(1));
     icCollect2Library.putExtra("bookName",set.getString(2));
     icCollect2Library.putExtra("USN",scanUsn);
     icCollect2Library.putExtra("BookId",scanBookId);
     icCollect2Library.putExtra("TAG","Collect");
                    startActivity(icCollect2Library);
                    finish();
                }



            }
            catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}