package com.waracle.cakemgr.util;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CakeDataUtil {
    
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
            List<CakeModel> cakes = objectMapper.readValue(cakeDataURL, new TypeReference<List<CakeModel>>() {});

            cakeRepository.saveAll(cakes);
            return true;
        } catch (IOException e) {
            // unable to load cake data from url
            return false;
        }
    }
}
