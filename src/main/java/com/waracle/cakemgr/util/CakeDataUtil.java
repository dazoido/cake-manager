package com.waracle.cakemgr.util;

import java.net.URL;
import java.util.Set;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CakeDataUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(CakeDataUtil.class);

    @Value("${cake.data.url}")
    private String cakeDataUrlValue;

    private CakeRepository cakeRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public CakeDataUtil(CakeRepository cakeRepository, ObjectMapper objectMapper) {
        this.cakeRepository = cakeRepository;
        this.objectMapper = objectMapper;
    }

    public boolean loadData() {
        try {
            URL cakeDataURL = new URL(cakeDataUrlValue);
            Set<CakeModel> cakes = objectMapper.readValue(cakeDataURL, new TypeReference<Set<CakeModel>>() {});
            logger.info("loadData() cakes fetched[" + (cakes != null ? cakes.size() : 0) + "]");

            cakes.forEach((c) -> {
                if (!cakeRepository.existsByTitle(c.getTitle())) {
                    logger.info("loadData() saving cake[" + c.getTitle() + "]");
                    cakeRepository.save(c);
                }                
            });
            return true;
        } catch (Exception e) {
            // unable to load cake data from url
            return false;
        }
    }
}
