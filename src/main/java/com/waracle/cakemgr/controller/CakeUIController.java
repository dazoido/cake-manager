package com.waracle.cakemgr.controller;

import java.util.List;

import javax.validation.Valid;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CakeUIController {
    
    private static final Logger logger = LoggerFactory.getLogger(CakeUIController.class);
    
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
    public String saveCake(@Valid @ModelAttribute("cake") CakeModel cake, BindingResult bindingResult, Model model) {
        model.addAttribute("cake", cake);

        logger.info("saveCake() hasErrors[" + bindingResult.hasErrors() + "]");
        //logger.info("saveCake() bindingResult[" + bindingResult.toString() + "]");

        logger.info("saveCake() hasFieldErrors(title) =>[" + bindingResult.hasFieldErrors("title") + "]");
        logger.info("saveCake() hasFieldErrors(desc) =>[" + bindingResult.hasFieldErrors("desc") + "]");
        logger.info("saveCake() hasFieldErrors(image) =>[" + bindingResult.hasFieldErrors("image") + "]");

        if (bindingResult.hasErrors()) {
            return "addCakeForm";
        }

        cakeRepository.save(cake);
        return "redirect:/";
    }

}
