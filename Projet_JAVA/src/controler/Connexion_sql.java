/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Gautier PLANTE
 */
public class Connexion_sql {

	private static String url = "jdbc:postgresql://localhost:5432/Societe";
	private static String user = "root";
	private static String passwd = "";
	private static Connection connect;
          private Statement stmt;
   private ResultSet rset;
   private ResultSetMetaData rsetMeta;

	public  Connection getInstance() throws SQLException, ClassNotFoundException{
		    // chargement driver "com.mysql.jdbc.Driver"
        
       Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost:3306/" + "projetplanning";
       // String urlDatabase = "jdbc:mysql://localhost:3308/jps?characterEncoding=latin1";

        //création d'une connexion JDBC à la base 
        connect = DriverManager.getConnection(urlDatabase, "root","");

        // création d'un ordre SQL (statement)
        stmt = connect.createStatement();
		return connect;	
	}
        public ArrayList Affich(String requete) throws ClassNotFoundException, SQLException
        {
             

       Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost:3306/" + "projetplanning";
       // String urlDatabase = "jdbc:mysql://localhost:3308/jps?characterEncoding=latin1";

        //création d'une connexion JDBC à la base 
        connect = DriverManager.getConnection(urlDatabase, "root","");

        // création d'un ordre SQL (statement)
        stmt = connect.createStatement();
		
        // création d'un ordre SQL (statement)
       rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }

            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }
        

 return liste;
    }


        }

