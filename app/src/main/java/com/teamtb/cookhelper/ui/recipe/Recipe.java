package com.teamtb.cookhelper.ui.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Recipe
{
    private String name;
    private int id;
    private ArrayList<String> announce = new ArrayList<>();
    private ArrayList<String> description = new ArrayList<>();
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<String> steps = new ArrayList<>();

    public Recipe(String name, int id, ArrayList<String> description, ArrayList<String> announce, ArrayList<Ingredient> ingredients, ArrayList<String> steps)
    {
        setName(name);
        this.id = id;
        this.announce = announce;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<String> getDescription()
    {
        return description;
    }

    public ArrayList<String> getAnnounce()
    {
        return announce;
    }

    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public ArrayList<String> getSteps()
    {
        return steps;
    }

    @Override
    public String toString()
    {
        return "parser.Recipe{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", announce='" + announce + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }

    public String createJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

