package com.waracle.cakemgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CakeUIController {
    
    @GetMapping("/")
    public String cakeIndex() {
        return "index";
    } 

    
}
