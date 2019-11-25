package com.hrd.homework005;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context mContext;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.mContext = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.book_title.setText(book.getTitle());

        holder.book_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.book_option);
                popupMenu.inflate(R.menu.list_card_item);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.remove:
                                Toast.makeText(mContext, "Remove", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.read:
                                Toast.makeText(mContext, "Read", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.edit:
                                Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView book_cover;
        TextView book_title;
        TextView book_author;
        TextView book_publish;
        TextView book_category;
        TextView book_price;
        TextView book_amount;
        TextView book_option;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_cover = itemView.findViewById(R.id.book_cover_cv);
            book_title = itemView.findViewById(R.id.book_title);
            book_author = itemView.findViewById(R.id.book_author);
            book_publish = itemView.findViewById(R.id.book_year);
            book_category = itemView.findViewById(R.id.book_category);
            book_price = itemView.findViewById(R.id.book_price);
            book_amount = itemView.findViewById(R.id.book_amount);
            book_option = itemView.findViewById(R.id.button_option);
        }
    }
}