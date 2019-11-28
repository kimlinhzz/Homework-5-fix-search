package com.hrd.homework005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SearchFragment.SearchService {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.book_content,new BookContentFragment())
                .addToBackStack("Main")
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.home_tab)
                    reloadMain(getSupportFragmentManager());
                return true;
            }
        });
    }

    public void reloadMain(FragmentManager fm) {
        fm.beginTransaction()
                .replace(R.id.book_content,new BookContentFragment())
                .addToBackStack("Main")
                .commit();
    }

    @Override
    public void sendSearchText(String search) {
        BookContentFragment bookContentFragment = new BookContentFragment();
        bookContentFragment.getSearchText(search);
    }
}