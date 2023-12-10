package com.teamtb.cookhelper;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.teamtb.cookhelper.back.Serializer;
import com.teamtb.cookhelper.back.recipe.Recipe;
import com.teamtb.cookhelper.back.recipe.RecipeHandler;
import com.teamtb.cookhelper.back.recipe.RecipesBook;
import com.teamtb.cookhelper.databinding.ActivityRecipeBinding;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRecipeBinding binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int recipe_id = 0;
        boolean add_favorites = true;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recipe_id = extras.getInt("recipe_id");
            add_favorites = extras.getBoolean("favorites");
        }

        Recipe recipe = RecipesBook.getRecipeFromId(recipe_id);

        String str_recipe = RecipeHandler.getStringAnnounce(recipe) + RecipeHandler.getStringDescription(recipe) + RecipeHandler.getStringIngredients(recipe) + RecipeHandler.getStringSteps(recipe);


        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(recipe.getName());

        FloatingActionButton fab = binding.fab;
        binding.layoutScrollingRecipe.textRecipe.setText(str_recipe);

        final boolean finalAdd_favorites = add_favorites;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> favorites = new ArrayList<>();
                Serializer.deserializeData(RecipeActivity.this, "favorites.json").forEach((elem) -> favorites.add((int) (double)elem));

                if (finalAdd_favorites) {
                    if (!favorites.contains(recipe.getId())) {
                        Snackbar.make(view, R.string.add_favorite, Snackbar.LENGTH_LONG).show();
                        favorites.add(recipe.getId());
                        Serializer.serializeData(RecipeActivity.this, "favorites.json", favorites);
                    } else {
                        Snackbar.make(view, R.string.already_favorites, Snackbar.LENGTH_LONG).show();
                    }
                    System.out.println(favorites);
                }
                else {
                    if (favorites.contains(recipe.getId())) {
                        Snackbar.make(view, R.string.remove_favorite, Snackbar.LENGTH_LONG).show();
                        favorites.remove((Object) recipe.getId());
                        Serializer.serializeData(RecipeActivity.this, "favorites.json", favorites);
                    }
                }
            }
        });
    }
}