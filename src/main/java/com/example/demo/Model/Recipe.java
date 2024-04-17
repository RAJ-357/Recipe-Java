package com.example.demo.Model;

public class Recipe {
    private Long recipeId;
    private String recipeName;
    private String username;

    // Constructors, getters, and setters

    public Recipe(Long recipeId, String recipeName, String username) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.username = username;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Recipe ID: " + recipeId + ", Name: " + recipeName + ", Username: " + username;
    }
}
