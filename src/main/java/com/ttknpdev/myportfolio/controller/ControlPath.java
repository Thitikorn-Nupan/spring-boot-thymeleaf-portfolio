package com.ttknpdev.myportfolio.controller;

import com.ttknpdev.myportfolio.repository.LineNotifyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

@Controller
public class ControlPath {

    private LineNotifyRepo lineNotifyRepo;
    @Autowired
    public ControlPath(LineNotifyRepo lineNotifyRepo) {
        this.lineNotifyRepo = lineNotifyRepo;
    }

    @GetMapping
    private String start() throws Exception {
        lineNotifyRepo.sendLineNotifyMessage();
        TimeUnit.MILLISECONDS.sleep(2000);
        return "index";
    }
}
