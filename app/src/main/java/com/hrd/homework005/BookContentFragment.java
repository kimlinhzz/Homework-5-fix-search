package com.hrd.homework005;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookContentFragment extends Fragment{
    private RecyclerView recyclerView;
    private List<Book> bookList = new ArrayList<>();
    private BookAdapter bookAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.book_content_rcv);

        bookList.addAll(DatabaseClient.getInstance(this.getContext())
                .getBookDatabase()
                .getBookDao()
                .getBookList());
        bookAdapter = new BookAdapter(getActivity(), bookList);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        bookList = DatabaseClient.getInstance(this.getContext())
//                .getBookDatabase()
//                .getBookDao()
//                .getBookList();
//        BookAdapter bookAdapter = new BookAdapter(getActivity(), bookList);
//        recyclerView.setAdapter(bookAdapter);
//    }

    public void getSearchText(String search) {
        bookList = DatabaseClient.getInstance(getActivity())
                .getBookDatabase()
                .getBookDao()
                .searchBook(search);
        bookAdapter.swapData(bookList);
        Log.i("SSS", "sendSearchText: "+search);
    }
}
