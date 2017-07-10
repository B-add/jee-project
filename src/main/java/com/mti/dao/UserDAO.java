package com.mti.dao;

import com.mti.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by val on 10/07/17.
 */

@ApplicationScoped
public class UserDAO extends DAO<User> {

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = null;
        try {
            users = new ArrayList(manager.createQuery("SELECT * FROM User").getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;    }

    @Override
    public User find(int id) {
        User u = null;
        try {
            u = manager.createQuery(
                    "SELECT c FROM User c WHERE c.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return u;
    }

    @Override
    public User create(User obj) {
        try {
            manager.persist(obj);
            manager.getTransaction().commit();
            obj = this.find(obj.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }
}
