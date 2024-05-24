package com.example.gticslab620212607.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value = {"/openLoginWindow","","/"})
    public String loginForm(){
        return "loginWindow";
    }
}
