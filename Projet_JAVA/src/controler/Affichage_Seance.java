/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gautier PLANTE
 */
public class Affichage_Seance {

    private Connection connexion = null;

    /**
     *
     * @param conn
     */
    public Affichage_Seance(Connection conn) {
        this.connexion = conn;
    }

    /**
     *
     * @param id_groupe
     */
    public void affiche(int id_groupe) {
        try {
            int id_seance = 0, semaine = 0, id_cours = 0;
            Statement stmt = connexion.createStatement();
            Statement stt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from seance_groupe WHERE id_groupe=" + id_groupe);
            while (rs.next()) {
                id_seance = rs.getInt("id_seance");
                System.out.println(id_seance);
                ResultSet res = stt.executeQuery("select * from seance WHERE ID=" + id_seance);
                while (res.next()) {
                    semaine = res.getInt("Semaine");
                    id_cours = res.getInt("Id_cours");
                    System.out.println("\nId de la seance:" + id_seance + "\nSemaine: " + semaine + "\nCours: " + id_cours);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Affichage_Seance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
