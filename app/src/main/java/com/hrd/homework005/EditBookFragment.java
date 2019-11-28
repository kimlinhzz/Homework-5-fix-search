package com.hrd.homework005;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditBookFragment extends DialogFragment {

    private FloatingActionButton fab;
    private ImageView cover;
    private final static int REQUEST_IMAGE_CODE = 1000;
    private int bookId;
    private Button cancel;
    private Button update;
    private EditText title;
    private EditText author;
    private EditText price;
    private EditText year;
    private EditText amount;
    private Spinner spinner;
    private Book book;

    public EditBookFragment() {
        // Required empty public constructor
    }

    public EditBookFragment(int bookId){
        this.bookId = bookId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab = view.findViewById(R.id.browse_image);
        cover = view.findViewById(R.id.edit_image);
        cancel = view.findViewById(R.id.btn_edit_cancel);
        update = view.findViewById(R.id.btn_update);
        title = view.findViewById(R.id.edit_title);
        author = view.findViewById(R.id.edit_author);
        price = view.findViewById(R.id.edit_price);
        year = view.findViewById(R.id.edit_year);
        amount = view.findViewById(R.id.edit_amount);
        spinner = view.findViewById(R.id.edit_genre);
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setTitle(title.getText().toString());
                book.setAuthor(author.getText().toString());
                book.setAmount(Integer.parseInt(amount.getText().toString()));
                book.setCategory(spinner.getSelectedItem().toString());
                book.setPrice(Double.parseDouble(price.getText().toString()));
                book.setPublishYear(Integer.parseInt(year.getText().toString()));

                DatabaseClient.getInstance(getContext())
                        .getBookDatabase()
                        .getBookDao()
                        .updateBook(book);

                Objects.requireNonNull(getDialog()).dismiss();

                new MainActivity()
                        .reloadMain(
                                ((AppCompatActivity) Objects.requireNonNull(getContext())).getSupportFragmentManager()
                        );
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
    public void onStart() {
        super.onStart();
        book = DatabaseClient.getInstance(getContext())
                .getBookDatabase()
                .getBookDao()
                .findBookById(bookId);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        price.setText(String.format("%s",book.getPrice()));
        year.setText(String.format("%s",book.getPublishYear()));
        amount.setText(String.format("%s",book.getAmount()));

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()),
                R.array.genre,android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(book.getCategory()));
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }
}
