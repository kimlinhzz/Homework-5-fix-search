package com.hrd.homework005;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookContentFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Book> bookList;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        bookList = new ArrayList<>();
        loadRecyclerViewItem();
    }

    private void loadRecyclerViewItem(){
        for(int i=0; i<10; i++){
            Book book = new Book(
                    ""+i,
                    ""+i,
                    ""+i,
                    i,i,i
            );
            bookList.add(book);
        }
        BookAdapter bookAdapter = new BookAdapter(getActivity(), bookList);
        recyclerView.setAdapter(bookAdapter);
    }
}
