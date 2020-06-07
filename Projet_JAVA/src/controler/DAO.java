/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;

/**
 *
 * @author Gautier PLANTE
 * @param <T>
 */
public abstract class DAO<T> {

    /**
     *
     */
    protected Connection connexion = null;

    /**
     *
     * @param conn
     */
    public DAO(Connection conn) {
        this.connexion = conn;
    }

    /**
     *
     * @param id
     * @return
     */
    public abstract T find(int id);

    /**
     *
     * @param obj
     * @return
     */
    public abstract T create(T obj);

    /**
     *
     * @param obj
     * @return
     */
    public abstract T update(T obj);

    /**
     *
     * @param obj
     */
    public abstract void delete(T obj);
}
