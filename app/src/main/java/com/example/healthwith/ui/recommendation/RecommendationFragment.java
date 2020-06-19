package com.example.healthwith.ui.recommendation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.healthwith.R;
import com.example.healthwith.ui.menu.MenuViewModel;

public class RecommendationFragment extends Fragment {

    private RecommendationViewModel recommendationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recommendationViewModel = new ViewModelProvider(this).get(RecommendationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recommendation, container, false);



        return root;
    }
}
