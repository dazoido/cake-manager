package com.waracle.cakemgr.controller;

import java.util.List;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;
import com.waracle.cakemgr.util.CakeDataUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cakes")
public class CakeDataController {

    private CakeRepository cakeRepository;
    private CakeDataUtil cakeDataUtil;

    @Autowired
    public CakeDataController(CakeRepository cakeRepository, CakeDataUtil cakeDataUtil) {
        this.cakeRepository = cakeRepository;
        this.cakeDataUtil = cakeDataUtil;
    }

    @RequestMapping(method=RequestMethod.GET) 
    @ResponseBody
    public String viewAll() {
        List<CakeModel> cakes = cakeRepository.findAll();
        
        if (null != cakes && !cakes.isEmpty()) {
            return "Found cakes[" + cakes.size() + "]";
        }
        return "There are currently no cakes available to view. Please load or add cakes.";

    }

    @GetMapping("/add")
    @ResponseBody
    public String add() {
        CakeModel cake = new CakeModel("ChocoCake", "A chocolate cake made with hmmm ... chocolate", "chocolate_cake.png");

        if (!cakeRepository.existsByTitle(cake.getTitle())) {
            CakeModel savedCake = cakeRepository.save(cake);
            return "Added" + savedCake.toString();
        }
        return "Cake[" + cake.getTitle() + "] already exists"; 
    }

    @GetMapping("/load")
    @ResponseBody
    public String loadData() {
        boolean loaded = cakeDataUtil.loadData();

        if (loaded) {
            return "Cake data loaded => " + this.viewAll();
        }
        return "Unable to load data";
    }
}
