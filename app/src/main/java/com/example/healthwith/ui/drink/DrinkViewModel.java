package com.example.healthwith.ui.drink;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.healthwith.db.Event;
import com.example.healthwith.db.EventRepository;

import java.util.Date;
import java.util.List;

public class DrinkViewModel extends AndroidViewModel {

    private EventRepository mRepository;

    public DrinkViewModel(Application application) {
        super(application);
        mRepository = new EventRepository(application);
    }

    public void insert(Event event) {
        if(event.getAmount() != 0){
            mRepository.insert(event);
        }
    }

}
