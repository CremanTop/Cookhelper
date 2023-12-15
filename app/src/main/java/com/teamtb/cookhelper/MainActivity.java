package com.teamtb.cookhelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.teamtb.cookhelper.back.recipe.Recipe;
import com.teamtb.cookhelper.back.recipe.RecipesBook;
import com.teamtb.cookhelper.databinding.ActivityMainBinding;
import com.teamtb.cookhelper.locale.LocaleHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        boolean isNightMode = sharedPreferences.getBoolean("night_mode", true);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if (RecipesBook.getRecipes().size() == 0) initRecipeBook();

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private void initRecipeBook() {
        RecipesBook book = RecipesBook.getInstance();
        ArrayList<Recipe> list = new ArrayList<>();

        for (int i = 0; i <= 400; i++) {
            StringBuilder result = new StringBuilder().append("");
            try {
                InputStream inputStream = getAssets().open("recipes/"+i+".json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                inputStream.close();
                list.add(RecipesBook.parseRecipe(result.toString()));
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        book.initRecipes(list);
    }
}