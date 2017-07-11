package com.mti.services;

import com.mti.dao.PostDAO;
import com.mti.dao.CommentDAO;
import com.mti.dao.UserDAO;
import com.mti.entities.Comment;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by val on 11/07/17.
 */
@WebService
@Path("/comments")
@Produces("application/json; charset=UTF-8")
public class CommentWebServices {

    @Inject
    private CommentDAO cdao;

    @Inject
    private PostDAO postDAO;

    @Inject
    UserDAO userDAO;

    @GET
    public ArrayList<Comment> getAllComments(@Context final HttpServletRequest request) throws NotFoundException {
        return cdao.findAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Comment addComment(@Context final HttpServletRequest request,
                        MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        Comment newComment = new Comment();
        newComment.setBody(formParams.getFirst("body"));
        newComment.setDate(new Date());

        newComment.setAuthor(userDAO.find(Integer.parseInt(formParams.getFirst("userId"))));
        newComment.setPost(postDAO.find(Integer.parseInt(formParams.getFirst("postId"))));
        return cdao.create(newComment);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@Context final HttpServletRequest request,
                        @PathParam("commentId") final Integer commentId) throws NotFoundException {
        return cdao.find(commentId);
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    @Path("/{commentId}")
    public Comment updateComment(@Context final HttpServletRequest request,
                        @PathParam("commentId") final Integer commentId,
                           MultivaluedMap<String, String> formParams) throws BadRequestException, SystemException {
        return cdao.update(commentId, formParams);
    }

    @DELETE
    @Path("/{commentId}")
    public Boolean deleteComment(@Context final HttpServletRequest request,
                        @PathParam("commentId") final Integer commentId) throws NotFoundException {
        return cdao.delete(commentId);
    }
}