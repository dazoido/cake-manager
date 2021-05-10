package com.waracle.cakemgr.controller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;
import com.waracle.cakemgr.exception.BadRequestException;
import com.waracle.cakemgr.util.CakeDataUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cakes")
public class CakeDataController {

    private static final Logger logger = LoggerFactory.getLogger(CakeDataUtil.class);

    private CakeRepository cakeRepository;
    private CakeDataUtil cakeDataUtil;
    private ObjectMapper objectMapper;

    @Autowired
    public CakeDataController(CakeRepository cakeRepository, CakeDataUtil cakeDataUtil, ObjectMapper objectMapper) {
        this.cakeRepository = cakeRepository;
        this.cakeDataUtil = cakeDataUtil;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(method=RequestMethod.GET) 
    public List<CakeModel> viewAll() {
        List<CakeModel> cakes = cakeRepository.findAll();       
        logger.info("viewAll() Found cakes[" + (null != cakes && !cakes.isEmpty() ? cakes.size() : 0) + "]");      
        return cakes;
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED) 
    public CakeModel add(@RequestBody @Valid CakeModel cake) throws BadRequestException, Exception {
        CakeModel savedCake = null;
        if (cake != null) {
            if (cakeRepository.existsByTitle(cake.getTitle())) {
                logger.warn("add() cake already exists with given title[" + cake.getTitle() + "]");      
                throw new BadRequestException("Cake already exists with given title[" + cake.getTitle() + "]"); // TODO fix message propogation to API message returned to client
            }
            logger.info("add() saved cake[" + cake.toString() + "]");      
            savedCake = cakeRepository.save(cake);
        }
        return savedCake;
    }

    @GetMapping("/resetData")
    public void resetData() {
        cakeRepository.deleteAll();
        boolean loaded = cakeDataUtil.loadData();

        if (loaded) {
            logger.info("resetData() Cake data reset");
        } else {
            logger.warn("resetData() Unable to reset data");
        }
    }

    @GetMapping("/downloadData")
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
