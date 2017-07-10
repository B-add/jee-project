package com.mti.dao;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;

/**
 * Created by val on 10/07/17.
 */

public abstract class DAO<T> {
    @PersistenceContext(unitName = "intrapu")
    protected EntityManager manager;


    @Resource
    protected UserTransaction userTransaction;
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
    public abstract T create(T obj) throws SystemException;

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     * @param formParams MultivaluedMap<String, String>
     * @param id Integer
     */
    public abstract T update(Integer id, MultivaluedMap<String, String> formParams) throws SystemException;

    /**
     * Permet la suppression d'une entrée de la base
     * @param id Integer
     */
    public abstract Boolean delete(Integer id);
}
