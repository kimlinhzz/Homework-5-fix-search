package com.hrd.homework005;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.thedeanda.lorem.LoremIpsum;

import java.util.Objects;

public class ReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        // back navigation action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        int bookId =  getIntent().getIntExtra("bookId",1);

        Book book = DatabaseClient.getInstance(getApplicationContext())
                .getBookDatabase()
                .getBookDao()
                .findBookById(bookId);

        TextView description = findViewById(R.id.read_desc);
        description.setText(book.getDescription());
        TextView title = findViewById(R.id.read_title);
        title.setText(book.getTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
