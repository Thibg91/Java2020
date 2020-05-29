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
public class Login extends JFrame{
    private JPanel chp_co = new JPanel();
    private JTextField chp_login = new JTextField("Rentrez votre login");
    private JTextField chp_mdp = new JTextField("Rentrez votre mot de passe");
    private JButton valider = new JButton("Valider");
    
    public Login(){
        this.setTitle(" Connexion");
        this.setSize(350,250);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setLocationRelativeTo(null);
         
         
         JTextArea ID = new JTextArea("Identifiant : ");
         JTextArea MDP = new JTextArea("Mot de passe : ");
         JPanel ContenuID = new JPanel();
         JPanel ContenuMDP = new JPanel();
         JPanel ContenuB = new JPanel();
         ContenuB.setBounds(50, 50, 0, 0);
         chp_login.setPreferredSize(new Dimension(160, 35));
         chp_mdp.setPreferredSize(new Dimension(160, 35));
         chp_co.setLayout(new GridLayout(4,1));
         ContenuID.add(ID);
         ContenuID.add(chp_login);
         ContenuMDP.add(MDP);
         ContenuMDP.add(chp_mdp);
         ContenuB.add(valider);
         chp_co.add(ContenuID); 
         chp_co.add(ContenuMDP); 
         chp_co.add(ContenuB); 
         this.setContentPane(chp_co);
         this.setVisible(true);
    }
}
