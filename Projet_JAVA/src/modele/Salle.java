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
    
    public Salle(int id, String nom, int capacite, int site){
        this.id_salle = id;
        this.nom_site = nom;
        this.capacite = capacite;
        this.site = site;
    }
    
    public void setId(int id){
        this.id_salle = id;
    }
    
    public void setNom(String nom){
        this.nom_site = nom;
    }
    
    public void setCapacite(int capacite){
        this.capacite = capacite;
    }
    
    public void setSite(int id){
        this.site = id;
    }
    
    public int getId(){
        return this.id_salle;
    }
    
    public String getNom(){
        return this.nom_site;
    }
    
    public int getCapacite(){
        return this.capacite;
    }
    
    public int getSite(){
        return this.site;
    }
}
