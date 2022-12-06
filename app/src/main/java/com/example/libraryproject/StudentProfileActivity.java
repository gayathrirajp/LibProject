package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class StudentProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Intent i=getIntent();
        String k=i.getStringExtra("USN");
        Toast.makeText(StudentProfileActivity.this, "Reached "+ k, Toast.LENGTH_LONG).show();
    }
}