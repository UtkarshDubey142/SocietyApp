package com.utkarsh.practice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);


        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        BottomNavigationView navigationView = findViewById(R.id.buttom_navigation);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.ic_info:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.ic_comment:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.ic_settings:
                    viewPager.setCurrentItem(2);
                    return true;
            }


            return false;
        });
    }

    @Override
    public void onClick(View v) {

    }
}
