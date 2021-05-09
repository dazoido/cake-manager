package com.waracle.cakemgr.controller;

import java.util.List;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CakeUIController {
    
    private CakeRepository cakeRepository;
    
    @Autowired
    public CakeUIController(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @GetMapping("/")
    public String cakeIndex(Model model) {
        List<CakeModel> cakes = cakeRepository.findAll();
        model.addAttribute("cakesList", cakes);

        return "index";
    } 


}
