package com.mti.dao;

import com.mti.entities.Blog;

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
public class BlogDAO extends DAO<Blog> {

    @Override
    public ArrayList<Blog> findAll() {
        ArrayList<Blog> blogs;
        try {
            blogs = new ArrayList(manager.createQuery("SELECT blog FROM Blog blog").getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException();
        }
        return blogs;
    }

    @Override
    public Blog find(int id) {
        Blog blog;
        try {
            blog = manager.createQuery(
                    "SELECT c FROM Blog c WHERE c.id = :id", Blog.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return blog;
    }

    @Override
    public Blog create(Blog blog) throws SystemException {
        try {
            userTransaction.begin();
            manager.persist(blog);
            userTransaction.commit();
            return blog;
        } catch (Exception ex) {
            ex.printStackTrace();
            userTransaction.rollback();
            throw new BadRequestException();
        }
    }

    @Override
    public Blog update(Integer blogId, MultivaluedMap<String, String> formParams) throws SystemException {
        Blog blog;
        try {
            userTransaction.begin();
            blog = this.find(blogId);
            blog.setTitle(formParams.getFirst("title"));
            manager.merge(blog);
            userTransaction.commit();
        } catch (Exception ex) {
            userTransaction.rollback();
            throw new BadRequestException();
        }
        return blog;
    }

    @Override
    public Boolean delete(Integer blogId) {
        try {
            userTransaction.begin();
            manager.remove(this.find(blogId));
            userTransaction.commit();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return true;
    }

    public Blog setArchive(Integer blogId, Boolean shouldArchive) throws SystemException {
        Blog blog;
        try {
            userTransaction.begin();
            blog = this.find(blogId);
            blog.setIsArchived(shouldArchive);
            manager.merge(blog);
            userTransaction.commit();
        } catch (Exception ex) {
            userTransaction.rollback();
            throw new BadRequestException();
        }
        return blog;
    }

}
