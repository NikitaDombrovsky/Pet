package com.example.pet.Booking;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EventDao {

    //@Query("SELECT * FROM event WHERE date = :date LIMIT 1")
    @Query("SELECT * FROM event WHERE date = :date LIMIT 1")
    Event getEventByDate(String date);

    @Insert
    void insertEvent(Event event);

    @Update
    void updateEvent(Event event);
}