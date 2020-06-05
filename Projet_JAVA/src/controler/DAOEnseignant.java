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
import modele.Enseignant;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOEnseignant extends DAO<Enseignant> {

    public DAOEnseignant(Connection conn) {
        super(conn);
    }

    @Override
    public Enseignant find(int id_prof) {
        Enseignant prof = null;
        int id = 0, droit = 0;
        String nom = null, prenom = null, email = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utilisateurs WHERE ID=" + id_prof);
            while (rs.next()) {
                id = rs.getInt("ID");
                email = rs.getString("Email");
                nom = rs.getString("Nom");
                prenom = rs.getString("Prenom");
                droit = rs.getInt("Droit");
            }
            prof = new Enseignant(id, email, nom, prenom, droit);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prof;
    }

    @Override
    public Enseignant create(Enseignant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enseignant update(Enseignant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Enseignant obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs = stmt.executeQuery("DELETE from utilisateurs WHERE ID=" + obj.getID());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res = stmt.executeQuery("DELETE from enseignant WHERE Id_utilisateurs=" + obj.getID());
            System.out.println("Le prof a été supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(DAOEnseignant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
