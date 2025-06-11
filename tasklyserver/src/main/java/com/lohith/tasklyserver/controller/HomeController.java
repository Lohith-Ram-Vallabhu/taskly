package com.lohith.tasklyserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping()
    @ResponseBody
    public String welcome() {
        logger.info("Welcome endpoint accessed");
        return "Welcome to Taskly!";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Test endpoint accessed");
        return "Test endpoint working!";
    }
}