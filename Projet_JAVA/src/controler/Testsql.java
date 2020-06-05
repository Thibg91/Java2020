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
import static java.time.temporal.TemporalQueries.localDate;
import modele.Admin;
import modele.Enseignant;
import modele.Etudiant;
import modele.Referent;
import modele.Seance;
import modele.Utilisateur;
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


        //Login check= new Login();
//    DAO<Etudiant> student = new DAOEtudiant(Connexion_sql.getInstance());
//    Etudiant etu = student.find(id);
  //  System.out.println(etu.getNom()+ " " + etu.getDroit());
  

   /* 

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

   
  //  DAO<Etudiant> student = new DAOEtudiant(Connexion_sql.getInstance());
    

    
    /* 

    Etudiant etu = student.find(id);
    System.out.println(etu.getDroit());
/*    

    Login monLogin = new Login();
    monLogin.setVisible(false);
*/

    //Fenetre myWindow = new Fenetre();
    //myWindow.setVisible(true);
//    String email = "Papier@edu.ece.fr";
//    Traitement_Connexion test = new Traitement_Connexion(Connexion_sql.getInstance());
//    Etudiant personne = (Etudiant) test.traitement_co(email);
//   
//
//    Affichage_Seance cours = new Affichage_Seance(Connexion_sql.getInstance());
//    cours.affiche(personne.getGroupe());


  //  Fenetre myWindow = new Fenetre();
//    myWindow.setVisible(true);
//    String email = "Papier@edu.ece.fr";
//    Traitement_Connexion test = new Traitement_Connexion(Connexion_sql.getInstance());
//    Etudiant personne = (Etudiant) test.traitement_co(email);
//    System.out.println("Droit: "+personne.getDroit()+" Nom:"+ personne.getGroupe());
//Login log=new Login();
//log.Login();
    
//        Login monLogin = new Login();
//        System.out.println("Mon id: " + monLogin.getEmail());
//        do{
//            System.out.print("");
//        }while(monLogin.getEmail() == "");
//        System.out.println("Mon id: " + monLogin.getEmail());
//        monLogin.setVisible(false);
//        Traitement_Connexion test = new Traitement_Connexion(Connexion_sql.getInstance());
//        Utilisateur personne = test.traitement_co(monLogin.getEmail());
//        System.out.println("Mon droit est " + personne.getDroit());
//        if(personne.getDroit() == 1){
//            Admin student = (Admin) personne;
//            System.out.println("Je suis un admin.");
//            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance());
//        }
//        if(personne.getDroit() == 2){
//            Referent student = (Referent) personne;
//            System.out.println("Je suis un referent.");
//            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance());
//        }
//        if(personne.getDroit() == 3){
//            Enseignant student = (Enseignant) personne;
//            System.out.println("Je suis un prof.");
//            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance());
//        }
//        if(personne.getDroit() == 4){
//            Etudiant student = (Etudiant) personne;
//            System.out.println("Mon numero etudiant est " + student.getNumero());
//            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance());
//        }

        Login monLogin = new Login();
        do{
            System.out.print("");
        }while(monLogin.getEmail() == "");
        monLogin.setVisible(false);
        Traitement_Connexion test = new Traitement_Connexion(Connexion_sql.getInstance());
        Utilisateur personne = test.traitement_co(monLogin.getEmail());
        if(personne.getDroit() == 1){
            Admin student = (Admin) personne;
            
          Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student);
            myWindow.hideCal();
            myWindow.hideRec();
            myWindow.hideRep();
            myWindow.showMaj();
            myWindow.setContentMaj();
            
        }
        if(personne.getDroit() == 2){
            Referent student = (Referent) personne;
           Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student);
            myWindow.hideCal();
            myWindow.showRec();
            myWindow.showRep();
            myWindow.hideMaj();
            myWindow.setContentRec();
            
        }
        if(personne.getDroit() == 3){
            Enseignant student = (Enseignant) personne;
            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student);
            myWindow.showCal();
            myWindow.showRec();
            myWindow.showRep();
            myWindow.hideMaj();
        }
        if(personne.getDroit() == 4){
            Etudiant student = (Etudiant) personne;
            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student);
            myWindow.showCal();
            myWindow.showRec();
            myWindow.showRep();
            myWindow.hideMaj();
        }


    
    
    }
    
}
