package com.hrd.homework005;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private Button donateBook;
    private Button search;
    private TextView searchText;
    private SearchService searchService;
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
        search = view.findViewById(R.id.btn_search);
        searchText = view.findViewById(R.id.ed_search);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        donateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBookFragment bookFragment = new AddBookFragment();
                assert getFragmentManager() != null;
                bookFragment.show(getFragmentManager().beginTransaction(),"Add");
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Search", Toast.LENGTH_SHORT).show();
                searchService.sendSearchText(searchText.getText().toString());
            }
        });
    }

    interface SearchService{
        void sendSearchText(String search);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            searchService = (SearchService)getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException("Error");
        }
    }
}
