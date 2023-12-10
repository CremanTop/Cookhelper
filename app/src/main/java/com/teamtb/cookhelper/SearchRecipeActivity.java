package com.teamtb.cookhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamtb.cookhelper.back.recipe.Recipe;
import com.teamtb.cookhelper.back.recipe.RecipeSearcher;
import com.teamtb.cookhelper.databinding.ActivitySearchRecipeBinding;
import com.teamtb.cookhelper.ui.adapters.SearchRecipeAdapter;

import java.util.ArrayList;

public class SearchRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySearchRecipeBinding binding = ActivitySearchRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<String> ingredients = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ingredients = extras.getStringArrayList("list_ingredients");
        }

        final ListView listView = binding.listRecipe;// список рецептов

        ArrayList<Recipe> recipes = RecipeSearcher.getRelevantRecipes(ingredients);
        ArrayList<Integer> ids = new ArrayList<>();

        for (Recipe res : recipes) {
            ids.add(res.getId());
        }

        SearchRecipeAdapter adapter = new SearchRecipeAdapter(this, ids, recipes);
        listView.setAdapter(adapter); // устанавливаем свой адаптер для отображения и логики элементов в списке

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchRecipeActivity.this, RecipeActivity.class);
                intent.putExtra("recipe_id", (Integer) parent.getItemAtPosition(position));
                intent.putExtra("favorites", true);
                startActivity(intent);
                //Toast.makeText((Context) SearchRecipeActivity.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();
            }
        });
    }
}