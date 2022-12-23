package com.example.libraryproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adapterlayer.BookAdapter;
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

        //moving to scanner activity to scan student QR code
        Intent iStudent = new Intent(TransactActivity.this, ScannerActivity.class);
        startActivityForResult(iStudent,1);

        //Button to initiate scan of Barcode of Issue book
        bIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iBook=new Intent(TransactActivity.this,ScannerActivity.class);
                startActivityForResult(iBook,3);
            }
        });
        //Button to initiate scan of Barcode of Return book
        bCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCollect=new Intent(TransactActivity.this,ScannerActivity.class);
                startActivityForResult(iCollect,4);
            }
        });
    }
    //To get the details of book issued earlier(and not returned)
    void getDataForStudentBooks() throws SQLException {
        ResultSet set1 = TransactLayer.getStudentBookDetails(scanUsn);
        while(set1.next()){
            //To set the details of book issued earlier(and not returned)
            bookName.add(set1.getString(1));
            issuedOn.add(set1.getString(2));
            issuedBy.add(set1.getString(3));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //after scanning student QR
        if(requestCode==1&&resultCode==2){
            scanUsn=data.getStringExtra("variable");
            ResultSet set= businesslayer.TransactLayer.studentInfo(scanUsn);
            bookName=new ArrayList<String>();
            issuedOn=new ArrayList<String>();
            issuedBy=new ArrayList<String>();
            //cards for the books issued by the student and not yet returned
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
                //set the student details in CardView
                set.next();
                name.setText(set.getString(2));
                usn.setText(set.getString(1));
                batch.setText(set.getString(3));
                branch.setText(set.getString(4));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //after scanning book bar code for issue
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
                    //intent to move to library activity to dispaly AlertDialog
                    Intent iIssue=new Intent(TransactActivity.this,LibraryActivity.class);
                    iIssue.putExtra("studentName",set.getString(2));
                    iIssue.putExtra("bookName",set.getString(1));
                    iIssue.putExtra("USN",scanUsn);
                    iIssue.putExtra("BookId",scanBookId);
                    iIssue.putExtra("TAG","Issue");
                    //to clear the top of the stack
                    iIssue.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(iIssue);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //after scanning bar code for collect
        else  if(requestCode==4&&resultCode==2){
            scanBookId=data.getStringExtra("variable");
            try {
                ResultSet set= TransactLayer.collectBookDetails(scanUsn,scanBookId);
                if(!set.next()){
                    Toast.makeText(this, "not issued", Toast.LENGTH_SHORT).show();
                }
                else if(set!=null ){
                    Intent iCollect2Library=new Intent(TransactActivity.this,LibraryActivity.class);
                    iCollect2Library.putExtra("studentName",set.getString(1));
                    iCollect2Library.putExtra("bookName",set.getString(2));
                    iCollect2Library.putExtra("USN",scanUsn);
                    iCollect2Library.putExtra("BookId",scanBookId);
                    iCollect2Library.putExtra("TAG","Collect");
                    startActivity(iCollect2Library);
                    finish();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}