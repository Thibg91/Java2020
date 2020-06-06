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
public class Etudiant extends Utilisateur {
    
    private int numero;
    private int groupe;
    
    /**
     *
     * @param id
     * @param email
     * @param nom
     * @param prenom
     * @param droit
     * @param numero
     * @param groupe
     */
    public Etudiant(int id, String email, String nom, String prenom, int droit, int numero, int groupe){
        super(id, email, nom, prenom, droit);
        this.numero = numero;
        this.groupe = groupe;
    }
    
    /**
     *
     * @param numero
     */
    public void setNumero(int numero){
        this.numero = numero;
    }
      
    /**
     *
     * @return
     */
    public int getNumero(){
        return this.numero;
    }

    /**
     *
     * @return
     */
    public int getGroupe() {
        return groupe;
    }

    /**
     *
     * @param groupe
     */
    public void setGroupe(int groupe) {
        this.groupe = groupe;
    }
    
    
}
