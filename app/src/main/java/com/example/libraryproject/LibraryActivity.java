package com.example.libraryproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.libraryproject.databinding.ActivityLibraryBinding;
/*import com.example.libraryproject.databinding.ActivityMainBinding;*/
import com.google.android.material.navigation.NavigationBarView;

import java.sql.ResultSet;
import java.sql.SQLException;

import businesslayer.TransactLayer;

public class LibraryActivity extends AppCompatActivity {

ActivityLibraryBinding binding;
//To bind the fragments
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLibraryBinding.inflate(getLayoutInflater());
        //Binding the fragments to the frame layout

        setContentView(binding.getRoot());
        replaceFragment(new TransactFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Transact:

                        replaceFragment(new TransactFragment());
                        break;
                    case R.id.History:replaceFragment(new HistoryFragment());
                        break;
                    case R.id.Profile:replaceFragment(new ProfileFragment());
                        break;

                }

                return true;

            }
        });


        Intent iFromTransact =getIntent();
        if(getIntent()!=null && iFromTransact.getStringExtra("TAG")!=null){
if(iFromTransact.getStringExtra("TAG").equals("Issue")){
            String studentName =iFromTransact.getStringExtra("studentName");
            String bookName = iFromTransact.getStringExtra("bookName");
            String USN = iFromTransact.getStringExtra("USN");
    String bookId = iFromTransact.getStringExtra("BookId");


                AlertDialog.Builder ad = new AlertDialog.Builder(LibraryActivity.this);
                ad.setTitle("Confirm Issue?\n");
                ad.setCancelable(false);
                ad.setMessage("Book: " + bookName + "\nStudent: "+ studentName+"\nUSN: "+USN);
              ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      try {
                          ResultSet set= TransactLayer.issueBookConfirm(USN,bookId,LoginActivity.StaffId);
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                      dialogInterface.cancel();
                      Toast.makeText(LibraryActivity.this, "Issue successful", Toast.LENGTH_SHORT).show();
                  }
              });
              ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.cancel();
                      Toast.makeText(LibraryActivity.this, "Book not issued", Toast.LENGTH_SHORT).show();
                  }
              });

                AlertDialog d=ad.create();
                d.show();
            }
          else  if(iFromTransact.getStringExtra("TAG").equals("Collect")){

    String studentName =iFromTransact.getStringExtra("studentName");
    String bookName = iFromTransact.getStringExtra("bookName");
    String USN = iFromTransact.getStringExtra("USN");
    String bookId = iFromTransact.getStringExtra("BookId");



    AlertDialog.Builder ad = new AlertDialog.Builder(LibraryActivity.this);
    ad.setTitle("Confirm return?\n");
    ad.setCancelable(false);
    ad.setMessage("Book: " + bookName + "\nStudent: " + studentName+"\nUSN: "+USN);
    ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            try {
                ResultSet set= TransactLayer.collectBookConfirm(USN,bookId,LoginActivity.StaffId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dialogInterface.cancel();
            Toast.makeText(LibraryActivity.this, "Collected Successfully", Toast.LENGTH_SHORT).show();
        }
    });
    ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
            Toast.makeText(LibraryActivity.this, "Book not Collected", Toast.LENGTH_SHORT).show();
        }
    });

    AlertDialog d=ad.create();
    d.show();
}




        }


}
    //method that will replace frame layout with fragments
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


}