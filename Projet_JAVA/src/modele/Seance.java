/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 *
 * @author Gautier PLANTE
 */
public class Seance {
    private int id_seance;
    private int semaine;
    private Date date;
    private Time heure_debut;
    private Time heure_fin;
    private String etat;
    private int cours;
    private int type;
    
    public Seance(int id, int week, Date date, Time debut, Time fin, String etat, int cours, int type){
        this.id_seance = id;
        this.semaine = week;
        this.date = date;
        this.heure_debut = debut;
        this.heure_fin = fin;
        this.etat = etat;
        this.cours = cours;
        this.type = type;
    }
    
    public void setId(int id){
        this.id_seance = id;
    }
    
    public void setWeek(int semaine){
        this.semaine = semaine;
    }
    
    public void setDebut(Time debut){
        this.heure_debut = debut;
    }
    
    public void setFin(Time fin){
        this.heure_fin = fin;
    }
    
    public void setEtat(String etat){
        this.etat = etat;
    }
    
    public void setCours(int cours){
        this.cours = cours;
    }
    
    public void setType(int type){
        this.type = type;
    }
    
    public int getId(){
        return this.id_seance;
    }
    
    public int getWeek(){
        return this.semaine;
    }
    
    public Time getDebut(){
        return this.heure_debut;
    }
    
    public Time getFin(){
        return this.heure_fin;
    }
    
    public String getEtat(){
        return this.etat;
    }
    
    public int getCours(){
        return this.cours;
    }
    
    public int getType(){
        return this.type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
