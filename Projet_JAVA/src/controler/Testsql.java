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
        
   /*Connection conn;
   Statement stmt;
   ResultSet rset;
   ResultSetMetaData rsetMeta;
          // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost:3306/" + "projetplanning";
       // String urlDatabase = "jdbc:mysql://localhost:3308/jps?characterEncoding=latin1";

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, "root", "");

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
        ResultSet rs=stmt.executeQuery("select * from utilisateurs"); 

    while(rs.next()) {
        System.out.println ("ID: "+ rs.getString(1)+ " Email " + rs.getString(2));
    }
    conn.close(); */
    int id = 4;
    DAO<Etudiant> student = new DAOEtudiant(Connexion_sql.getInstance());
    
    Etudiant etu = student.find(id);
    System.out.println(etu.getDroit());
/*    
    Login monLogin = new Login();
    monLogin.setVisible(false);
*/
    Fenetre myWindow = new Fenetre();
    myWindow.setVisible(true);
    
    }
    
}
