package com.gillessed.daedalus.rest.resources;

import com.gillessed.daedalus.rest.api.Id;
import com.gillessed.daedalus.rest.api.request.CreateAuthorRequest;
import com.gillessed.daedalus.rest.api.request.CreateTagRequest;
import com.gillessed.daedalus.rest.api.request.EditAuthorRequest;
import com.gillessed.daedalus.rest.api.request.EditTagRequest;
import com.gillessed.daedalus.rest.api.request.FilterRequest;
import com.gillessed.daedalus.rest.exception.AuthException;
import com.gillessed.daedalus.rest.exception.ValidationException;
import com.gillessed.daedalus.rest.model.Author;
import com.gillessed.daedalus.rest.model.Languages;
import com.gillessed.daedalus.rest.model.Tag;
import com.gillessed.daedalus.rest.services.AuthService;
import com.gillessed.daedalus.rest.services.CatalogueService;
import com.gillessed.daedalus.rest.services.Services;
import com.gillessed.daedalus.rest.services.ValidationService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gcole on 5/20/16.
 */
@Path("/catalogue")
public class CatalogueResource {

    private final AuthService authService;
    private final CatalogueService catalogueService;
    private final ValidationService validationService;

    public CatalogueResource(Services services) {
        authService = services.getAuthService();
        catalogueService = services.getCatalogueService();
        validationService = services.getValidationService();
    }

    @GET
    @Path("/languages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLanguages(@HeaderParam("X-Auth-Token") String authToken) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        return Response.ok(Languages.ALL).build();
    }

    @POST
    @Path("/tags")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTags(@HeaderParam("X-Auth-Token") String authToken, FilterRequest filter) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        return Response.ok(catalogueService.getTags(filter.getSubstring())).build();
    }

    @POST
    @Path("/add/tag")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTag(@HeaderParam("X-Auth-Token") String authToken, CreateTagRequest request) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        Tag tag = new Tag(UUID.randomUUID().toString(), request.getLanguageMap());
        try {
            validationService.verifyTag(tag);
        } catch(ValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        catalogueService.addTag(tag);
        return Response.ok(new Id(tag.getId())).build();
    }

    @POST
    @Path("/edit/tag")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editTag(@HeaderParam("X-Auth-Token") String authToken, EditTagRequest request) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        try {
            validationService.verifyEditTag(request);
        } catch(ValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        catalogueService.editTag(request.getTagId(), request.getLanguage(), request.getValue());
        return Response.ok(new ArrayList<>()).build();
    }

    @POST
    @Path("/delete/tag")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTag(@HeaderParam("X-Auth-Token") String authToken, Id id) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        try {
            validationService.verifyTagId(id);
        } catch(ValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        catalogueService.deleteTag(id.getId());
        return Response.ok(new ArrayList<>()).build();
    }

    @POST
    @Path("/authors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthors(@HeaderParam("X-Auth-Token") String authToken, FilterRequest filter) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        return Response.ok(catalogueService.getAuthors(filter.getSubstring())).build();
    }

    @POST
    @Path("/add/author")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAuthor(@HeaderParam("X-Auth-Token") String authToken, CreateAuthorRequest request) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        Author author = new Author(UUID.randomUUID().toString(), request.getLanguageMap());
        try {
            validationService.verifyAuthor(author);
        } catch(ValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        catalogueService.addAuthor(author);
        return Response.ok(new Id(author.getId())).build();
    }

    @POST
    @Path("/edit/author")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editAuthor(@HeaderParam("X-Auth-Token") String authToken, EditAuthorRequest request) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        try {
            validationService.verifyEditAuthor(request);
        } catch(ValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        catalogueService.editAuthor(request.getAuthorId(), request.getLanguage(), request.getValue());
        return Response.ok(new ArrayList<>()).build();
    }

    @POST
    @Path("/delete/author")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@HeaderParam("X-Auth-Token") String authToken, Id id) {
        try {
            authService.verifyToken(authToken);
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        try {
            validationService.verifyAuthorId(id);
        } catch(ValidationException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        catalogueService.deleteAuthor(id.getId());
        return Response.ok(new ArrayList<>()).build();
    }

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> search() {
        return null;
    }
}
