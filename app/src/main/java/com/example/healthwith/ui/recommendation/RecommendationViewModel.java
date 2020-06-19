package com.example.healthwith.ui.recommendation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthwith.db.Event;
import com.example.healthwith.db.EventRepository;
import com.example.healthwith.db.User;
import com.example.healthwith.db.UserRepository;

import java.util.List;

public class RecommendationViewModel extends AndroidViewModel {

    private EventRepository mRepository;
    private UserRepository mUserRepository;

    private LiveData<List<Event>> mAllEvents;
    private LiveData<User> mUser;

    public RecommendationViewModel(@NonNull Application application) {
        super(application);
        mRepository = new EventRepository(application);
        mUserRepository = new UserRepository(application);
        mAllEvents = mRepository.getAllEvents();
        mUser = mUserRepository.getUser();
    }

    LiveData<List<Event>> getmAllEvents() { return mAllEvents; }
    public void insert(Event event) { mRepository.insert(event); }
    public void deleteAll() {mRepository.deleteAll();}
    LiveData<User> getUser () {return mUser;}
    public void insertUser(User user) {mUserRepository.insert(user);}
}