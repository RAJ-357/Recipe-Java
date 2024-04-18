package com.example.demo.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @GetMapping("/home")
    public String browseRecipes() {
        return "Home"; 
    }

    @GetMapping("/cusines")
    public String getCusine() {
        return "Cusines"; 
    }

    @GetMapping("/cuisine_call")
    public String getCusineCall() {
        return "cusines call"; 
    }

    @GetMapping("/user-input")
    public String userinput() {
        return "user input";
    }

    @GetMapping("/")
    public String login() {
        return "Login";
    }

    @GetMapping("/userPreference")
    public String searchPage() {
        return "UserPreference";
    }

    @GetMapping("/savedRecipes")
    public String showAddRecipePage() {
        return "SavedRecipes";
    }

    @GetMapping("/displayItems")
    public String displayLocalStorage() {
        return "display_items";
    }

    @GetMapping("/allrecipes")
    public String getallrecipes() {
        return "card";
    }

    @GetMapping("/recipe-details")
    public String getdetails() {
        return "recipe_details";
    }
    
    
}
