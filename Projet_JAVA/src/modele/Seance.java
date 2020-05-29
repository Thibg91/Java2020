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
public class Seance {
    private int id_seance;
    private int semaine;
    private int date;
    private int heure_debut;
    private int heure_fin;
    private String etat;
    private Cours cours;
    private Type_Cours type;
    private Enseignant prof;
    
    public void Seance(int id, int week, int date, int debut, int fin, String etat, Cours cours, Type_Cours type, Enseignant prof){
        this.id_seance = id;
        this.semaine = week;
        this.date = date;
        this.heure_debut = debut;
        this.heure_fin = fin;
        this.etat = etat;
        this.cours = cours;
        this.type = type;
        this.prof = prof;
    }
    
    public void setId(int id){
        this.id_seance = id;
    }
    
    public void setWeek(int semaine){
        this.semaine = semaine;
    }
    
    public void setDate(int date){
        this.date = date;
    }
    
    public void setDebut(int debut){
        this.heure_debut = debut;
    }
    
    public void setFin(int fin){
        this.heure_fin = fin;
    }
    
    public void setEtat(String etat){
        this.etat = etat;
    }
    
    public void setCours(Cours cours){
        this.cours = cours;
    }
    
    public void setType(Type_Cours type){
        this.type = type;
    }
    
    public int getId(){
        return this.id_seance;
    }
    
    public int getWeek(){
        return this.semaine;
    }
    
    public int getDate(){
        return this.date;
    }
    
    public int getDebut(){
        return this.heure_debut;
    }
    
    public int getFin(){
        return this.heure_fin;
    }
    
    public String getEtat(){
        return this.etat;
    }
    
    public Cours getCours(){
        return this.cours;
    }
    
    public Type_Cours getType(){
        return this.type;
    }
    
}
