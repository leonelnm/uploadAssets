package com.codigo04.uploadassets.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginWebController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // TODO: Check username and password
        // Redirect to /assets
        return "redirect:/assets";
    }

    @GetMapping("/logout")
    public String logout() {
        // TODO: Clear session
        return "redirect:/";
    }

}
