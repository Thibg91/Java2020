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
import modele.Cours;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOCours extends DAO<Cours> {

    public DAOCours(Connection conn) {
        super(conn);
    }

    @Override
    public Cours find(int id_cours) {
        Cours matiere = null;
        int id = 0;
        String nom = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs=stmt.executeQuery("select * from cours WHERE ID="+id_cours);
            while(rs.next()) {
                id = rs.getInt("ID");
                nom = rs.getString("Nom");
            }
            matiere = new Cours(id, nom);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOCours.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matiere;
    }

    @Override
    public Cours create(Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cours update(Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cours obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table cours
            ResultSet rs=stmt.executeQuery("DELETE from cours WHERE ID="+obj.getId());
            //Supprimer l'etudiant de la table seance
            ResultSet res=stmt.executeQuery("DELETE from seance WHERE Id_cours="+obj.getId());
            System.out.println("Le cours a été supprimé");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
