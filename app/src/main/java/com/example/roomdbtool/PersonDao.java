package com.example.roomdbtool;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    void insert(Person p);

    @Query("Select * from person")
    List<Person> getPersonList();

    @Update
    void update(Person p);

    @Delete
    void delete(Person p);

}
