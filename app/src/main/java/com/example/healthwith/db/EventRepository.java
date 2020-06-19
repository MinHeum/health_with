package com.example.healthwith.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Date;
import java.util.List;

public class EventRepository {
    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;

    public EventRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvents = db.eventDao().getAll();
    }

    public LiveData<List<Event>> getAllEvents() { return mAllEvents; }

    public void insert(Event event) {
        new insertAsyncTask(mEventDao).execute(event);
    }
    public void deleteAll() { new eraseAllAsyncTask(mEventDao).execute();}
    public void update(Event event) { new updateAsyncTask(mEventDao).execute(); }

    private static class insertAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao mAsyncTaskDao;

        insertAsyncTask(EventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            mAsyncTaskDao.insertEvent(events[0]);
            return null;
        }
    }

    private static class eraseAllAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao mAsyncTaskDao;

        eraseAllAsyncTask(EventDao dao) {mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Event... events) {
            mAsyncTaskDao.deleteAllEvents();
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao mAsyncTaskDao;

        updateAsyncTask(EventDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(Event... events) {
            mAsyncTaskDao.updateEvent(events[0]);
            return null;
        }
    }

}
