/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Utilisateur;
import java.sql.Statement;

/**
 *
 * @author Luka Carneiro
 */
public class Recherchelog {

    private Connection connexion = null;

    /**
     *
     * @param conn
     */
    public Recherchelog(Connection conn) {
        this.connexion = conn;
    }

    /**
     *
     * @param email
     * @param mdpp
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ConnexionException
     */
    public Utilisateur Recherche(String email, String mdpp) throws ClassNotFoundException, SQLException, ConnexionException {
        //System.out.println(email);
        //System.out.println(mdpp);
        Utilisateur personne = null;
        String mdpbdd = "";
        Statement stmt;
        // création d'un ordre SQL (statement)
        this.connexion = Connexion_sql.getInstance();
        stmt = connexion.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from utilisateurs Where Email= " + "\"" + email + "\"");
        while (rs.next()) {
            mdpbdd = rs.getString("Password");
        }
        if (mdpbdd.equals(mdpp)) {
            Traitement_Connexion test = new Traitement_Connexion(this.connexion);
            personne = test.traitement_co(email);
        } else {

            System.out.println("no ok");

            throw new ConnexionException();

        }
        return personne;
    }
}
