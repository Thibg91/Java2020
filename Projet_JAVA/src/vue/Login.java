/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

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
    
    //Constructeur basique
    public Login(){
        this.setTitle(" Connexion");
        this.setSize(350,250);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLocationRelativeTo(null);
         
         
         JTextArea ID = new JTextArea("Identifiant : ");
         JTextArea MDP = new JTextArea("Mot de passe : ");
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
    }
    //on ferme la fenetre quand on clique sur submit (mais ca ca va changer)
    public void actionPerformed(ActionEvent arg0){
        this.setVisible(false);
    }
}
