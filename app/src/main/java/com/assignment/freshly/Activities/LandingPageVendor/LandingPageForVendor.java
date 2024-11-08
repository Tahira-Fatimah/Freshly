package com.assignment.freshly.Activities.LandingPageVendor;

import com.assignment.freshly.R; // Make sure this is the correct one
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class LandingPageForVendor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_category_0);

            Fragment defaultFragment = CategoryFragmentForVendor.newInstance(null);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, defaultFragment)
                    .commit();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int id = item.getItemId();
                if(id == R.id.nav_category_0){
                    selectedFragment = CategoryFragmentForVendor.newInstance(null);
                }else if (id == R.id.nav_category_1) {
                    selectedFragment = CategoryFragmentForVendor.newInstance("vegetable");
                } else if (id == R.id.nav_category_2) {
                    selectedFragment = CategoryFragmentForVendor.newInstance("fruit");
                } else if (id == R.id.nav_category_3) {
                    selectedFragment = CategoryFragmentForVendor.newInstance("dry fruit");
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, selectedFragment)
                            .commit();
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });



    }
}