package com.hrd.homework005;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {
    @Query("SELECT * FROM books")
    List<Book> getBookList();

    @Query("SELECT * FROM books WHERE id=:id")
    Book findBookById(int id);

    @Insert
    void insertBook(Book... book);

    @Delete
    void deleteBook(Book book);

    @Update
    void updateBook(Book book);

    @Query("SELECT * FROM books WHERE title LIKE :title")
    List<Book> searchBook(String title);

}
