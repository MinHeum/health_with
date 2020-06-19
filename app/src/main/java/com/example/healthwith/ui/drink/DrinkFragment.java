package com.example.healthwith.ui.drink;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthwith.R;
import com.example.healthwith.db.Event;

import java.util.Calendar;
import java.util.Date;

public class DrinkFragment extends Fragment {

    private TextView textView;
    private SeekBar seekBar;
    private int waterAmount;
    private Button sendButton;
    private DrinkViewModel drinkViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drinkViewModel = new ViewModelProvider(this).get(DrinkViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_drink, container, false);
        textView = (TextView) root.findViewById(R.id.seekbarValue);
        textView.setText("0mL");

        // seekBar 진동을 위해 생성
        final Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        // 아이콘 투명 효과를 주기 위한 변수 생성
        final ImageView mDropImage = root.findViewById(R.id.drinkIcon_drop);
        final ImageView mCupImage = root.findViewById(R.id.drinkIcon_cup);
        final ImageView mBottleImage = root.findViewById(R.id.drinkIcon_pet);

        seekBar = root.findViewById(R.id.drink_seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //waterAmount 변수는 마신 물의 양을 담는 int 변수입니다. db로 넘겨줄 때 해당 변수를 통해 넘겨주면 됩니다.
            waterAmount = seekBar.getProgress()*100;
            //100 단위로 입력받을 값을
            textView.setText(String.valueOf(waterAmount+"mL"));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrateLittle(vibrator);
            }

             /*  waterAmount 에 따른 좌측 아이콘 표시 변화주기
             *  100, 200 = 물방울, 300, 400 컵, 500 부터는 페트병 모양을 줄 예정
             *  switch 를 사용해서 구별
             */
            switch (waterAmount) {
                case 0:
                case 100:
                case 200:
                    mDropImage.setVisibility(View.VISIBLE); mCupImage.setVisibility(View.INVISIBLE); mBottleImage.setVisibility(View.INVISIBLE); break;
                case 300:
                case 400:
                    mDropImage.setVisibility(View.INVISIBLE); mCupImage.setVisibility(View.VISIBLE); mBottleImage.setVisibility(View.INVISIBLE); break;
                case 500: mDropImage.setVisibility(View.INVISIBLE); mCupImage.setVisibility(View.INVISIBLE); mBottleImage.setVisibility(View.VISIBLE); break;
            }

        }

        // 최초에 탭하여 드래그 시작할때 발생
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(10,10));
            }
        }
        // 드래그를 멈출때 발생
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrateLittle(vibrator);
            }
        }
    });

        // db에 insert 하기 위한 button 인스턴스를 정의했습니다.
        sendButton = (Button) root.findViewById(R.id.drink_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 waterAmount 를 테스트하기위한 코드입니다.
                System.out.println(waterAmount);
                Date currDate = Calendar.getInstance().getTime();
                drinkViewModel.insert(new Event(3, currDate, waterAmount));
                if (waterAmount == 0) {
                    Toast.makeText(root.getContext(), "0 이상의 값만 기록해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(root.getContext(), "기록되었습니다.", Toast.LENGTH_SHORT).show();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrateLittle(vibrator);
                }
            }
        });
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void vibrateLittle(Vibrator vibrator){
        vibrator.vibrate(VibrationEffect.createOneShot(10,200));
    }


}
