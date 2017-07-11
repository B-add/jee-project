package com.mti.services;

import com.mti.dao.BlogDAO;
import com.mti.dao.PostDAO;
import com.mti.dao.UserDAO;
import com.mti.entities.Post;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by val on 11/07/17.
 */
@WebService
@Path("/posts")
@Produces("application/json; charset=UTF-8")
public class PostWebServices {

    @Inject
    private PostDAO pdao;

    @Inject
    private BlogDAO blogDAO;

    @Inject
    UserDAO userDAO;

    @GET
    public ArrayList<Post> getAllPosts(@Context final HttpServletRequest request) throws NotFoundException {
        return pdao.findAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Post addPost(@Context final HttpServletRequest request,
                        MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        Post newPost = new Post();
        newPost.setBody(formParams.getFirst("body"));
        newPost.setTitle(formParams.getFirst("title"));
        newPost.setDate(new Date());

        newPost.setAuthor(userDAO.find(Integer.parseInt(formParams.getFirst("userId"))));
        newPost.setBlog(blogDAO.find(Integer.parseInt(formParams.getFirst("blogId"))));
        return pdao.create(newPost);
    }

    @GET
    @Path("/{postId}")
    public Post getPost(@Context final HttpServletRequest request,
                        @PathParam("postId") final Integer postId) throws NotFoundException {
        return pdao.find(postId);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Path("/{postId}")
    public Post updatePost(@Context final HttpServletRequest request,
                        @PathParam("postId") final Integer postId,
                           MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        return pdao.update(postId, formParams);
    }

    @DELETE
    @Path("/{postId}")
    public Boolean deletePost(@Context final HttpServletRequest request,
                        @PathParam("postId") final Integer postId) throws NotFoundException {
        return pdao.delete(postId);
    }
}