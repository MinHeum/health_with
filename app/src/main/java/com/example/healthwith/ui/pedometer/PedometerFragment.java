package com.example.healthwith.ui.pedometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.healthwith.R;

public class PedometerFragment extends Fragment implements SensorEventListener { // implement 센서 값 받음

    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    TextView stepsTextView,kcalTextView; //걸음 수
    private int stepsCounter; //리스너 등록 후
    private int steps;
    private ProgressBar progressBar;

    private Button pedometerResetButton;

    private PedometerViewModel pedometerViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pedometerViewModel = new ViewModelProvider(this).get(PedometerViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedometer, container, false);
        stepsTextView = root.findViewById(R.id.pedometerStepsTextView);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        kcalTextView = root.findViewById(R.id.pedometerKCalTextView);

        // pedometer Fragment에서 RESET 버튼을 눌렀을 때
        pedometerResetButton= root.findViewById(R.id.pedometerResetButton);
        pedometerResetButton.setOnClickListener(v -> {
            steps=0;
            stepsCounter=0;
            stepsTextView.setText(Integer.toString(steps));
        });

        if (stepCountSensor == null) {
            Toast.makeText(root.getContext(), "No step Detect Sensor", Toast.LENGTH_SHORT).show();
        }
        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setProgress(steps);
        System.out.println(progressBar.getProgress());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener)this, stepCountSensor, sensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) { //센서 동작 감지시 이벤트 발생하여 이 함수에 값 전달
        /*
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            //stepsTextView.setText(String.valueOf(event.values[0]));
            stepsTextView.setText(String.valueOf((int)event.values[0]));
            steps=Integer.valueOf((int) event.values[0]);
            //stepsProgressBar.setProgress(steps);
            kcalTextView.setText("예상"+steps+"kcal 소모");
        }
         */

        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){

                //stepcountsenersor는 앱이 꺼지더라도 초기화 되지않는다. 그러므로 우리는 초기값을 가지고 있어야한다.
            if (stepsCounter < 1) {
                // initial value
                stepsCounter = (int) event.values[0];
            }
            //리셋 안된 값 + 현재값 - 리셋 안된 값
            steps = (int) event.values[0] - stepsCounter;
            stepsTextView.setText(Integer.toString(steps));
            Log.i("log: ", "New step detected by STEP_COUNTER sensor. Total step count: " + steps );

            kcalTextView.setText("예상"+steps+"kcal 소모");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}