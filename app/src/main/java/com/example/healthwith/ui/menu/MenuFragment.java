package com.example.healthwith.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthwith.R;
import com.example.healthwith.db.User;

public class MenuFragment extends Fragment {

    private MenuViewModel menuViewModel;
    private Button eraseButton;
    private TextView testTextView1, testTextView2;
    private TextView BMITextView, menuBMI, AverageWater, AverageWaterValue, kCalperDay, averagekCalValue;
    private TextView userHeight, userWeight, userAge, userGender;

    private String userName;
    private Button profileButton;
    private ProfileDialog profileDialog;


    private User user;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        //init
        testTextView1 = root.findViewById(R.id.testText01);
        testTextView2 = root.findViewById(R.id.testText02);
        eraseButton = root.findViewById(R.id.eraseButton);
        profileButton = root.findViewById(R.id.profileButton);

        BMITextView = root.findViewById(R.id.BMItextView);
        menuBMI = root.findViewById(R.id.menuBmi);
        AverageWater = root.findViewById(R.id.AverageWater);
        AverageWaterValue = root.findViewById(R.id.AverageWaterValue);
        kCalperDay = root.findViewById(R.id.kCalperDay);
        averagekCalValue = root.findViewById(R.id.averagekCalValue);

        userHeight = root.findViewById(R.id.userHeight);
        userWeight = root.findViewById(R.id.userWeight);
        userAge = root.findViewById(R.id.userAge);
        userGender = root.findViewById(R.id.userGender);


        menuViewModel.getmAllEvents().observe(getViewLifecycleOwner(), events -> {testTextView1.setText(events.toString());});
        menuViewModel.getUser().observe(getViewLifecycleOwner(), users -> {testTextView2.setText(users.toString());});

        menuViewModel.getUser().observe(getViewLifecycleOwner(), v -> {
            menuBMI.setText(String.valueOf(v.getWeight() / (v.getHeight() * v.getHeight() / 10000f)));
        });

        menuViewModel.getUser().observe(getViewLifecycleOwner(),v->{
            AverageWaterValue.setText(String.valueOf(v.getWaterAverage()+" mL"));
        });

        menuViewModel.getUser().observe(getViewLifecycleOwner(), v-> {
            averagekCalValue.setText(String.valueOf(v.getkCal()+" kCal"));
        });

        menuViewModel.getUser().observe(getViewLifecycleOwner(), v-> {
            userHeight.setText(String.valueOf(v.getHeight()+" cm"));
        });
        menuViewModel.getUser().observe(getViewLifecycleOwner(), v-> {
            userWeight.setText(String.valueOf(v.getWeight()+" kg"));
        });
        menuViewModel.getUser().observe(getViewLifecycleOwner(), v-> {
            userAge.setText(String.valueOf(v.getAge()+" 세"));
        });
        menuViewModel.getUser().observe(getViewLifecycleOwner(), v-> {
            userGender.setText(v.isMale()?"남성":"여성");
        });



        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuViewModel.deleteAll();
            }
        });


        profileDialog = new ProfileDialog(root.getContext(), menuViewModel);
        profileButton.setOnClickListener(v -> {
            profileDialog.setContentView(R.layout.fragment_menu_set_profile);
            profileDialog.show();
        });


        return root;
    }
}
