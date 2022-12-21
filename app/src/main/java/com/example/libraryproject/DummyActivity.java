package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        Intent i=getIntent();
        String k=i.getStringExtra("StaffId");
        Toast.makeText(DummyActivity.this, "Reached "+ k, Toast.LENGTH_LONG).show();
    }
}