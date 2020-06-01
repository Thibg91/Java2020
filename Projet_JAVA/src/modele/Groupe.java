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
public class Groupe {
    
    private int id_groupe;
    private String nom_groupe;
    private int promo;
    
    public Groupe(int id, String nom, int promo){
        this.id_groupe = id;
        this.promo = promo;
        this.nom_groupe = nom;
    }
    
    public void setId(int id){
        this.id_groupe = id;
    }
    
    public void setNom(String nom){
        this.nom_groupe = nom;
    }
    
    public void setPromo(int id){
        this.promo = id;
    }
    
    public int getId(){
        return this.id_groupe;
    }
    
    public String getNom(){
        return this.nom_groupe;
    }
    
    public int getPromo(){
        return this.promo;
    }
}
