/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controler.Connexion_sql;
import java.awt.*;

import java.awt.event.*;
import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.Connection;

/**
 *
 * @author titig
 */

//fenetre de connexion
public class Login extends JFrame implements ActionListener{
    // Y a  deux champs de texte et un bouton pour submit
    private JPanel chp_co = new JPanel();
    private JTextField chp_login = new JTextField();
    private JTextField chp_mdp = new JTextField();
    private BoutonInt valider = new BoutonInt("Valider");
     private Connection connexion = null;
 private JTextArea ID = new JTextArea("Identifiant : ");
  private  JTextArea MDP = new JTextArea("Mot de passe : ");
        ArrayList<String> liste; 
  private  Statement stmt;
  private  ResultSet rset;
   private boolean verif;
    
    //Constructeur basique
    public void Login() throws ClassNotFoundException, SQLException{
        this.setTitle(" Connexion");
        this.setSize(350,250);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLocationRelativeTo(null);
         
         
         
         //déclaration de 3 JPanel pour positionner les différents champs de texte et le bouton
         JPanel ContenuID = new JPanel();
         JPanel ContenuMDP = new JPanel();
         JPanel ContenuB = new JPanel();
         
         ContenuB.setBounds(50, 50, 0, 0);
         chp_login.setPreferredSize(new Dimension(160, 35));
         chp_mdp.setPreferredSize(new Dimension(160, 35));
         chp_co.setLayout(new GridLayout(4,1));
         
         //on ajoute les composants dans les différents panel
         ContenuID.add(ID);
         ContenuID.add(chp_login);
         ContenuMDP.add(MDP);
         ContenuMDP.add(chp_mdp);
         ContenuB.add(valider);
         //puis sur le JPanel principal
         chp_co.add(ContenuID); 
         chp_co.add(ContenuMDP); 
         chp_co.add(ContenuB); 
         
         //on demande au bouton d'intéragir quand on clique dessus ( pour l'instant ca ferme juste la fenetre de co
         valider.addActionListener(this);
         
         //le conteneur principal de la fenetre est chp_co
         this.setContentPane(chp_co);
         this.setVisible(true);
         System.out.println(chp_login.getText());
      
        
    }
    //on ferme la fenetre quand on clique sur submit (mais ca ca va changer)
    @Override
    public void actionPerformed(ActionEvent arg0){
       
      verif=false;
     
        try {
            verif=Recherche();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println(verif);
       
    
       
    }
    public boolean Recherche() throws ClassNotFoundException, SQLException
    { String mdpbdd="";
    boolean verif=true;
    String email=chp_login.getText();
    String mdpp=chp_mdp.getText();
   
     // création d'un ordre SQL (statement)
  this.connexion= Connexion_sql.getInstance();
        stmt = connexion.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from utilisateurs Where ID= "+ email); 
        while(rs.next())
        {
            mdpbdd=rs.getString("Password");
        }
      
        
         if(mdpbdd.equals(mdpp))
         {
           
          
            System.out.println("ok");
            
            
             
               return verif;
             
         }
         else
         {
             
              System.out.println(" no ok");
         verif=false;
        
             
         }
         System.out.println(verif);
         return verif;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 

  
}
