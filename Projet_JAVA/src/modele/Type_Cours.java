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
    
    public Type_Cours(int id, String nom){
        this.id_type = id;
        this.nom_type = nom;
    }
    
    public void setId(int id){
        this.id_type = id;
    }
    
    public void setNom(String nom){
        this.nom_type = nom;
    }
    
    public int getId(){
        return this.id_type;
    }
    
    public String getNom(){
        return this.nom_type;
    }
}
