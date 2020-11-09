package com.example.roomdbtool;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Person.class, exportSchema = false, version = 1)
public abstract class PersonDB extends RoomDatabase {

    private static final String DB_NAME = "person_db";//資料庫名稱
    private static PersonDB instance ;

    public static synchronized PersonDB getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PersonDB.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract PersonDao personDao();

}
