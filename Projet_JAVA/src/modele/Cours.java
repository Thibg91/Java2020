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
public class Cours {
    
    private int id_cours;
    private String nom_cours;
    
    /**
     *
     * @param id
     * @param nom
     */
    public Cours(int id, String nom){
        this.id_cours = id;
        this.nom_cours = nom;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_cours = id;
    }
    
    /**
     *
     * @param nom
     */
    public void setNom(String nom){
        this.nom_cours = nom;
    }
    
    /**
     *
     * @return
     */
    public int getId(){
        return this.id_cours;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom_cours;
    }
}
