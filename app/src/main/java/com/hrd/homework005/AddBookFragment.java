package com.hrd.homework005;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends DialogFragment {

    private ImageView cover;
    private FloatingActionButton fab;
    private final static int REQUEST_IMAGE_CODE = 1000;
    private Button cancel;
    private Button save;
    private EditText title;
    private EditText author;
    private EditText price;
    private EditText year;
    private EditText amount;
    private Spinner spinner;

    public AddBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cover = view.findViewById(R.id.pop_image);
        fab = view.findViewById(R.id.browse_image);
        cancel = view.findViewById(R.id.btn_add_cancel);
        save = view.findViewById(R.id.btn_add_save);
        title = view.findViewById(R.id.add_title);
        author = view.findViewById(R.id.add_author);
        price = view.findViewById(R.id.add_price);
        year = author = view.findViewById(R.id.add_year);
        amount = view.findViewById(R.id.add_amount);
        spinner = view.findViewById(R.id.add_genre);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.genre,android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_IMAGE_CODE);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book(
                        title.getText().toString(),
                        author.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        LoremIpsum.getInstance().getParagraphs(1,5),
                        Integer.parseInt(year.getText().toString()),
                        Double.parseDouble(price.getText().toString()),
                        Integer.parseInt(amount.getText().toString())
                );
                DatabaseClient.getInstance(getContext())
                        .getBookDatabase()
                        .getBookDao()
                        .insertBook(book);
                Log.i("BOOK", "onClick: "+book.toString());
                Objects.requireNonNull(getDialog()).dismiss();

                List<Book> list = DatabaseClient.getInstance(getContext())
                        .getBookDatabase()
                        .getBookDao()
                        .getBookList();
                Log.i("Data", "onClick: "+list.size());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CODE && resultCode== Activity.RESULT_OK){
            assert data != null;
            cover.setImageURI(data.getData());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    // validate text
    private boolean isAvailableText(EditText ed){
        return !ed.getText().toString().equals("");
    }
}
