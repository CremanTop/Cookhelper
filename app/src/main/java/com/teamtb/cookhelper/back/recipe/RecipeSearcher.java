package com.teamtb.cookhelper.back.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class RecipeSearcher {
    public static ArrayList<Recipe> getRelevantRecipes(ArrayList<String> ingredients) {
        ArrayList<Recipe> all_recipes = RecipesBook.getRecipes();
        ArrayList<Recipe> recipes = new ArrayList<>();

        HashMap<Recipe, Float> recipeMap = new HashMap<>();

        for (Recipe recipe : all_recipes) {
            float matches = 0; // сколько ингридиентов из нужных в рецепте есть у пользователя
            for (Ingredient recipe_ingredient : recipe.getIngredients()) {
                for (String ingredient : ingredients) {
                    if (recipe_ingredient.getName().contains(ingredient)) {
                        matches++;
                        break;
                    }
                }
            }
            if (matches > 0) {
                recipeMap.put(recipe, matches / recipe.getIngredients().size()); // значение - процент совпадения ингридиентов
            }
        }

        for (Map.Entry<Recipe, Float> entry : recipeMap.entrySet().stream().sorted(Map.Entry.<Recipe, Float>comparingByValue().reversed()).collect(Collectors.toList())) {
            recipes.add(entry.getKey());
            System.out.println(entry.getKey().getName() + " = " + entry.getValue());
        }

        // список рецептов отсортированный по убыванию совпадения
        return recipes;
    }
}
