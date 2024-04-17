package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Recipe;

@RestController
@RequestMapping("/recipes")
public class Controller {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = jdbcTemplate.query("SELECT * FROM SavedRecipes", BeanPropertyRowMapper.newInstance(Recipe.class));
        return recipes;
    }

    @GetMapping("/getRecipesByUsername")
    public List<Recipe> getRecipesByUsername(@RequestParam String username) {
        String sql = "SELECT * FROM SavedRecipes WHERE username = ?";
        List<Recipe> recipes = jdbcTemplate.query(sql, new Object[]{username}, BeanPropertyRowMapper.newInstance(Recipe.class));
        return recipes;
    }

    @GetMapping("/userPreferences")
    public List<String> getUserPreferences(@RequestParam String username) {
        String sql = "SELECT preferences FROM UserPreferences WHERE user_id = (SELECT user_id FROM Users WHERE username = ?)";
        List<String> preferences = jdbcTemplate.queryForList(sql, String.class, username);
        return preferences;
    }


    @PostMapping("/addRecipe")
    public void addRecipe(@RequestBody Recipe recipe) {
        jdbcTemplate.update(
            "INSERT INTO SavedRecipes (recipe_name, username) VALUES (?, ?)",
            recipe.getRecipeName(),
            recipe.getUsername()
        );
    }
}