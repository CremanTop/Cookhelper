package com.teamtb.cookhelper.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.teamtb.cookhelper.R;
import com.teamtb.cookhelper.RecipeActivity;
import com.teamtb.cookhelper.SearchRecipeActivity;
import com.teamtb.cookhelper.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageButton recipeButton;
    private TextView recipeText;
    private IngredientsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        final EditText inputField = binding.inputField; // поле для ввода текста пользователем
        final ListView listView = binding.listView;// список ингридиентов
        recipeButton = binding.buttonRecipe;
        recipeText = binding.textRecipe;

        adapter = new IngredientsAdapter(this.requireContext(), recipeButton, recipeText);
        listView.setAdapter(adapter); // устанавливаем свой адаптер для отображения и логики элементов в списке

        if (adapter.getListIngredients().size() == 0) {
            recipeButton.setVisibility(View.INVISIBLE);
            recipeText.setVisibility(View.INVISIBLE);
        }

        // обработчик ввода текста
        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = v.getText().toString();
                if (text.length() == 0) {
                    Toast.makeText(v.getContext(), getString(R.string.void_input), Toast.LENGTH_SHORT).show();
                    return false;
                }
                System.out.println("Введено: " + text);
                adapter.addElement(text); // добавляем ингридиент

                v.setText(""); // очищаем поле ввода

                recipeButton.setVisibility(View.VISIBLE);
                recipeText.setVisibility(View.VISIBLE);

                return true;
            }
        });

        recipeButton.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                //Toast.makeText(v.getContext(), adapter.getListIngredients().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), SearchRecipeActivity.class);
                intent.putStringArrayListExtra("list_ingredients", adapter.getListIngredients());
                startActivity(intent);
            }
        });



        //Toast.makeText(this.getContext(), "onCreateView", Toast.LENGTH_SHORT).show();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Toast.makeText(this.getContext(), "onDestroyView", Toast.LENGTH_SHORT).show();
        binding = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.put
    }
}