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
import modele.Salle;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOSalle extends DAO<Salle>{

    public DAOSalle(Connection conn) {
        super(conn);
    }

    @Override
    public Salle find(int id_salle) {
        Salle room = null;
        int id = 0, id_site = 0, capacite = 0;
        String nom = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs=stmt.executeQuery("select * from salle WHERE ID="+id_salle);
            while(rs.next()) {
                id = rs.getInt("ID");
                nom = rs.getString("Nom");
                capacite = rs.getInt("Capacite");
                id_site = rs.getInt("ID_site");
            }
            room = new Salle(id, nom, capacite, id_site);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return room;
    }

    @Override
    public Salle create(Salle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Salle update(Salle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Salle obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs=stmt.executeQuery("DELETE from salle WHERE ID="+obj.getId());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res=stmt.executeQuery("DELETE from seance_salle WHERE id_salle="+obj.getId());
            System.out.println("La salle a été supprimée");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
