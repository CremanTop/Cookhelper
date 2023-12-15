package com.teamtb.cookhelper.back.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipesBook { // синглтон
    private static RecipesBook INSTANCE;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public HashMap<Integer, Recipe> RECIPES = new HashMap<>();

    private RecipesBook() {
    }

    public static RecipesBook getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RecipesBook();
            System.out.println("INSTANCE RECIPE_BOOK");
        }
        return INSTANCE;
    }

    public static Recipe parseRecipe(String jsonString) {
        return gson.fromJson(jsonString, Recipe.class);
    }

    public static ArrayList<Recipe> parseRecipes(String jsonString) {
        TypeToken<ArrayList<Recipe>> collectionType = new TypeToken<ArrayList<Recipe>>(){};
        return gson.fromJson(jsonString, collectionType);
    }

    public static ArrayList<Recipe> getRecipes() {
        return new ArrayList<>(getInstance().RECIPES.values());
    }

    public void initRecipes(ArrayList<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            RECIPES.put(recipe.getId(), recipe);
        }
    }

    public static Recipe getRecipeFromId(int id) {
        Recipe recipe = getInstance().RECIPES.get(id);
        return (recipe != null) ? recipe : getInstance().RECIPES.get(0);
    }
}
