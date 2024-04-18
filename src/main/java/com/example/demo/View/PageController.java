package com.example.demo.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
