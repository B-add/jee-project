package com.mti.services;

import com.mti.dao.BlogDAO;
import com.mti.dao.UserDAO;
import com.mti.entities.Blog;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

/**
 * Created by val on 11/07/17.
 */
@WebService
@Path("/blogs")
@Produces("application/json; charset=UTF-8")
public class BlogWebServices {

    @Inject
    private BlogDAO bdao;

    @Inject
    UserDAO userDAO;
    @GET
    public ArrayList<Blog> getAllBlogs(@Context final HttpServletRequest request) throws NotFoundException {
        return bdao.findAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Blog addBlog(@Context final HttpServletRequest request,
                        MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        Blog newBlog = new Blog();
        newBlog.setTitle(formParams.getFirst("title"));
        newBlog.setOwner(userDAO.find(Integer.parseInt(formParams.getFirst("userId"))));
        newBlog.setIsArchived(false);
        return bdao.create(newBlog);
    }

    @GET
    @Path("/{blogId}")
    public Blog getBlog(@Context final HttpServletRequest request,
                        @PathParam("blogId") final Integer blogId) throws NotFoundException {
        return bdao.find(blogId);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Path("/{blogId}")
    public Blog updateBlog(@Context final HttpServletRequest request,
                           @PathParam("blogId") final Integer blogId,
                           MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        return bdao.update(blogId, formParams);
    }

    @DELETE
    @Path("/{blogId}")
    public Boolean deleteBlog(@Context final HttpServletRequest request,
                              @PathParam("blogId") final Integer blogId) throws NotFoundException {
        return bdao.delete(blogId);
    }

    @POST
    @Path("/{blogId}/setArchived")
    @Consumes("application/x-www-form-urlencoded")
    public Blog deleteBlog(@Context final HttpServletRequest request,
                           @PathParam("blogId") final Integer blogId,
                           @FormParam("isArchive") String shouldArchive) throws NotFoundException, SystemException {
        return bdao.setArchive(blogId, Boolean.valueOf(shouldArchive));
    }
}