package it.aulab.springbootcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// @RequestMapping()
public class RootController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("title", "Homepage");
        return "index";
    }
    
}
