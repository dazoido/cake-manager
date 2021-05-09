package com.waracle.cakemgr.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

@Service
@Path("/")
public class CakeManagerService {

    @GET
    @Produces("text/plain")
    public String viewAll() {
        return "TODO All Cakes list appears here";
    }

    @GET
    @Path("/add")
    @Produces("text/plain")
    public String add() {
        return "TODO Add cake impl goes here";
    }
}
