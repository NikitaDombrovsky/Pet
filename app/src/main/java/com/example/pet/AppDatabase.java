package com.example.pet;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.pet.Booking.Event;
import com.example.pet.Booking.EventDao;
import com.example.pet.Daily.DailyTask;
import com.example.pet.Daily.DailyTaskDao;
import com.example.pet.Daily.Streak;
import com.example.pet.Daily.StreakDao;

@Database(entities = {Event.class, DailyTask.class, Streak.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DailyTaskDao taskDao();
    public abstract StreakDao streakDao();

    public abstract EventDao eventDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "events1_db"
                    ).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
