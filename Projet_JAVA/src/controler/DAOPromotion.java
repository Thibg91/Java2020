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
import modele.Promotion;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOPromotion extends DAO<Promotion> {

    /**
     *
     * @param conn
     */
    public DAOPromotion(Connection conn) {
        super(conn);
    }

    /**
     *
     * @param id_promo
     * @return
     */
    @Override
    public Promotion find(int id_promo) {
        Promotion promo = null;
        int id = 0;
        String nom = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from promotion WHERE ID=" + id_promo);
            while (rs.next()) {
                id = rs.getInt("ID");
                nom = rs.getString("Nom");
            }
            promo = new Promotion(id, nom);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return promo;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Promotion create(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Promotion update(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param obj
     */
    @Override
    public void delete(Promotion obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs = stmt.executeQuery("DELETE from promotion WHERE ID=" + obj.getId());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res = stmt.executeQuery("DELETE from groupe WHERE Id_promotion=" + obj.getId());
            System.out.println("La promo a été supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
