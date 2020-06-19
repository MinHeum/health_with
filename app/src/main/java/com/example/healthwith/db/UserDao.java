package com.example.healthwith.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE id=1")
    LiveData<User> getUser();

    @Insert(onConflict = 1)
    void insertUser(User user);
}
