/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import controler.ConnexionException;
import controler.Recherchelog;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.Connection;
import modele.Utilisateur;

/**
 *
 * @author titig
 */

//fenetre de connexion
public class Login extends JFrame implements ActionListener{
    // Y a  deux champs de texte et un bouton pour submit
    private final JPanel chp_co = new JPanel();
   
    private final JTextField chp_login = new JTextField("Papier@edu.ece.fr");
    private final JTextField chp_mdp = new JTextField("Papier123");
    private final BoutonInt valider = new BoutonInt("Valider");
    private final Connection connexion = null;
    private final JTextArea ID = new JTextArea("Identifiant : ");
    private final JTextArea MDP = new JTextArea("Mot de passe : ");
    private Statement stmt;
    private ResultSet rset;
    private Recherchelog reche=null;
    private Utilisateur Personne=null;
    private String email = "";

    //Constructeur basique

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Login() throws ClassNotFoundException, SQLException{
        
        this.setTitle(" Connexion");
        this.setSize(350,250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //JTextArea ID = new JTextArea("Identifiant : ");
        ID.setEditable(false);
        //JTextArea MDP = new JTextArea("Mot de passe : ");
        MDP.setEditable(false);

        //déclaration de 3 JPanel pour positionner les différents champs de texte et le bouton
        JPanel ContenuID = new JPanel();
        JPanel ContenuMDP = new JPanel();
        JPanel ContenuB = new JPanel();
        ContenuID.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        ContenuMDP.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        ContenuB.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
         chp_co.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
         ID.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
         MDP.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
         
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
    
    /**
     *
     * @param arg0
     */
    @Override
    public void actionPerformed(ActionEvent arg0){
        reche=new Recherchelog(connexion);
        String mail=chp_login.getText();
        String mdp=chp_mdp.getText();
        try {
            try {
                Personne = reche.Recherche(mail,mdp);
            } catch (ConnexionException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.email = Personne.getEmail();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @param mail
     */
    public void setEmail(String mail) {
        this.email = mail;
    }
}
