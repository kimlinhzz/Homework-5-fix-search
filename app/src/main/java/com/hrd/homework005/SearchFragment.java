package com.hrd.homework005;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private Button donateBook;
    private final static int size = LinearLayout.LayoutParams.MATCH_PARENT;
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        donateBook = view.findViewById(R.id.btn_donate_book);
        donateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBookFragment bookFragment = new AddBookFragment();
                assert getFragmentManager() != null;
                bookFragment.show(getFragmentManager().beginTransaction(),"ff");
            }
        });
    }
}
