package com.example.interceptormiddleware02.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/root")
public class BasicController {
    @GetMapping
    public String welcome(){
        return "welcome!";
    }
}
