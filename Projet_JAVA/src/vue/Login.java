/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controler.Affichage_Seance;
import controler.Connexion_sql;
import controler.Recherchelog;
import controler.Traitement_Connexion;
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
import modele.Etudiant;
import modele.Utilisateur;

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
    private Recherchelog reche;
    //Constructeur basique
    public void Login() throws ClassNotFoundException, SQLException{
        this.setTitle(" Connexion");
        this.setSize(350,250);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLocationRelativeTo(null);
         
         

         JTextArea ID = new JTextArea("Identifiant : ");
         ID.setEditable(false);
         JTextArea MDP = new JTextArea("Mot de passe : ");
         MDP.setEditable(false);

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
     System.out.println(chp_login.getText());
     System.out.println(chp_mdp.getText());
        try {
            Utilisateur Personne = reche.Recherche(chp_login.getText(),chp_mdp.getText());
            //System.out.println(Personne.getDroit());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
   
  
}
