package com.sergeystets.vladex.web.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}
