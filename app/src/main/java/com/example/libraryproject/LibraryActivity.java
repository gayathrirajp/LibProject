package com.example.libraryproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.libraryproject.databinding.ActivityLibraryBinding;
import com.example.libraryproject.databinding.ActivityMainBinding;
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
        replaceFragment(new CollectFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.Issue: replaceFragment(new IssueFragment());
                        break;
                    case R.id.Collect:replaceFragment(new CollectFragment());
                        break;
                    case R.id.History:replaceFragment(new HistoryFragment());
                        break;

                }

                return true;
            }
        });


    }
    //method that will replace frame layout with fragments
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}