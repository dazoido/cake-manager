package com.waracle.cakemgr.controller;

import java.util.List;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/add")
    public String addCake(Model model) {
        model.addAttribute("cake", new CakeModel());

        return "addCakeForm";
    }

    @PostMapping("/add")
    public String saveCake(@ModelAttribute CakeModel cake, Model model) {
        model.addAttribute("cake", cake);

        cakeRepository.save(cake);
        return "redirect:/";
    }

}
