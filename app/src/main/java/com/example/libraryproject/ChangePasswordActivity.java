package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;

import businesslayer.StudentLayer;

public class ChangePasswordActivity extends AppCompatActivity {

    Button btnChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent i= getIntent();
        String Usn= i.getStringExtra("usn");

        btnChange= findViewById(R.id.btnChangePasswd);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edConfirm= findViewById(R.id.edConfirmPassword);
                EditText edNew = findViewById(R.id.edNewPassword);
                EditText edOld= findViewById(R.id.edOldPassword);
                String old= edOld.getText().toString(),
                        newP= edNew.getText().toString(),
                        confirm=edConfirm.getText().toString();
                if(newP.equals(confirm)){
                    ResultSet s= StudentLayer.changePassword(Usn, old, newP);
                    if(s!=null){
                        try{
                            s.next();
                            if (s.getString(1).equals("1")){
                                Toast.makeText(ChangePasswordActivity.this, "Changed successfully", Toast.LENGTH_LONG).show();
                                Intent m=new Intent(ChangePasswordActivity.this, StudentActivity.class);
                                startActivity(m);
                            }
                            else{
                                Toast.makeText(ChangePasswordActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception e){
                            Log.e("Error", e.getMessage());
                        }
                    }

                }
                else{
                    Toast.makeText(ChangePasswordActivity.this, "Not confirmed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}