package com.example.healthwith.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM Event")
    LiveData<List<Event>> getAll();

    @Insert
    void insertEvent(Event event);

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);

    @Query("DELETE FROM Event")
    void deleteAllEvents();
}
