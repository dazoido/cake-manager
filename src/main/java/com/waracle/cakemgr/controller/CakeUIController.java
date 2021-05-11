package com.waracle.cakemgr.controller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private ObjectMapper objectMapper;

    @Autowired
    public CakeUIController(CakeRepository cakeRepository, ObjectMapper objectMapper) {
        this.cakeRepository = cakeRepository;
        this.objectMapper = objectMapper;
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

        if (cakeRepository.existsByTitle(cake.getTitle())) {
            bindingResult.rejectValue("title", "error.title", "Title already exists");
            return "addCakeForm";
        }

        cakeRepository.save(cake);
        return "redirect:/";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadData() throws Exception {

		List<CakeModel> cakes = cakeRepository.findAll();
        
        // TODO: Should we throw new exception if cakes is null or empty?

		String json = objectMapper.writeValueAsString(cakes);
		byte[] inputStrBytes = json.getBytes();

		String fileName = "cakes.json";
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentLength(inputStrBytes.length);
		respHeaders.setContentType(new MediaType("text", "json"));
		respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		return new ResponseEntity<byte[]>(inputStrBytes, respHeaders, HttpStatus.OK);
    } 
}
