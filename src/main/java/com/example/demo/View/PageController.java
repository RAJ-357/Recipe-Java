package com.example.demo.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/browse")
    public String browseRecipes() {
        return "browse"; 
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search";
    }

    @GetMapping("/addRecipe")
    public String showAddRecipePage() {
        return "addrecipe";
    }
}
