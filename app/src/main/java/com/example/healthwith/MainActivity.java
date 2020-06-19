package com.example.healthwith;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.healthwith.db.AppDatabase;
import com.example.healthwith.db.User;
import com.example.healthwith.ui.menu.MenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_pedometer,
                R.id.navigation_drink,
                R.id.navigation_calendar,
                R.id.navigation_recommendation,
                R.id.navigation_menu)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        AppDatabase db = AppDatabase.getDatabase(this);
    }
}
