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
public class Utilisateur {
    
    /**
     *
     */
    protected int id_user;

    /**
     *
     */
    protected String email_user;

    /**
     *
     */
    protected String nom_user;

    /**
     *
     */
    protected String prenom_user;

    /**
     *
     */
    protected int droit_user;
    
    /**
     *
     * @param id
     * @param email
     * @param nom
     * @param prenom
     * @param droit
     */
    public Utilisateur(int id, String email, String nom, String prenom, int droit){
        this.id_user = id;
        this.email_user = email;
        this.nom_user = nom;
        this.prenom_user = prenom;
        this.droit_user = droit;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_user = id;
    }
    
    /**
     *
     * @param email
     */
    public void setEmail(String email){
        this.email_user = email;
    }
    
    /**
     *
     * @param nom
     */
    public void setNom(String nom){
        this.nom_user = nom;
    }
    
    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom){
        this.prenom_user = prenom;
    }
    
    /**
     *
     * @param droit
     */
    public void setDroit(int droit){
        this.droit_user = droit;
    }
    
    /**
     *
     * @return
     */
    public int getID(){
        return this.id_user;
    }
    
    /**
     *
     * @return
     */
    public String getEmail(){
        return this.email_user;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom_user;
    }
    
    /**
     *
     * @return
     */
    public String getPrenom(){
        return this.prenom_user;
    }
    
    /**
     *
     * @return
     */
    public int getDroit(){
        return this.droit_user;
    }
}
