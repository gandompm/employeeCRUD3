package com.root.springboot.demo.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    public DemoController() {
    }

    @GetMapping({"/hello"})
    public String sayHello(Model theModel) {
        theModel.addAttribute("theDate", new Date());
        return "helloworld";
    }
}
