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
public class Type_Cours {
    
    private int id_type;
    private String nom_type;
    
    /**
     *
     * @param id
     * @param nom
     */
    public Type_Cours(int id, String nom){
        this.id_type = id;
        this.nom_type = nom;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_type = id;
    }
    
    /**
     *
     * @param nom
     */
    public void setNom(String nom){
        this.nom_type = nom;
    }
    
    /**
     *
     * @return
     */
    public int getId(){
        return this.id_type;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom_type;
    }
}
