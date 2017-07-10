package com.mti.dao;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by val on 10/07/17.
 */

public class Manager {
    @Produces
    @Dependent
    public EntityManager getManager() {
        return Persistence.createEntityManagerFactory("jee-project").createEntityManager();
    }
}