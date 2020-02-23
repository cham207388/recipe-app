package com.abc.recipemainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final Environment environment;

    @GetMapping(path = "/index")
    public String index(Model model){
        model.addAttribute("project", environment.getProperty("project"));
        return "index";
    }
}
