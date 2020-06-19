package com.example.healthwith.ui.calendar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.healthwith.db.Event;
import com.example.healthwith.db.EventRepository;
import com.example.healthwith.db.User;
import com.example.healthwith.db.UserRepository;

import java.util.Date;
import java.util.List;

public class CalendarViewModel extends AndroidViewModel {

    private EventRepository mRepository;
    private UserRepository mUserRepository;

    private LiveData<List<Event>> mAllEvents;
    private LiveData<User> mUser;

    public CalendarViewModel(@NonNull Application application) {
        super(application);
        mRepository = new EventRepository(application);
        mUserRepository = new UserRepository(application);
        mAllEvents = mRepository.getAllEvents();
        mUser = mUserRepository.getUser();
    }

    public void insert(Event event) {
        mRepository.insert(event);
    }
    public LiveData<User> getUser() {return mUserRepository.getUser();}
    public LiveData<List<Event>> getmAllEvents() { return mAllEvents;}
}