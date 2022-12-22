package com.example.libraryproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScannerActivity extends AppCompatActivity {
String variable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        IntentIntegrator intentIntegrator=new IntentIntegrator(ScannerActivity.this);
        intentIntegrator.setPrompt("To turn on flash,press volume up button");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(CaptureActivityPortrait.class);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null){
            if(intentResult.getContents()==null){
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
           //  to access context from another context
           Intent iCancel=new Intent(ScannerActivity.this,LibraryActivity.class);
           iCancel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(iCancel);
             
            }
            else{
                variable=intentResult.getContents();

                Intent iTransact=new Intent(ScannerActivity.this,TransactActivity.class);
                iTransact.putExtra("variable",variable);
                setResult(2,iTransact);
                finish();
               // Toast.makeText(ScannerActivity.this, variable, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
            /*
            Intent iCancel=new Intent(ScannerActivity.this,LibraryActivity.class);
            startActivity(iCancel);
            finish();*/
        }
    }
}