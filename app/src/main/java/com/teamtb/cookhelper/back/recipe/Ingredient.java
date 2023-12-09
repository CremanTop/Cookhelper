package com.teamtb.cookhelper.back.recipe;

public class Ingredient
{
    private String name;
    private String count;

    public Ingredient() {
    }

    public Ingredient(String name, String count)
    {
        setName(name);
        setCount(count);
    }

    @Override
    public String toString()
    {
        return name.substring(0, 1).toUpperCase() + name.substring(1) + " - " + count;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }
}

