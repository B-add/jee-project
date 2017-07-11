package com.mti.dao;

import com.mti.entities.Comment;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.SystemException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

/**
 * Created by val on 11/07/17.
 */
@ApplicationScoped
public class CommentDAO extends DAO<Comment> {

    @Override
    public ArrayList<Comment> findAll() {
        ArrayList<Comment> comments;
        try {
            comments = new ArrayList(manager.createQuery("SELECT comment FROM Comment comment").getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException();
        }
        return comments;
    }

    @Override
    public Comment find(int id) {
        Comment comment;
        try {
            comment = manager.createQuery(
                    "SELECT c FROM Comment c WHERE c.id = :id", Comment.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return comment;
    }

    @Override
    public Comment create(Comment comment) throws SystemException {
        try {
            userTransaction.begin();
            manager.persist(comment);
            userTransaction.commit();
            return comment;
        } catch (Exception ex) {
            ex.printStackTrace();
            userTransaction.rollback();
            throw new BadRequestException();
        }
    }

    @Override
    public Comment update(Integer commentId, MultivaluedMap<String, String> formParams) throws SystemException {
        Comment comment;
        try {
            userTransaction.begin();
            comment = this.find(commentId);
            comment.setBody(formParams.getFirst("body"));

            manager.merge(comment);
            userTransaction.commit();
        } catch (Exception ex) {
            userTransaction.rollback();
            throw new BadRequestException();
        }
        return comment;
    }

    @Override
    public Boolean delete(Integer commentId) {
        try {
            userTransaction.begin();
            manager.remove(this.find(commentId));
            userTransaction.commit();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return true;
    }
}
