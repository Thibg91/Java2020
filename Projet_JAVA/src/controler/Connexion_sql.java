/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Gautier PLANTE
 */
public class Connexion_sql {

	private static String url;
	private static String user = "root";
	private static String passwd = "";
	private static Connection connect;

	public static Connection getInstance() throws ClassNotFoundException{
            Class.forName("com.mysql.jdbc.Driver");
            Connexion_sql.url = "jdbc:mysql://localhost:3306/projetplanning?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}
}
