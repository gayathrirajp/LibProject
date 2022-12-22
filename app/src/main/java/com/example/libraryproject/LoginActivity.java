package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;

import businesslayer.LoginLayer;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edUname, edPasswd;
    RadioGroup rGroup;
    String flag;
    public static String StaffId="", StudentId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Referencing views
        btnLogin=findViewById(R.id.btnLogin);
        edUname=findViewById(R.id.edTxtUsername);
        edPasswd=findViewById(R.id.edTxtPassword);
        rGroup=findViewById(R.id.rGroup);

        //Keeping EditTexts and Login Button disabled unless the "Staff/Student" RadioButton(s) are checked
        btnLogin.setEnabled(false);
        edUname.setEnabled(false);
        edPasswd.setEnabled(false);

        //RadioGroup's listener to choose between Staff and Student
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radioStaff){
                    flag="Staff";
                }
                else
                    flag="Student";
                btnLogin.setEnabled(true);
                edUname.setEnabled(true);
                edPasswd.setEnabled(true);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling method to validate login and redirect user
                authenticateLogin();
            }
        });

    }

    //Method to validate credentials of a user and redirect user to respective StartActivity
    void authenticateLogin(){
        String userName=edUname.getText().toString(),
                passWord=edPasswd.getText().toString();
        ResultSet set= LoginLayer.verifyLogin(userName, passWord, flag);
        if(set !=null){

            try{
                set.next();
                StaffId=set.getString(1);
                StudentId=set.getString(2);
                if(flag=="Student" && !StudentId.equals("-1")){
                    Intent i= new Intent(LoginActivity.this,StudentActivity.class);
                    i.putExtra("key", StudentId);
                    startActivity(i);
                    finish();
                }
                else if(flag=="Staff" && !StaffId.equals("-1")){
                    Intent i= new Intent(LoginActivity.this,LibraryActivity.class);
                    i.putExtra("StaffId", StaffId);

                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                }

            }
            catch(Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("error",e.getMessage());
            }
        }
    }
}