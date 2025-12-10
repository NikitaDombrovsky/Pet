package com.example.pet.Booking;

import android.content.Context;

import com.example.pet.AppDatabase;

public class EventRepository {

    private final EventDao eventDao;

    public EventRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.eventDao = db.eventDao();
    }

    public Event getEvent(String date) {
        Event event = eventDao.getEventByDate(date);
        if (event != null) {
            return event;
        }
        return null;
        //return eventDao.getEventByDate(date);
    }

    public void saveEvent(String date, String text) {
        Event existing = eventDao.getEventByDate(date);

        if (existing == null) {
            eventDao.insertEvent(new Event(date, text));
        } else {
            existing.text = text;
            eventDao.updateEvent(existing);
        }
    }
}

