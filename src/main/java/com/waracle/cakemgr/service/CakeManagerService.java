package com.waracle.cakemgr.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.waracle.cakemgr.data.model.CakeModel;
import com.waracle.cakemgr.data.repository.CakeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/")
public class CakeManagerService {

    private CakeRepository cakeRepository;

    @Autowired
    public CakeManagerService(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @GET
    @Produces("text/plain")
    public String viewAll() {
        return "TODO All Cakes list appears here";
    }

    @GET
    @Path("/add")
    @Produces("text/plain")
    public String add() {
        CakeModel cake = new CakeModel("ChocoCake", "A chocolate cake made with hmmm ... chocolate", "chocolate_cake.png");
        CakeModel savedCake = cakeRepository.save(cake);
        return "Added" + savedCake.toString();
    }
}
