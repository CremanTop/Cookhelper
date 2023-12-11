package com.teamtb.cookhelper.back.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RecipesBook { // синглтон
    private static RecipesBook INSTANCE;

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ArrayList<Recipe> RECIPES;

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
        return getInstance().RECIPES;
    }

    public void initRecipes(ArrayList<Recipe> recipes) {
        RECIPES = recipes;
    }

    public static Recipe getRecipeFromId(int id) {
        for (Recipe recipe : getInstance().RECIPES) {
            if (recipe.getId() == id) return recipe;
        }
        return getInstance().RECIPES.get(0);
    }
}
