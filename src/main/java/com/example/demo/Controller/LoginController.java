package com.example.demo.Controller;

import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;

@Controller
public class LoginController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @RequestMapping("/home")
    public String homePage() {
        return "Home"; 
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            user = null;
        }

        if (user == null) {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "login";
        } else {
            return "redirect:/home"; // Redirect to home page after successful login
        }
    }
}
