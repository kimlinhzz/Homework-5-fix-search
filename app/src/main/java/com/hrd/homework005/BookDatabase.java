package com.hrd.homework005;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class},version = 1,exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDAO getBookDao();
}
