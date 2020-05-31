/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;



import controler.Connexion_sql;
import java.awt.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;




/**
 *
 * @author titig
 */

//classe principale de l'affichage ( il faut que je module ce programme en différentes fonctions)
public final class Fenetre extends JFrame {
    //déclaration du tableau
    private JTable monTableau;
    private Connexion_sql conn= new Connexion_sql();

    private BoutonInt testButton = new BoutonInt("je suis un bouton de test");
   
    
    //constructeur de la classe
    public Fenetre() throws ClassNotFoundException, SQLException {
        
    // déclaration de la fenetre
     String prof="";
     String id_cours="";
     String nomcours="";
     ArrayList<String> liste; 
     liste=conn.Affich("Select Nom from utilisateurs Where ID=16");
         for(int i=0;i<liste.size();i++)
       {
           
          prof = liste.get(i);
           
       }
         liste=conn.Affich("Select Id_cours from enseignant Where ID_utilisateurs=16");
           for(int i=0;i<liste.size();i++)
       {
           
          id_cours = liste.get(i);
           
       }
                    liste=conn.Affich("Select Nom  from cours Where ID="+ id_cours);
           for(int i=0;i<liste.size();i++)
       {
           
          nomcours = liste.get(i);
           
       }
    //modification des propriétés de la fenetre principale (titre, taille, position et action de fermeture)
    this.setSize(1500, 1000);
    this.setTitle("Mon Calendrier");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
   
    //Test pour remplir une des cases du tableau, "recap" est le string dans lequel on écrit les informations qu'on souhaite afficher
    
    //String matiere = "VHDL";
    String groupe = "ING3 Gr9";
    String salle = "Salle P416";
    String recap = nomcours + "\r\n"+ prof + "\r\n"+ groupe + "\r\n"+ salle + "\r\n" ;
    
    //Panel dans lequel on place un JTextPane, en gros c'est la qu'on défini les cases de notre tableau donc la couleur la taille et surtout le text grace au setText(recap) avec "recap" le string vu plus haut
    
    JPanel firstPanel = new JPanel();
    JTextPane contenu = new JTextPane();
    contenu.setBackground(Color.magenta);
    contenu.setEditable(false);
    contenu.setText(recap);
    //c'est avec ca qu'on centre le texte dans une case
    StyledDocument doc = contenu.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
doc.setParagraphAttributes(0, doc.getLength(), center, false);
    contenu.setBounds( 51, 51, 200, 70 );
    
    //ici on déclare le meme type de composant mais il est vide, on va initialiser le tableau en le remplissant de Voidcontenu
    JTextPane Voidcontenu = new JTextPane();
    Voidcontenu.setEditable(false);
    Voidcontenu.setText("");
    Voidcontenu.setBounds( 51, 51, 200, 70);
    
    
    
    
    //Ici c'est juste la colonne qui affiche les heures du tableau
    JPanel firstColumnPane = new JPanel();
    JTextPane firstColumn = new JTextPane();
    firstColumn.setBackground(Color.lightGray);
    firstColumn.setEditable(false);
    firstColumn.setText("\r\n \r\n \r\n \r\n" + "8h30-10h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"10h15-11h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"12h00-13h30" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"13h45-15h15" + "\r\n \r\n \r\n \r\n \r\n \r\n \r\n" +"15h30-17h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"17h15-18h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"19h30-21h00");
    firstColumn.setBounds( 0, 0, 80, 1000 );
    firstColumnPane.setLayout(null);
    firstColumnPane.add(firstColumn);
    firstColumnPane.setPreferredSize(new Dimension (80,1000));
    
    
    // Ici on a les boutons de bas de page qui vont permettre de passer d'une semaine a une autre
    JPanel changePage = new JPanel();
    changePage.setBackground(Color.lightGray);
    BoutonInt bouton1 = new BoutonInt("Semaine 1");
    BoutonInt bouton2 = new BoutonInt("Semaine 2");
    BoutonInt bouton3 = new BoutonInt("Semaine 3");
    BoutonInt bouton4 = new BoutonInt("Semaine 4");
    changePage.add(bouton1);
    changePage.add(bouton2);
    changePage.add(bouton3);
    changePage.add(bouton4);
    
    
//partie Tableau
    
    //le tableau est rempli de JTextPane vide
     Object[][] test_line = {
        {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
        {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
        {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu}   
    };  
     
    //test de case avec des données
      test_line[1][3] = contenu;
     
    //1ere ligne du tableau avec les différents jours
    String[] test_column = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
   
   //modele de tableau permettant de définir la forme du tableau, par exemple on a changé la taille des cases 
   MonModel model = new MonModel(test_line,test_column);
   this.monTableau = new JTable(model); 
   this.monTableau.setRowHeight(100);
   this.monTableau.setDefaultRenderer(JTextPane.class, new ComposantTable());
   
   
   //Partie filtre : c'est la partie sur la droite de la page qui va contenir tout les filtres utiles sur notre tableau
   
    JPanel SecondPanel = new JPanel();
    JComboBox cours = new JComboBox();
    SecondPanel.setBackground(Color.lightGray);
      liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      cours.addItem(liste.get(i));
      cours.addItem(liste.get(i+1));
      cours.addItem(liste.get(i+2));
      cours.addItem(liste.get(i+3));
       }
  
    JLabel coursLabel = new JLabel("Cours : ");
    SecondPanel.add(coursLabel);
    SecondPanel.add(cours);
    
    JScrollPane conteneurTab = new JScrollPane(monTableau);
    
    
    //partie Layout 
    this.setLayout(new BorderLayout());
    
    
    //Contenu du centre
    this.getContentPane().add(conteneurTab, BorderLayout.CENTER);
    
    //Contenu du haut
    this.getContentPane().add(new BoutonInt("Barre de navigation"),BorderLayout.NORTH);
    
    //contenu du bas 
    this.getContentPane().add(changePage,BorderLayout.SOUTH);
    
    //contenu de gauche
    this.getContentPane().add(firstColumnPane,BorderLayout.WEST);
    
    //contenu de Droite 
    this.getContentPane().add(SecondPanel,BorderLayout.EAST);
    
   
    
    Login monLogin = new Login();
    monLogin.setVisible(true);
    
    //Cacher la fenetre ou pas : bool 
    this.setVisible(false); 

    }    
 

  
 

    
   

}
