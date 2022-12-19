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
            Toast.makeText(this, bookName, Toast.LENGTH_SHORT).show();


                AlertDialog.Builder ad = new AlertDialog.Builder(LibraryActivity.this);
                ad.setTitle("Issue Successful");
                ad.setMessage("Book" + bookName + " has been successfully issued to " + studentName);
                ad.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog d=ad.create();
                d.show();
            }
          else  if(iFromTransact.getStringExtra("TAG").equals("Collect")){

    String studentName =iFromTransact.getStringExtra("studentName");
    String bookName = iFromTransact.getStringExtra("bookName");
    Toast.makeText(this, bookName, Toast.LENGTH_SHORT).show();


    AlertDialog.Builder ad = new AlertDialog.Builder(LibraryActivity.this);
    ad.setTitle("Collected Successfully!");
    ad.setMessage("Book" + bookName + " has been successfully returned by " + studentName);
    ad.setNeutralButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
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