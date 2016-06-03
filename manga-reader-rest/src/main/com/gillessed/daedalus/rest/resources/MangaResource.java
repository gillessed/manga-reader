package com.gillessed.daedalus.rest.resources;

import com.gillessed.daedalus.rest.api.response.MangaResult;
import com.gillessed.daedalus.rest.services.CatalogueService;
import com.gillessed.daedalus.rest.services.Services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by gcole on 4/16/16.
 */
@Path("/manga")
public class MangaResource {

    private final CatalogueService catalogueService;

    public MangaResource(Services services) {
        catalogueService = services.getCatalogueService();
    }

    @POST
    @Path("/manga/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MangaResult getManga(@PathParam("id") String id) {
        return null;
    }
}
