/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;
import modele.Etudiant;
/**
 *
 * @author Gautier PLANTE
 */
public class DAOEtudiant extends DAO<Etudiant>{
    
    public DAOEtudiant(){
        
    }
    
    @Override
    public Etudiant create(Etudiant student){
        return student;
    }
    public Etudiant find(long id_student){
        Etudiant student = null;
        return student;
    }
    @Override
    public Etudiant update(Etudiant student){
        return student;
    }
    @Override
    public void delete(Etudiant student){

    }

}
