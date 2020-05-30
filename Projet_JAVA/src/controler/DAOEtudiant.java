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
        try {
            Statement stmt = connexion.createStatement();
            ResultSet rs=stmt.executeQuery("select * from utilisateurs WHERE ID="+id_student);
            while(rs.next()) {
                System.out.println ("ID: "+ rs.getString(1)+ " Email " + rs.getString(2));
             }
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
        //faire une requete sql ici avec l'id de l'etudiant
        //supprimer l'etudiant de la base de données
    }

}
