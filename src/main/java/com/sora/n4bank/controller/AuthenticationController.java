package com.sora.n4bank.controller;

import com.sora.n4bank.form.LoginForm;
import com.sora.n4bank.model.User;
import com.sora.n4bank.repository.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    private UserDAO userDAO;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "next", defaultValue = "/") String nextUrl) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("nextUrl", nextUrl);
        return "auth/login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute @Valid LoginForm loginForm, BindingResult errors, Model model, HttpSession session, @RequestParam(name = "next", defaultValue = "/") String nextUrl) {
        if (errors.hasErrors()) {
            model.addAttribute("loginForm", loginForm);
            return "auth/login";
        }

        // thực hiện kiểm tra thông tin xác thực
        User user = userDAO.get(loginForm.getUsername(), loginForm.getPassword());

        if (user == null) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác");
            return "auth/login";
        }
        logger.info(nextUrl);
        session.setAttribute("userId", user.getId());
        return "redirect:" + nextUrl;
    }
}
