package com.example.healthwith.ui.calendar;

import android.app.Application;
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
import androidx.annotation.Nullable;

import com.example.healthwith.R;
import com.example.healthwith.db.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodDialog extends Dialog {
    private Context context;
    private Button confirmButton, cancelButton;
    private Spinner spinner;
    private NumberPicker numberPicker;
    private TextView kCalTextView, howManyFood;

    private CalendarViewModel calendarViewModel;

    public FoodDialog(@NonNull Context context, CalendarViewModel calendarViewModel) {
        super(context);
        this.context = context;
        this.calendarViewModel = calendarViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_calendar_food_dialog);

        Resources foodRes = context.getResources();
        String[] arrString = foodRes.getStringArray(R.array.food_array);
        List<String> foodArray = new ArrayList<>();
        for (String s:arrString){foodArray.add(s);}

        //init
        confirmButton = (Button) findViewById(R.id.foodConfirmBtn);
        cancelButton = (Button) findViewById(R.id.foodCancelBtn);
        spinner = (Spinner) findViewById(R.id.foodSpinner);
        numberPicker = (NumberPicker) findViewById(R.id.foodNumberPicker);
        kCalTextView = (TextView) findViewById(R.id.dialogkCalResultTextView);
        howManyFood = (TextView) findViewById(R.id.textViewHowManyFood);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this.getContext() , R.array.food_array_name, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kCalTextView.setText(String.valueOf((Integer.parseInt(foodArray.get(position).split(",")[3])*numberPicker.getValue())));
                howManyFood.setText(foodArray.get(position).split(",")[2]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        numberPicker.setMaxValue(3);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                kCalTextView.setText(String.valueOf((Integer.parseInt(foodArray.get(spinner.getSelectedItemPosition()).split(",")[3])*newVal)));
            }
        });

        confirmButton.setOnClickListener(v -> recordFood(kCalTextView));

        cancelButton.setOnClickListener(v -> this.dismiss());

    }

    private void recordFood(TextView input) {
        TextView kcal = input;
        float kCalFloat = Float.parseFloat(kcal.getText().toString());
        calendarViewModel.insert(new Event(1, new Date(),kCalFloat));
        this.dismiss();
        }
}
