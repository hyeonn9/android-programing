package com.cookandroid.miniproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNV = findViewById(R.id.nav_view);
        bottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigation(menuItem.getItemId());

                return true;
            }
        });
        bottomNV.setSelectedItemId(R.id.page1);
    }
    private void BottomNavigation(int id){
        String tag=String.valueOf(id);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        Fragment currentFragment= fragmentManager.getPrimaryNavigationFragment();
        if(currentFragment !=null){
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragment=fragmentManager.findFragmentByTag(tag);
        if (fragment==null){
            if(id==R.id.page1){
                fragment = new MemoFragment();
            }else if(id==R.id.page2){
                fragment=new TodoFragment();
            }else{
                fragment=new WiseFragment();
            }

            fragmentTransaction.add(R.id.content_layout,fragment,tag);
        }else{
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNow();
    }

};

        


