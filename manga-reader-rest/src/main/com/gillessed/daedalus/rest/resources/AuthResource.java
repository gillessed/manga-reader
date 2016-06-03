package com.gillessed.daedalus.rest.resources;

import com.gillessed.daedalus.rest.api.request.AuthAttempt;
import com.gillessed.daedalus.rest.api.response.LoginResponse;
import com.gillessed.daedalus.rest.exception.AuthException;
import com.gillessed.daedalus.rest.services.AuthService;
import com.gillessed.daedalus.rest.services.Services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by gcole on 5/22/16.
 */
@Path("/auth")
public class AuthResource {
    private final AuthService authService;
    public AuthResource(Services services) {
        authService = services.getAuthService();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(AuthAttempt authAttempt) {
        String token;
        try {
            token = authService.login(authAttempt.getUsername(), authAttempt.getPassword());
        } catch(AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
        return Response.ok(new LoginResponse(token)).build();
    }
}
