package com.mti.dao;

import com.mti.entities.User;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

/**
 * Created by val on 10/07/17.
 */

@ApplicationScoped
public class UserDAO extends DAO<User> {

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users;
        try {
            users = new ArrayList(manager.createQuery("SELECT user FROM User user").getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NotFoundException();
        }
        return users;
    }

    @Override
    public User find(int id) {
        User u;
        try {
            u = manager.createQuery(
                    "SELECT c FROM User c WHERE c.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return u;
    }

    @Override
    public User create(User user) throws SystemException {
        try {
            userTransaction.begin();
            manager.persist(user);
            userTransaction.commit();
            return user;
        } catch (Exception ex) {
            userTransaction.rollback();
            throw new BadRequestException();
        }
    }

    @Override
    public User update(Integer userId, MultivaluedMap<String, String> formParams) throws SystemException {
        User user;
        try {
            userTransaction.begin();
            user = this.find(userId);
            user.setEmail(formParams.getFirst("email"));
            user.setPassword(formParams.getFirst("password"));
            user.setUsername(formParams.getFirst("username"));
            manager.merge(user);
            userTransaction.commit();
        } catch (Exception ex) {
            userTransaction.rollback();
            throw new BadRequestException();
        }
        return user;
    }

    @Override
    public Boolean delete(Integer userId) {
        try {
            userTransaction.begin();
            manager.remove(this.find(userId));
            userTransaction.commit();
        } catch (Exception ex) {
            throw new NotFoundException();
        }
        return true;
    }
}
