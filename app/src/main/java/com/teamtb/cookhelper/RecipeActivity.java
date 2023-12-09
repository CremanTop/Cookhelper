package com.teamtb.cookhelper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.teamtb.cookhelper.back.recipe.Recipe;
import com.teamtb.cookhelper.back.recipe.RecipeHandler;
import com.teamtb.cookhelper.back.recipe.RecipesBook;
import com.teamtb.cookhelper.databinding.ActivityRecipeBinding;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRecipeBinding binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int recipe_id = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recipe_id = extras.getInt("recipe_id");
        }

        Recipe recipe = RecipesBook.getRecipeFromId(recipe_id);

        String str_recipe = RecipeHandler.getStringAnnounce(recipe) + RecipeHandler.getStringDescription(recipe) + RecipeHandler.getStringIngredients(recipe) + RecipeHandler.getStringSteps(recipe);


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(recipe.getName());

        FloatingActionButton fab = binding.fab;
        binding.layoutScrollingRecipe.textRecipe.setText(str_recipe);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}