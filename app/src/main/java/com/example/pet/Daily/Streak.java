package com.example.pet.Daily;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "streak")
public class Streak {

    @PrimaryKey
    public int id = 1; // одна запись

    public int currentStreak;
    @NonNull
    public String lastStreakDate; // yyyy-MM-dd

    public Streak(int currentStreak, @NonNull String lastStreakDate) {
        this.currentStreak = currentStreak;
        this.lastStreakDate = lastStreakDate;
    }
}
