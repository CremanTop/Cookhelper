package com.teamtb.cookhelper.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.teamtb.cookhelper.RecipeActivity;
import com.teamtb.cookhelper.back.Serializer;
import com.teamtb.cookhelper.back.recipe.Recipe;
import com.teamtb.cookhelper.back.recipe.RecipeSearcher;
import com.teamtb.cookhelper.databinding.FragmentNotificationsBinding;
import com.teamtb.cookhelper.ui.adapters.SearchRecipeAdapter;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listFavoritesRecipe;// список рецептов

        ArrayList<Integer> favorites_ids = new ArrayList<>();
        Serializer.deserializeData(this.requireContext(), "favorites.json").forEach((elem) -> favorites_ids.add((int) (double)elem));

        ArrayList<Recipe> recipes = RecipeSearcher.getRecipesFromIds(favorites_ids);

        SearchRecipeAdapter adapter = new SearchRecipeAdapter(this.requireContext(), favorites_ids, recipes);
        listView.setAdapter(adapter); // устанавливаем свой адаптер для отображения и логики элементов в списке

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NotificationsFragment.this.getContext(), RecipeActivity.class);
                intent.putExtra("recipe_id", (Integer) parent.getItemAtPosition(position));
                intent.putExtra("favorites", false);
                startActivity(intent);
                //Toast.makeText((Context) SearchRecipeActivity.this, String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}