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
import modele.Groupe;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOGroupe extends DAO<Groupe> {

    /**
     *
     * @param conn
     */
    public DAOGroupe(Connection conn) {
        super(conn);
    }

    /**
     *
     * @param id_groupe
     * @return
     */
    @Override
    public Groupe find(int id_groupe) {
        Groupe classe = null;
        int id = 0, id_promo = 0;
        String nom = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from groupe WHERE ID=" + id_groupe);
            while (rs.next()) {
                id = rs.getInt("ID");
                nom = rs.getString("Nom");
                id_promo = rs.getInt("ID_promotion");
            }
            classe = new Groupe(id, nom, id_promo);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classe;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Groupe create(Groupe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Groupe update(Groupe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param obj
     */
    @Override
    public void delete(Groupe obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs = stmt.executeQuery("DELETE from groupe WHERE ID=" + obj.getId());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res = stmt.executeQuery("DELETE from seance_groupe WHERE Id_groupe=" + obj.getId());
            System.out.println("Le groupe a été supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(DAOGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
