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
    
    /**
     *
     * @param id
     * @param week
     * @param date
     * @param debut
     * @param fin
     * @param etat
     * @param cours
     * @param type
     */
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
    
    /**
     *
     * @param id
     */
    public void setId(int id){
        this.id_seance = id;
    }
    
    /**
     *
     * @param semaine
     */
    public void setWeek(int semaine){
        this.semaine = semaine;
    }
    
    /**
     *
     * @param debut
     */
    public void setDebut(Time debut){
        this.heure_debut = debut;
    }
    
    /**
     *
     * @param fin
     */
    public void setFin(Time fin){
        this.heure_fin = fin;
    }
    
    /**
     *
     * @param etat
     */
    public void setEtat(String etat){
        this.etat = etat;
    }
    
    /**
     *
     * @param cours
     */
    public void setCours(int cours){
        this.cours = cours;
    }
    
    /**
     *
     * @param type
     */
    public void setType(int type){
        this.type = type;
    }
    
    /**
     *
     * @return
     */
    public int getId(){
        return this.id_seance;
    }
    
    /**
     *
     * @return
     */
    public int getWeek(){
        return this.semaine;
    }
    
    /**
     *
     * @return
     */
    public Time getDebut(){
        return this.heure_debut;
    }
    
    /**
     *
     * @return
     */
    public Time getFin(){
        return this.heure_fin;
    }
    
    /**
     *
     * @return
     */
    public String getEtat(){
        return this.etat;
    }
    
    /**
     *
     * @return
     */
    public int getCours(){
        return this.cours;
    }
    
    /**
     *
     * @return
     */
    public int getType(){
        return this.type;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
}
