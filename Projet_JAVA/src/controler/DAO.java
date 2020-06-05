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
 */
public abstract class DAO<T> {

    protected Connection connexion = null;

    public DAO(Connection conn) {
        this.connexion = conn;
    }

    public abstract T find(int id);

    public abstract T create(T obj);

    public abstract T update(T obj);

    public abstract void delete(T obj);
}
