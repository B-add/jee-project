package com.mti.services;

import com.mti.dao.UserDAO;
import com.mti.entities.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

/**
 * Created by val on 10/07/17.
 */
@WebService
@Path("/users")
@Produces("application/json; charset=UTF-8")
public class UserWebServices {

    @Inject
    private UserDAO udao;

    @GET
    public ArrayList<User> getAllUsers(@Context final HttpServletRequest request) throws NotFoundException {
        return udao.findAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public User addUser(@Context final HttpServletRequest request,
                        MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        User newUser = new User();
        newUser.setEmail(formParams.getFirst("email"));
        newUser.setPassword(formParams.getFirst("password"));
        newUser.setUsername(formParams.getFirst("username"));
        return udao.create(newUser);
    }

    @GET
    @Path("/{userId}")
    public User getUser(@Context final HttpServletRequest request,
                        @PathParam("userId") final Integer userId) throws NotFoundException {
        return udao.find(userId);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Path("/{userId}")
    public User updateUser(@Context final HttpServletRequest request,
                        @PathParam("userId") final Integer userId,
                           MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        return udao.update(userId, formParams);
    }

    @DELETE
    @Path("/{userId}")
    public Boolean deleteUser(@Context final HttpServletRequest request,
                        @PathParam("userId") final Integer userId) throws NotFoundException {
        return udao.delete(userId);
    }
}