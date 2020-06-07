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
import modele.Admin;
import modele.Enseignant;
import modele.Etudiant;
import modele.Referent;
import modele.Utilisateur;

/**
 *
 * @author Gautier PLANTE
 */
public class Traitement_Connexion {

    private Connection connexion = null;

    /**
     *
     * @param conn
     */
    public Traitement_Connexion(Connection conn) {
        this.connexion = conn;
    }

    /**
     *
     * @param email
     * @return
     * @throws ClassNotFoundException
     */
    public Utilisateur traitement_co(String email) throws ClassNotFoundException {
        Utilisateur user = null;
        int droit = 0, id = 0;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utilisateurs where Email=" + "\"" + email + "\"");
            while (rs.next()) {
                droit = rs.getInt("Droit");
                id = rs.getInt("ID");
            }
            if (droit == 1) {
                DAO<Admin> admin = new DAOAdmin(connexion);
                user = admin.find(id);
            }
            if (droit == 2) {
                DAO<Referent> ref = new DAOReferent(connexion);
                user = ref.find(id);
            }
            if (droit == 3) {
                DAO<Enseignant> prof = new DAOEnseignant(connexion);
                user = prof.find(id);
            }
            if (droit == 4) {
                DAO<Etudiant> eleve = new DAOEtudiant(connexion);
                user = eleve.find(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

}
