package com.waracle.cakemgr.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;
import com.waracle.cakemgr.util.CakeDataUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/")
public class CakeManagerService {

    private CakeRepository cakeRepository;
    private CakeDataUtil cakeDataUtil;

    @Autowired
    public CakeManagerService(CakeRepository cakeRepository, CakeDataUtil cakeDataUtil) {
        this.cakeRepository = cakeRepository;
        this.cakeDataUtil = cakeDataUtil;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String viewAll() {
        List<CakeModel> cakes = cakeRepository.findAll();
        
        if (null != cakes && !cakes.isEmpty()) {
            return "Found cakes[" + cakes.size() + "]";
        }
        return "There are currently no cakes available to view. Please load or add cakes.";

    }

    @GET
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    public String add() {
        CakeModel cake = new CakeModel("ChocoCake", "A chocolate cake made with hmmm ... chocolate", "chocolate_cake.png");
        CakeModel savedCake = cakeRepository.save(cake);
        return "Added" + savedCake.toString();
    }

    @GET
    @Path("/load")
    @Produces(MediaType.TEXT_PLAIN)
    public String loadData() {
        boolean loaded = cakeDataUtil.loadData();

        if (loaded) {
            return "Cake data loaded => " + this.viewAll();
        }
        return "Unable to load data";
    }

}
