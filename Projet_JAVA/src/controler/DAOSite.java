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
import modele.Site;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOSite extends DAO<Site> {

    public DAOSite(Connection conn) {
        super(conn);
    }

    @Override
    public Site find(int id_site) {
        Site bat = null;
        int id = 0;
        String nom = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs=stmt.executeQuery("select * from site WHERE ID="+id_site);
            while(rs.next()) {
                id = rs.getInt("ID");
                nom = rs.getString("Nom");
            }
            bat = new Site(id, nom);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOSite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bat;
    }

    @Override
    public Site create(Site obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Site update(Site obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Site obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs=stmt.executeQuery("DELETE from site WHERE ID="+obj.getId());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res=stmt.executeQuery("DELETE from salle WHERE ID_site="+obj.getId());
            System.out.println("Le site a été supprimé");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOSite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
