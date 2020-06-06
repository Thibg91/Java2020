/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.SQLException;
import java.util.Calendar;
import modele.Admin;
import modele.Enseignant;
import modele.Etudiant;
import modele.Referent;
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
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Calendar cal = Calendar.getInstance();
        //La première semaine de l'année est celle contenant au moins 4 jours
        cal.setMinimalDaysInFirstWeek(4);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(week);
        Login monLogin = new Login();
        do {
            System.out.print("");
        } while ("".equals(monLogin.getEmail()));
        monLogin.setVisible(false);
        Traitement_Connexion test = new Traitement_Connexion(Connexion_sql.getInstance());
        Utilisateur personne = test.traitement_co(monLogin.getEmail());
        if (personne.getDroit() == 1) {
            Admin student = (Admin) personne;
            
          Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student, week);
            myWindow.hideCal();
            myWindow.hideRec();
            myWindow.hideRep();
            myWindow.showMaj();
            myWindow.setContentMaj();
            
        }
        if (personne.getDroit() == 2) {
            Referent student = (Referent) personne;
           Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student, week);
            myWindow.hideCal();
            myWindow.showRec();
            myWindow.hideRep();
            myWindow.hideMaj();
            myWindow.setContentRec();
            
        }
        if(personne.getDroit() == 3){
            Enseignant student = (Enseignant) personne;
            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student, week);
            myWindow.showCal();
            myWindow.showRec();
            myWindow.hideRep();
            myWindow.hideMaj();
        }
        if (personne.getDroit() == 4) {
            Etudiant student = (Etudiant) personne;
            Fenetre myWindow = new Fenetre(Connexion_sql.getInstance(), student, week);
            myWindow.showCal();
            myWindow.showRec();
            myWindow.showRep();
            myWindow.hideMaj();
        }

    }

}
