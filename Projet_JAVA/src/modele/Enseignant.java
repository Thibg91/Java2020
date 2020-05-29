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
public class Enseignant extends Utilisateur{
    
    public Enseignant(int id, String email, String nom, String prenom, int droit){
        super(id, email, nom, prenom, droit);
    }
}
