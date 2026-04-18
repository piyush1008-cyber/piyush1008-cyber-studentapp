package com.piyush.studentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/ui")
    public String ui() {
        return "ui";
    }
}
