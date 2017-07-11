package com.mti.dao;

import com.mti.entities.Post;

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
public class PostDAO extends DAO<Post> {

    @Override
    public ArrayList<Post> findAll() {
        ArrayList<Post> posts;
        try {
            posts = new ArrayList(manager.createQuery("SELECT post FROM Post post").getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException();
        }
        return posts;
    }

    @Override
    public Post find(int id) {
        Post post;
        try {
            post = manager.createQuery(
                    "SELECT c FROM Post c WHERE c.id = :id", Post.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return post;
    }

    @Override
    public Post create(Post post) throws SystemException {
        try {
            userTransaction.begin();
            manager.persist(post);
            userTransaction.commit();
            return post;
        } catch (Exception ex) {
            ex.printStackTrace();
            userTransaction.rollback();
            throw new BadRequestException();
        }
    }

    @Override
    public Post update(Integer postId, MultivaluedMap<String, String> formParams) throws SystemException {
        Post post;
        try {
            userTransaction.begin();
            post = this.find(postId);
            post.setTitle(formParams.getFirst("title"));
            post.setBody(formParams.getFirst("body"));

            manager.merge(post);
            userTransaction.commit();
        } catch (Exception ex) {
            userTransaction.rollback();
            throw new BadRequestException();
        }
        return post;
    }

    @Override
    public Boolean delete(Integer postId) {
        try {
            userTransaction.begin();
            manager.remove(this.find(postId));
            userTransaction.commit();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return true;
    }

    public ArrayList<Post> getPostsByBlogId(int blogId) {
        ArrayList<Post> posts;
        try {
            posts = new ArrayList(manager.createQuery("SELECT post FROM Post post WHERE post.blog.id = :blogId")
                    .setParameter("blogId", blogId)
                    .getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException();
        }
        return posts;
    }
}
