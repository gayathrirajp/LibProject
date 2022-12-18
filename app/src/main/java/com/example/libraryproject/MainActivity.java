package com.example.libraryproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.libraryproject.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.menuFindBook){
                    replaceFragment(new FindBookFragment());
                }
                else if (item.getItemId()==R.id.menuHome){
                    replaceFragment(new HomeFragment());
                }
                else if (item.getItemId()==R.id.menuHistory){
                    replaceFragment(new HistoryFragment());
                }
                /*switch(item.getItemId()){
                    case R.id.menuHome:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.btnHistory:
                        replaceFragment(new HistoryFragment());
                        break;
                    case R.id.btnFindBook:
                        replaceFragment(new FindBookFragment());
                        break;
                }*/
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