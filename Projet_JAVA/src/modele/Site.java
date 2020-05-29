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
public class Site {
    private int id_site;
    private String nom_site;
   
    public Site(int id, String nom){
        this.id_site = id;
        this.nom_site = nom;
    }
    
    public void setId(int id){
        this.id_site =id;
    }
    
    public void setNom(String nom){
        this.nom_site = nom;
    }
    
    public int getId(){
        return this.id_site;
    }
    
    public String getNom(){
        return this.nom_site;
    }
}
