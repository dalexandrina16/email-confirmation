package com.utm.tw.controller;

import com.utm.tw.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping("/")
    public ModelAndView landing() {
        return new ModelAndView("main.html");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("registration.html");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home.html");
    }

}
