/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vue.Fenetre;

/**
 *
 * @author Luka Carneiro
 */
public class test {

    /**
     *
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */private static Connection connect;
          private Statement stmt;
   private ResultSet rset;
   private ResultSetMetaData rsetMeta;

    /**
     *
     * @param args
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void main(String[] args) throws SQLException, ClassNotFoundException {
        
  Connexion_sql conn= new Connexion_sql();
  conn.Affich("Select* from utilisateurs");


    }
        }
    

