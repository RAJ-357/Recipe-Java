package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;



import com.example.demo.Model.Recipe;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, Model model) {

        // Query the database to check if the username and password match
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, username, password);
        if (count == 1) {
            return new RedirectView("/home");
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return new RedirectView("/");
        }
    }

    @PostMapping("/handleRecipeRequest")
    public ResponseEntity<String> handleRecipeRequest(@RequestBody Map<String, Object> requestData) {
        // Extract data from the request
        String selectedCuisine = (String) requestData.get("selectedCuisine");
        String requiredIngredients = (String) requestData.get("requiredIngredients");
        String nonRequiredIngredients = (String) requestData.get("nonRequiredIngredients");
        Map<String, Object> filledValues = (Map<String, Object>) requestData.get("filledValues");
        String apiKey = "b64a702ee37e49169362fdd350a369da";

        // Build the Spoonacular API request URL with the API key
        String url = "https://api.spoonacular.com/recipes/complexSearch";
        StringBuilder queryParams = new StringBuilder("?apiKey=").append(apiKey);
        queryParams.append("&cuisine=").append(selectedCuisine);
        queryParams.append("&includeIngredients=").append("\"").append(requiredIngredients).append("\"");
        queryParams.append("&excludeIngredients=").append("\"").append(nonRequiredIngredients).append("\"");

        // Add protein parameters with correct formatting
        addRangeParameter(queryParams, "protein", filledValues);

        // Add carbs parameters with correct formatting
        addRangeParameter(queryParams, "carbs", filledValues);

        // Add fat parameters with correct formatting
        addRangeParameter(queryParams, "fat", filledValues);

        // Add sugar parameters with correct formatting
        addRangeParameter(queryParams, "sugar", filledValues);

        url += queryParams.toString();

        // Make the API request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
            // Handle the response
            if (response.getStatusCode() == HttpStatus.OK) {
                return response;
            } else {
                // Return URL in case of error
                return new ResponseEntity<>(url, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Return URL in case of exception
            return new ResponseEntity<>(url, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void addRangeParameter(StringBuilder queryParams, String parameterName, Map<String, Object> filledValues) {
        if (filledValues.containsKey(parameterName)) {
            Map<String, Object> parameterValues = (Map<String, Object>) filledValues.get(parameterName);
            if (parameterValues.containsKey("min") && parameterValues.containsKey("max")) {
                queryParams.append("&min").append(parameterName.substring(0, 1).toUpperCase()).append(parameterName.substring(1)).append("=")
                        .append(parameterValues.get("min")).append("&max").append(parameterName.substring(0, 1).toUpperCase()).append(parameterName.substring(1)).append("=")
                        .append(parameterValues.get("max"));
            }
        }
    }

    @PostMapping("/getRecipeDetails")
    public ResponseEntity<String> handleRecipeDetails(@RequestBody Map<String, Object> requestBody) {
        
        String apiKey = "b64a702ee37e49169362fdd350a369da";
        String recipeId = (String) requestBody.get("recipeId");

        // Build the Spoonacular API request URL with the API key
        String url = "https://api.spoonacular.com/recipes/" + recipeId + "/information";
        StringBuilder queryParams = new StringBuilder("?apiKey=").append(apiKey);


        url += queryParams.toString();

        // Make the API request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(url, String.class);
            // Handle the response
            if (response.getStatusCode() == HttpStatus.OK) {
                return response;
            } else {
                // Return URL in case of error
                return new ResponseEntity<>(url, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Return URL in case of exception
            return new ResponseEntity<>(url, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}