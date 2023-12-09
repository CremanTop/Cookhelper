package com.teamtb.cookhelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.teamtb.cookhelper.databinding.ActivitySearchRecipeBinding;
import com.teamtb.cookhelper.ui.recipe_list.SearchRecipeAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchRecipeActivity extends AppCompatActivity {

    private ActivitySearchRecipeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<String> ingredients = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ingredients = extras.getStringArrayList("list_ingredients");
        }

        final ListView listView = binding.listRecipe;// список рецептов
        SearchRecipeAdapter adapter = new SearchRecipeAdapter(this, Arrays.asList("f", "a", "b"), ingredients);
        listView.setAdapter(adapter); // устанавливаем свой адаптер для отображения и логики элементов в списке

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchRecipeActivity.this, RecipeActivity.class);
                //intent.putStringArrayListExtra("list_ingredients", adapter.getListIngredients());
                startActivity(intent);
                Toast.makeText((Context) SearchRecipeActivity.this, (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}