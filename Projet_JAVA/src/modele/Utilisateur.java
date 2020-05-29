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
    
    protected int id_user;
    protected String email_user;
    protected String nom_user;
    protected String prenom_user;
    protected int droit_user;
    
    public Utilisateur(int id, String email, String nom, String prenom, int droit){
        this.id_user = id;
        this.email_user = email;
        this.nom_user = nom;
        this.prenom_user = prenom;
        this.droit_user = droit;
    }
    
    public void setId(int id){
        this.id_user = id;
    }
    
    public void setEmail(String email){
        this.email_user = email;
    }
    
    public void setNom(String nom){
        this.nom_user = nom;
    }
    
    public void setPrenom(String prenom){
        this.prenom_user = prenom;
    }
    
    public void setDroit(int droit){
        this.droit_user = droit;
    }
    
    public int getID(){
        return this.id_user;
    }
    
    public String getEmail(){
        return this.email_user;
    }
    
    public String getNom(){
        return this.nom_user;
    }
    
    public String getPrenom(){
        return this.prenom_user;
    }
    
    public int getDroit(){
        return this.getDroit();
    }
}
