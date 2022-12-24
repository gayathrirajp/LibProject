package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.libraryproject.databinding.ActivityStudentBinding;
import com.example.libraryproject.databinding.ActivityStudentBinding;
import com.google.android.material.navigation.NavigationBarView;

public class StudentActivity extends AppCompatActivity {
    public static String StudentUsn;
    ActivityStudentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding= ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        Intent i=getIntent();
        StudentUsn=i.getStringExtra("key");


        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.menuHome:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.menuHistory:
                        replaceFragment(new StudentHistoryFragment());
                        break;
                    case R.id.menuFindBook:
                        replaceFragment(new SearchBookFragment());
                        break;
                }
                return true;
                //return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}