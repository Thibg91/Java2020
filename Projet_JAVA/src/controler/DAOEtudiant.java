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
import modele.Etudiant;
/**
 *
 * @author Gautier PLANTE
 */
public class DAOEtudiant extends DAO<Etudiant>{

    public DAOEtudiant(Connection conn) {
        super(conn);
    }
    
    @Override
    public Etudiant create(Etudiant student){
        //appele le formulaire d'ajout d'etudiant
        //recuperer les infos du formulaire ici
        //creer un etudiant avec les donnees
        //l'enregistrer dans la base
        return student;
    }
    
    @Override
    public Etudiant find(int id_student){
        Etudiant student = null;
        int id = 0, numero = 0, groupe = 0, droit = 0;
        String nom = null, prenom = null, email = null;
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs=stmt.executeQuery("select * from utilisateurs WHERE ID="+id_student);
            while(rs.next()) {
                id = rs.getInt("ID");
                email = rs.getString("Email");
                nom = rs.getString("Nom");
                prenom = rs.getString("Prenom");  
                droit = rs.getInt("Droit");
            }
            ResultSet res=stmt.executeQuery("select * from etudiant WHERE Id_utilisateurs="+id_student);
            while(res.next()){
                numero = res.getInt("Numero");
                groupe = res.getInt("Id_groupe");
            }
            student = new Etudiant(id, email, nom, prenom, droit, numero, groupe);
            //se connecter a la base sql
            //faire une requete sql ici avec l'id de l'etudiant
            //recuperer les donnees
            //creer un nouvel objet etudiant avec les donnees    
        } catch (SQLException ex) {
            Logger.getLogger(DAOEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
    
    @Override
    public Etudiant update(Etudiant student){
        //se connecter a la base sql
        //faire une requete sql ici avec l'id de l'etudiant
        //recuperer les donnees
        //creer un nouvel objet etudiant avec les donnees modifiées
        return student;
    }
    
    @Override
    public void delete(Etudiant student){
        //se connecter a la base sql
        try {
            Statement stmt = connexion.createStatement();
            //Supprimer l'etudiant de la table utilisateur
            ResultSet rs=stmt.executeQuery("DELETE from utilisateurs WHERE ID="+student.getID());
            //Supprimer l'etudiant de la table etudaint
            ResultSet res=stmt.executeQuery("DELETE from etudiant WHERE Id_utilisateurs="+student.getID());
            System.out.println("Etudiant a été supprimé");
        }
        catch (SQLException ex) {
            Logger.getLogger(DAOEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
