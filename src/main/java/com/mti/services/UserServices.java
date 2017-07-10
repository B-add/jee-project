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
    public User setMessageRead(@QueryParam("token") final String token,
                               @PathParam("messageId") final Integer messageId)
    {
        return null;
    }

}
