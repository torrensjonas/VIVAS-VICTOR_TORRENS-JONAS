package com.backend.clinicaodontologica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
      @GetMapping("/")
    public String index() {
        return "index"; // Esto hace referencia a tu archivo index.html
    }
}