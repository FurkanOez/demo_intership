package com.github.fukanoez.demo_intership.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class HelloWorldController {

    @GetMapping("/HelloWorld")
    public String helloWorldAndCurrentDate(@RequestParam(value = "name" , defaultValue = "Kardes")String name) {
        return "Hello " + name;
    }

}
