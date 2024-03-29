package com.hrd.homework005;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context mContext;
    private List<Book> bookList;

    public BookAdapter(){
        bookList = new ArrayList<>();
    }

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
        final Book book = bookList.get(position);
        holder.book_title.setText(book.getTitle());
        holder.book_author.setText(book.getAuthor());
        holder.book_category.setText(book.getCategory());
        holder.book_price.setText(String.format("%s",book.getPrice()));
        holder.book_amount.setText(String.format("%s",book.getAmount()));
        holder.book_publish.setText(String.format("%s",book.getPublishYear()));

        holder.book_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu = new PopupMenu(mContext,holder.book_option);
                popupMenu.inflate(R.menu.list_card_item);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.remove:
                                removeBookAlert(v,book);
                                break;
                            case R.id.read:
                                Intent intent = new Intent(v.getContext(),ReadBookActivity.class);
                                intent.putExtra("bookId",book.getId());
                                v.getContext().startActivity(intent);
                                break;
                            case R.id.edit:
                                EditBookFragment bookFragment = new EditBookFragment(book.getId());
                                bookFragment.show(((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction(),"Edit");
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
        if(bookList!=null)
            return bookList.size();
        return 0;
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

    // remove
    private void removeBookAlert(final View view, final Book book){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure to remove?")
                .setTitle("Remove Book");

        // confirm delete button
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseClient.getInstance(view.getContext())
                        .getBookDatabase()
                        .getBookDao()
                        .deleteBook(book);

                new MainActivity().reloadMain(
                        ((AppCompatActivity) Objects.requireNonNull(view.getContext()))
                                .getSupportFragmentManager());

            }
        });

        // cancel delete button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    void swapData(List<Book> list){
        if(list!=null){
            this.bookList.clear();
            this.bookList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
