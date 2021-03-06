/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Seance;

/**
 *
 * @author Gautier PLANTE
 */
public class DAOSeance extends DAO<Seance> {

    /**
     *
     * @param conn
     */
    public DAOSeance(Connection conn) {
        super(conn);
    }

    /**
     *
     * @param id_seance
     * @return
     */
    @Override
    public Seance find(int id_seance) {
        Seance amphi = null;
        int id = 0, semaine = 0, id_cours = 0, id_type = 0;
        Time debut = null, fin = null;
        Date date = null;
        String etat = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from seance WHERE ID=" + id_seance);
            while (rs.next()) {
                id = rs.getInt("ID");
                etat = rs.getString("Etat");
                semaine = rs.getInt("Semaine");
                date = rs.getDate("Date");
                debut = rs.getTime("Debut");
                fin = rs.getTime("Fin");
                id_cours = rs.getInt("Id_cours");
                id_type = rs.getInt("Id_Typ");
            }
            amphi = new Seance(id, semaine, date, debut, fin, etat, id_cours, id_type);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOSeance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return amphi;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Seance create(Seance obj) {
        int id = 0;
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("INSERT into seance (Semaine, Date, Debut, Fin, Etat, Id_cours, Id_Typ) VALUES ('" + obj.getWeek() + "','" + obj.getDate() + "', '" + obj.getDebut() + "','" + obj.getFin() + "', '" + obj.getEtat() + "', '" + obj.getCours() + "', '" + obj.getType() + "')");
            Statement stt = connexion.createStatement();
            ResultSet rs = stt.executeQuery("select * from seance WHERE Date='" + obj.getDate() + "'AND Debut='" + obj.getDebut() + "'");
            while (rs.next()) {
                id = rs.getInt("ID");
            }
            obj.setId(id);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSeance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public Seance update(Seance obj) {
        try {
            Statement stmt = connexion.createStatement();
            stmt.executeUpdate("Update seance SET Semaine='" + obj.getWeek() + "',Date='" + obj.getDate() + "', Debut='" + obj.getDebut() + "', Fin='" + obj.getFin() + "', Etat='" + obj.getEtat() + "', Id_cours='" + obj.getCours() + "', Id_Typ='" + obj.getType() + "' WHERE ID="+obj.getId());
            obj = find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DAOSeance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    /**
     *
     * @param obj
     */
    @Override
    public void delete(Seance obj) {
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer la seance de la table seance
            stmt.executeUpdate("DELETE from seance WHERE ID=" + obj.getId());
            //Supprimer a seance des autres tables
            stmt.executeUpdate("DELETE from seance_salle WHERE id_seance=" + obj.getId());
            stmt.executeUpdate("DELETE from seance_groupe WHERE id_seance=" + obj.getId());
            stmt.executeUpdate("DELETE from seance_enseignant WHERE id_seance=" + obj.getId());
            System.out.println("La séance a été supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(DAOSeance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
