package com.mti.services;

import com.mti.entities.User;

import javax.jws.WebService;
import javax.ws.rs.*;

/**
 * Created by Zibe on 10/07/2017.
 */
@WebService
@Path("/user")
@Produces("application/json; charset=UTF-8")
public class UserServices {

    @GET
    @Path("/{userId}")
    public int getUserById(@PathParam("userId") final Integer userId)
    {
        return userId;
    }

    @POST
    @Consumes("application/json")
    public User addUser(final String content)
    {
        return null;
    }

    @PUT
    @Consumes("application/json")
    public User editUser(final String content)
    {
        return null;
    }

}
