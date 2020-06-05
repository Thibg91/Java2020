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
import modele.Type_Cours;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOType extends DAO<Type_Cours> {

    public DAOType(Connection conn) {
        super(conn);
    }

    @Override
    public Type_Cours find(int id_type) {
        Type_Cours type = null;
        int id = 0;
        String nom = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from salle WHERE ID=" + id_type);
            while (rs.next()) {
                id = rs.getInt("ID");
                nom = rs.getString("Nom");
            }
            type = new Type_Cours(id, nom);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOType.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    @Override
    public Type_Cours create(Type_Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type_Cours update(Type_Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Type_Cours obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs = stmt.executeQuery("DELETE from type_cours WHERE ID=" + obj.getId());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res = stmt.executeQuery("DELETE from seance WHERE ID_Typ=" + obj.getId());
            System.out.println("Le type a été supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(DAOType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
