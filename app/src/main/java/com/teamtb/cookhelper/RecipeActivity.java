package com.teamtb.cookhelper;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.teamtb.cookhelper.databinding.ActivityRecipeBinding;
import com.teamtb.cookhelper.ui.recipe.Recipe;
import com.teamtb.cookhelper.ui.recipe.RecipeHandler;
import com.teamtb.cookhelper.ui.recipe.RecipesBook;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private ActivityRecipeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        String inputFileName = "C:/Users/timab/AndroidStudioProjects/Cookhelper/app/src/main/java/com/teamtb/cookhelper/files/44387.json";
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        ArrayList<String> yyy = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            yyy = extras.getStringArrayList("list_ingredients");
        }

        Recipe recipe = RecipesBook.parseRecipe(RecipesBook.RECIPE_1);
        StringBuilder str_recipe = new StringBuilder(RecipeHandler.getStringAnnounce(recipe) + RecipeHandler.getStringDescription(recipe) + RecipeHandler.getStringIngredients(recipe) + RecipeHandler.getStringSteps(recipe));

        if (yyy != null) {
            for (String s : yyy) {
                str_recipe.append(s).append(" ");
            }
        }


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(recipe.getName());

        FloatingActionButton fab = binding.fab;
        binding.layoutScrollingRecipe.textRecipe.setText(str_recipe.toString());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}