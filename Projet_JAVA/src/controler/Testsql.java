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

        Login monLogin = new Login();
        System.out.println("Mon id: " + monLogin.getEmail());
        do{
            System.out.print("");
        }while(monLogin.getEmail() == "");
        System.out.println("Mon id: " + monLogin.getEmail());
        monLogin.setVisible(false);
        Traitement_Connexion test = new Traitement_Connexion(Connexion_sql.getInstance());
        Utilisateur personne = test.traitement_co(monLogin.getEmail());
        System.out.println("Mon droit est " + personne.getDroit());
        if(personne.getDroit() == 1){
            Admin student = (Admin) personne;
            System.out.println("Je suis un admin.");
            Fenetre myWindow = new Fenetre();
        }
        if(personne.getDroit() == 2){
            Referent student = (Referent) personne;
            System.out.println("Je suis un referent.");
            Fenetre myWindow = new Fenetre();
        }
        if(personne.getDroit() == 3){
            Enseignant student = (Enseignant) personne;
            System.out.println("Je suis un prof.");
            Fenetre myWindow = new Fenetre();
        }
        if(personne.getDroit() == 4){
            Etudiant student = (Etudiant) personne;
            System.out.println("Mon numero etudiant est " + student.getNumero());
            Fenetre myWindow = new Fenetre();
        }
    
    
    }
    
}
