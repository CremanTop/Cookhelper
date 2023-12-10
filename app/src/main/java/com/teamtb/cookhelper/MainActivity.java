package com.teamtb.cookhelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
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
        //LocaleHelper.onCreate(this, "en");


//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        int theme = prefs.getInt("theme", R.style.Theme_CookHelper);
//
//        // Устанавливаем выбранную тему
//        setTheme(theme);


        //LocaleHelper.setLocale(this,"en");
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);


//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String language = "";//preferences.getString("language", "en-rUS");
//        LocaleHelper.setLocale(this, language);
        //setContentView(binding.getRoot());

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }
}