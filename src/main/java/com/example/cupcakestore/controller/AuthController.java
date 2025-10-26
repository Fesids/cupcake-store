package com.example.cupcakestore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.cupcakestore.service.UserService;
import com.example.cupcakestore.model.User;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService){ this.userService = userService; }

    @GetMapping("/login")
    public String loginForm(){ return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        var ou = userService.authenticate(email, password);
        if(ou.isEmpty()){
            model.addAttribute("error", "Credenciais inválidas");
            return "login";
        }
        session.setAttribute("userId", ou.get().id);
        return "redirect:/cupcakes";
    }

    @GetMapping("/register")
    public String registerForm(){ return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password, HttpSession session){
        User u = userService.register(name, email, password);
        session.setAttribute("userId", u.id);
        return "redirect:/cupcakes";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
