package com.ttknpdev.myportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlPath {
    @GetMapping(value = "/ttknpde-v")
    private String start() {
        return "index";
    }
}
