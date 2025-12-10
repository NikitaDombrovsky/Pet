/*
package com.example.pet.Daily;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.pet.AppDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalsRepository {

    private final DailyTaskDao taskDao;
    private final StreakDao streakDao;

    public GoalsRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.taskDao = db.taskDao();
        this.streakDao = db.streakDao();
    }

    public List<DailyTask> loadOrCreateDailyTasks(String today) {
        List<DailyTask> tasks = taskDao.getTasksForDate(today);

        if (tasks.isEmpty()) {
            tasks = new ArrayList<>();
            tasks.add(new DailyTask(today, "Задача 1", false));
            tasks.add(new DailyTask(today, "Задача 2", false));
            tasks.add(new DailyTask(today, "Задача 3", false));
            taskDao.insertTasks(tasks);
        }

        taskDao.cleanupOldTasks(today);
        return tasks;
    }

    public void updateTask(DailyTask task) {
        taskDao.updateTask(task);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateStreakForToday(String today) {
        Streak streak = streakDao.getStreak();

        if (streak == null) {
            streak = new Streak(0, today);
            streakDao.insertStreak(streak);
        }

        // Не обновлять дважды
        if (streak.lastStreakDate.equals(today)) return;

        // Проверяем вчера
        String yesterday = LocalDate.parse(today)
                .minusDays(1)
                .toString();

        List<DailyTask> yesterdayTasks = taskDao.getTasksForDate(yesterday);

        boolean allDone = true;
        for (DailyTask t : yesterdayTasks) {
            if (!t.done) {
                allDone = false;
                break;
            }
        }

        if (allDone && !yesterdayTasks.isEmpty()) {
            streak.currentStreak++;
        } else {
            streak.currentStreak = 0;
        }

        streak.lastStreakDate = today;
        streakDao.updateStreak(streak);
    }
}
*/
