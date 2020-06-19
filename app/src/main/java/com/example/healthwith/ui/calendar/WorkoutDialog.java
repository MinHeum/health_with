package com.example.healthwith.ui.calendar;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthwith.R;
import com.example.healthwith.db.Event;
import com.example.healthwith.db.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutDialog extends Dialog {
    private Context context;
    private Button confirmButton, cancelButton;
    private Spinner spinner;
    private NumberPicker numberPicker;
    private TextView kCalTextView;

    private User user;
    private Float userWeight;

    private CalendarViewModel calendarViewModel;

    public WorkoutDialog(@NonNull Context context, CalendarViewModel calendarViewModel, Float userWeight) {
        super(context);
        this.context = context;
        this.calendarViewModel = calendarViewModel;
        this.userWeight = userWeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendar_workout_dialog);


        Resources workoutRes = context.getResources();
        String[] arrString = workoutRes.getStringArray(R.array.workout_array);
        List<String> workoutArray = new ArrayList<>();
        for (String s:arrString){workoutArray.add(s);}


        //init
        confirmButton = (Button) findViewById(R.id.workoutConfirmBtn);
        cancelButton = (Button) findViewById(R.id.workoutCancelBtn);
        spinner = (Spinner) findViewById(R.id.workOutSpinner);
        numberPicker = (NumberPicker) findViewById(R.id.workoutNumberPicker);
        kCalTextView = (TextView) findViewById(R.id.dialogkCalConsumeTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getContext() , R.array.workout_array_name, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kCalTextView.setText(String.valueOf(userWeight*3.5* Float.parseFloat(workoutArray.get(position))*numberPicker.getValue()/40));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                int temp = value * 5;
                return ""+temp;
            }
        };

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                kCalTextView.setText(String.valueOf(userWeight*3.5* Float.parseFloat(workoutArray.get(spinner.getSelectedItemPosition()))*newVal/40));
            }
        });

        numberPicker.setFormatter(formatter);
        numberPicker.setMaxValue(60);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);

        confirmButton.setOnClickListener(v -> recordWorkOut(kCalTextView));

        cancelButton.setOnClickListener(v -> this.dismiss());

    }

    private void recordWorkOut(TextView input) {
        TextView kcal = input;
        float kCalFloat = Float.parseFloat(kcal.getText().toString());
        calendarViewModel.insert(new Event(1, new Date(), kCalFloat));
        this.dismiss();
    }
}
