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
public class Salle {
    private int id_salle;
    private String nom_site;
    private int capacite;
    private int site;
    
    /**
     *
     * @param id
     * @param nom
     * @param capacite
     * @param site
     */
    public Salle(int id, String nom, int capacite, int site){
        this.id_salle = id;
        this.nom_site = nom;
        this.capacite = capacite;
        this.site = site;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_salle = id;
    }
    
    /**
     *
     * @param nom
     */
    public void setNom(String nom){
        this.nom_site = nom;
    }
    
    /**
     *
     * @param capacite
     */
    public void setCapacite(int capacite){
        this.capacite = capacite;
    }
    
    /**
     *
     * @param id
     */
    public void setSite(int id){
        this.site = id;
    }
    
    /**
     *
     * @return
     */
    public int getId(){
        return this.id_salle;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom_site;
    }
    
    /**
     *
     * @return
     */
    public int getCapacite(){
        return this.capacite;
    }
    
    /**
     *
     * @return
     */
    public int getSite(){
        return this.site;
    }
}
