package com.example.healthwith.ui.calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthwith.R;
import com.example.healthwith.db.Event;
import com.example.healthwith.db.EventRepository;

import java.util.List;

public class DetailDialog extends Dialog {

    private Context context;
    private Button confirmButton, cancelButton;
    private RecyclerView recyclerView;

    private EventRepository eventRepository;

    public DetailDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_calendar_detail_dialog);

        //init
        confirmButton = (Button) findViewById(R.id.detailConfirmBtn);
        cancelButton = (Button) findViewById(R.id.detailCancleBtn);

        cancelButton.setOnClickListener(v -> this.dismiss());

    }
}
