package com.example.pet.Booking;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Event {
    @PrimaryKey
    @NonNull
    public String date;     // формат: "yyyy-MM-dd"

    public String text;

    public Event(@NonNull String date, String text) {
        this.date = date;
        this.text = text;
    }
}
