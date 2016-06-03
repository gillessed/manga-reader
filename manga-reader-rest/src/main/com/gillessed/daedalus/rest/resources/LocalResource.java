package com.gillessed.daedalus.rest.resources;

import com.gillessed.daedalus.rest.services.CatalogueService;
import com.gillessed.daedalus.rest.services.Services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Created by gcole on 5/20/16.
 */
@Path("/local")
public class LocalResource {

    private final CatalogueService catalogueService;

    public LocalResource(Services services) {
        catalogueService = services.getCatalogueService();
    }

    @POST
    @Path("/add/manga")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addManga() {
    }
}
