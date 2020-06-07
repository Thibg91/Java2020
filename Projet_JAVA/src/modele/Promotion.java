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
public class Promotion {
    
    private int id_promo;
    private String nom_promo;
    
    /**
     *
     * @param id
     * @param nom
     */
    public Promotion( int id, String nom){
        this.id_promo = id;
        this.nom_promo = nom;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_promo = id;
    }
    
    /**
     *
     * @param nom
     */
    public void setNom(String nom){
        this.nom_promo = nom;
    }
    
    /**
     *
     * @return
     */
    public int getId(){
        return this.id_promo;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom_promo;
    }
}
