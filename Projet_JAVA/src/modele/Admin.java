/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Gautier PLANTE
 */
public class Admin extends Utilisateur{
    
    /**
     *
     * @param id
     * @param email
     * @param nom
     * @param prenom
     * @param droit
     */
    public Admin(int id, String email, String nom, String prenom, int droit) {
        super(id, email, nom, prenom, droit);
    }
    
}
