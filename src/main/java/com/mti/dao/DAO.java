package com.mti.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by val on 10/07/17.
 */

public abstract class DAO<T> {
    @PersistenceContext(unitName = "jee-project")
    protected EntityManager manager;


    /**
     * Permet de récupérer un objet via son ID
     * @return ArrayList<T>
     */
    public abstract ArrayList<T> findAll();

    /**
     * Permet de récupérer un objet via son ID
     * @param id int
     * @return T
     */
    public abstract T find(int id);

    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj T
     */
    public abstract T create(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     * @param obj T
     */
    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     * @param obj T
     */
    public abstract void delete(T obj);
}
