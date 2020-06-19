package com.example.healthwith.ui.pedometer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthwith.db.Event;
import com.example.healthwith.db.EventRepository;

public class PedometerViewModel extends AndroidViewModel {

    private EventRepository mRepository;

    public PedometerViewModel(Application application) {
        super(application);
        mRepository = new EventRepository(application);
    }

    public void insert(Event event) {mRepository.insert(event);}
}