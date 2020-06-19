package com.example.healthwith.ui.menu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthwith.R;
import com.example.healthwith.db.User;

public class ProfileDialog extends Dialog {
    private Context context;
    private Button confirmBtn, cancelBtn;
    private TextView nameTextView, heightTextView, weightTextView,ageTextView;
    RadioButton maleRadioBtn, femaleRadioBtn;

    private MenuViewModel menuViewModel;

    public ProfileDialog(@NonNull Context context, MenuViewModel menuViewModel) {
        super(context);
        this.context = context;
        this.menuViewModel = menuViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_menu_set_profile);

        //init
        nameTextView = findViewById(R.id.editTextTextPersonName);
        heightTextView = findViewById(R.id.editTextHeight);
        weightTextView = findViewById(R.id.editTextWeight);
        ageTextView = findViewById(R.id.editTextAge);
        maleRadioBtn = findViewById(R.id.radioBtnMale);
        femaleRadioBtn = findViewById(R.id.radioBtnFemale);
        confirmBtn = findViewById(R.id.editProfileConfirmBtn);
        cancelBtn = findViewById(R.id.editProfileCancelBtn);

        // 남성, 여성 중 하나만 체크 가능하게 만드는 기능
        maleRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    femaleRadioBtn.setChecked(false);
                }
            }
        });
        femaleRadioBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    maleRadioBtn.setChecked(false);
                }
            }
        });


        // 취소 버튼을 누르면 dialog 를 닫습니다.
        cancelBtn.setOnClickListener(v -> this.dismiss());

        confirmBtn.setOnClickListener(v ->
                menuViewModel.insertUser(new User(nameTextView.getText().toString(),
                Float.valueOf(heightTextView.getText().toString()),
                Float.valueOf(weightTextView.getText().toString()),
                Integer.valueOf(ageTextView.getText().toString()),
                maleRadioBtn.isChecked()?true:false))
        );

    }
}
