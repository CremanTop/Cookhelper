package com.teamtb.cookhelper.back.recipe;

import java.util.ArrayList;

public abstract class RecipeHandler {
    private static String getStringInArray(ArrayList<String> list) {
        StringBuilder text = new StringBuilder();

        switch (list.size()) {
            case 0:
                break;
            case 1:
                text.append(list.get(0)).append("\n\n");
                break;
            default:
                for (String elem : list) {
                    text.append(elem).append("\n");
                }
                text.append("\n");
        }
        return text.toString();
    }
    public static String getStringDescription(Recipe recipe) {
        return getStringInArray(recipe.getDescription());
    }

    public static String getStringAnnounce(Recipe recipe) {
        return getStringInArray(recipe.getAnnounce());
    }

    public static String getStringIngredients(Recipe recipe) {
        StringBuilder text = new StringBuilder();
        for (Ingredient ingredient : recipe.getIngredients()) {
            text.append("• ").append(ingredient).append("\n");
        }
        text.append("\n");
        return text.toString();
    }

    public static String getStringSteps(Recipe recipe) {
        StringBuilder text = new StringBuilder();
        int index = 0;
        for (String step : recipe.getSteps()) {
            text.append(++index).append(". ").append(step).append("\n");
        }
        text.append("\n");
        return text.toString();
    }
}
