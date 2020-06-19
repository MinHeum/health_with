package com.example.healthwith.ui.calendar;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthwith.R;
import com.example.healthwith.db.User;
import com.example.healthwith.ui.calendar.decorator.SaturdayDecorator;
import com.example.healthwith.ui.calendar.decorator.SundayDecorator;
import com.example.healthwith.ui.menu.ProfileDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {

    private MaterialCalendarView calendarView;
    private FloatingActionButton calendarAddButton, calendarMealAddButton, calendarWorkoutAddButton, calendarDetailButton;
    private boolean isVisible;
    private ObjectAnimator sub1OpenAnim, sub2OpenAnim, sub3OpenAnim, sub1CloseAnim, sub2CloseAnim, sub3CloseAnim, calendarAddOpenAnim, calendarAddCloseAnim;

    private DetailDialog detailDialog;
    private WorkoutDialog workoutDialog;
    private ProfileDialog profileDialog;
    private FoodDialog foodDialog;

    private Button profileButton;

    private Float userWeight;

    private CalendarViewModel calendarViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarViewModel.getUser().observe(getViewLifecycleOwner(), v -> userWeight = v.getWeight());

        /*
        CalendarView UI 시작
         */
        Calendar mCalendar = Calendar.getInstance();
        Date today = mCalendar.getTime();
        calendarView = root.findViewById(R.id.materialCalendarView);
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2020, 0, 1))
                .setMaximumDate(CalendarDay.from(2100, 11,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        // Calendar 에서 주말 색상표현
        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator()
        );

        calendarView.setSelectedDate(CalendarDay.today());



        /*
        FAB 버튼 UI 시작
         */
        isVisible = false;
        calendarAddButton = root.findViewById(R.id.calendarFAB);
        calendarMealAddButton = root.findViewById(R.id.calendarFAB_sub1);
        calendarWorkoutAddButton = root.findViewById(R.id.calendarFAB_sub2);
        calendarDetailButton = root.findViewById(R.id.calendarFAB_sub3);

        /*
        Declaration of ObjectAnimators for FAB_subs and FAB
         */
        float dp = Resources.getSystem().getDisplayMetrics().density;

        sub1OpenAnim = ObjectAnimator.ofFloat(calendarMealAddButton, "translationX", -70f * dp);
        sub2OpenAnim = ObjectAnimator.ofFloat(calendarWorkoutAddButton, "translationX", -140f * dp);
        sub3OpenAnim = ObjectAnimator.ofFloat(calendarDetailButton, "translationX", -210f * dp);

        sub1CloseAnim = ObjectAnimator.ofFloat(calendarMealAddButton, "translationX", 0);
        sub2CloseAnim = ObjectAnimator.ofFloat(calendarWorkoutAddButton, "translationX", 0);
        sub3CloseAnim = ObjectAnimator.ofFloat(calendarDetailButton, "translationX", 0);

        calendarAddOpenAnim = ObjectAnimator.ofFloat(calendarAddButton, "rotation", 45);
        calendarAddCloseAnim = ObjectAnimator.ofFloat(calendarAddButton, "rotation", 0);


        calendarAddButton.setOnClickListener(v -> {
            if (!isVisible) {
                sub1CloseAnim.cancel();
                sub2CloseAnim.cancel();

                calendarMealAddButton.setVisibility(View.VISIBLE);
                calendarWorkoutAddButton.setVisibility(View.VISIBLE);
                calendarDetailButton.setVisibility(View.VISIBLE);

                calendarMealAddButton.setClickable(true);
                calendarWorkoutAddButton.setClickable(true);
                calendarDetailButton.setClickable(true);

                sub1OpenAnim.start();
                sub2OpenAnim.start();
                sub3OpenAnim.start();

                calendarAddOpenAnim.start();
                isVisible = true;
            }
            else {
                sub1OpenAnim.cancel();
                sub2OpenAnim.cancel();
                sub3OpenAnim.cancel();

                calendarMealAddButton.setClickable(false);
                calendarWorkoutAddButton.setClickable(false);
                calendarDetailButton.setClickable(false);

//              calendarMealAddButton.setVisibility(View.INVISIBLE);
//              calendarWorkoutAddButton.setVisibility(View.INVISIBLE);

                sub1CloseAnim.start();
                sub2CloseAnim.start();
                sub3CloseAnim.start();

                calendarAddCloseAnim.start();
                isVisible = false;
            }
        });

        /*
        FAB 버튼으로부터 나온 Dialog들을 호출하는 함수 세개
         */

        calendarDetailButton.setOnClickListener(v -> {
            detailDialog = new DetailDialog(root.getContext());
            detailDialog.show();
        });

        calendarWorkoutAddButton.setOnClickListener(v ->{
            workoutDialog = new WorkoutDialog(root.getContext(), calendarViewModel, userWeight);
            workoutDialog.show();
        });



        calendarMealAddButton.setOnClickListener(v -> {
            foodDialog = new FoodDialog(root.getContext(), calendarViewModel);
            foodDialog.show();
        });






         /*
        FAB 버튼 UI 끝
         */



        return root;
    }


}
