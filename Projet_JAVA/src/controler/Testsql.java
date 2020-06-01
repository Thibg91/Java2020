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
import modele.Etudiant;
import vue.Fenetre;
import vue.Login;

/**
 *
 * @author Luka Carneiro
 */
public class Testsql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Login check= new Login();
    int id = check.Login();
//    DAO<Etudiant> student = new DAOEtudiant(Connexion_sql.getInstance());
//    Etudiant etu = student.find(id);
  //  System.out.println(etu.getNom()+ " " + etu.getDroit());
    
    Fenetre f=new Fenetre();
   /* 
    Login monLogin = new Login();
    monLogin.setVisible(false);
    Fenetre myWindow = new Fenetre();
    myWindow.setVisible(false);
    */
    }
    
}
