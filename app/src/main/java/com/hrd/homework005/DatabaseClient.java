package com.hrd.homework005;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context mContext;
    private BookDatabase bookDatabase;
    private static DatabaseClient mInstance;
    private DatabaseClient(Context mContext){
        this.mContext = mContext;
        bookDatabase = Room.databaseBuilder(mContext,BookDatabase.class,"books")
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if(mInstance==null) mInstance = new DatabaseClient(context);
        return mInstance;
    }

    public BookDatabase getBookDatabase(){
        return bookDatabase;
    }
}
