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
   
    /**
     *
     * @param id
     * @param nom
     */
    public Site(int id, String nom){
        this.id_site = id;
        this.nom_site = nom;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_site =id;
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
     * @return
     */
    public int getId(){
        return this.id_site;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom_site;
    }
}
