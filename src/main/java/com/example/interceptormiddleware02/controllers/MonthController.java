package com.example.interceptormiddleware02.controllers;

import com.example.interceptormiddleware02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/months")
public class MonthController {
    @GetMapping("/month")
    public String getMonth(@RequestAttribute("month") Month month) {
        return month.toString();
    }
}
