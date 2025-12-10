/*
package com.example.pet.Daily;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DailyTaskDao {

    @Query("SELECT * FROM daily_tasks WHERE date = :date ORDER BY id ASC")
    List<DailyTask> getTasksForDate(String date);

    @Insert
    void insertTasks(List<DailyTask> tasks);

    @Update
    void updateTask(DailyTask task);

    @Query("DELETE FROM daily_tasks WHERE date != :today")
    void cleanupOldTasks(String today); // убираем старые
}*/
