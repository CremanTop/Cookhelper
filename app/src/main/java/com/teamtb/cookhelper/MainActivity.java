package com.teamtb.cookhelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.teamtb.cookhelper.databinding.ActivityMainBinding;
import com.teamtb.cookhelper.locale.LocaleHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // устанавливаем стандартный язык
        String lan = LocaleHelper.getLanguage(this);
        TextView control_text = findViewById(R.id.text_recipe);
        if (control_text != null) {
            switch (lan) {
                case "en":
                    if (!control_text.getText().toString().equals("Choose a recipe")) {
                        LocaleHelper.onCreate(this, lan);
                        recreate();
                    }
                    break;
                case "ru":
                    if (!control_text.getText().toString().equals("Подобрать рецепт")) {
                        LocaleHelper.onCreate(this, lan);
                        recreate();
                    }
                    break;
            }
        }

        SharedPreferences sharedPreferences = getSharedPreferences("night", 0);
        Boolean isNightMode = sharedPreferences.getBoolean("night_mode", true);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
}