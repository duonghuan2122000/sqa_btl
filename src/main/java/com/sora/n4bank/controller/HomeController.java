package com.sora.n4bank.controller;

import com.sora.n4bank.annotation.AdminAuth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class HomeController {

    @AdminAuth
    @GetMapping("/")
    public String home(HttpSession session, HttpServletRequest request, Model model, @RequestParam(name = "name", defaultValue = "Huân") String name){
//        model.addAttribute("name", name);
//        return "home";
        return "redirect:/loans";
    }

    @AdminAuth
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, Model model){
        UUID userId = (UUID) session.getAttribute("userId");
        if(userId != null){
            session.removeAttribute("userId");
        }

        return "redirect:/login";
    }
}
