package com.sora.n4bank.annotation;

import com.sora.n4bank.model.User;
import com.sora.n4bank.repository.UserDAO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Aspect
@Component
public class AdminAuthAspect {

    @Autowired
    private UserDAO userDAO;

    @Around("@annotation(AdminAuth)")
    public Object adminAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpSession session = (HttpSession) joinPoint.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        Model model = (Model) joinPoint.getArgs()[2];
        String currentUrl = request.getRequestURI();
        if(request.getQueryString() != null && !request.getQueryString().isEmpty()){
            currentUrl = currentUrl + "?" + request.getQueryString();
        }
        UUID userId = (UUID) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login?next=" + currentUrl;
        }

        User user = userDAO.get(userId);
        if (user == null) {
            return "redirect:/login?next=" + currentUrl;
        }

        model.addAttribute("currentUser", user);

        Object obj = joinPoint.proceed();
        return obj;
    }
}
