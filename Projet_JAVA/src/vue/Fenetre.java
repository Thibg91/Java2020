/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;



import controler.Connexion_sql;
import controler.DAO;
import controler.DAOEtudiant;
import java.awt.*;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import modele.Etudiant;




/**
 *
 * @author titig
 */

//classe principale de l'affichage ( il faut que je module ce programme en différentes fonctions)
public final class Fenetre extends JFrame implements ActionListener{
    //déclaration du tableau
    private Calendrier monTableau;
    private JTable monRecap;
    
    private Connexion_sql conn= new Connexion_sql();
    
    private BoutonInt bouton1 = new BoutonInt("Semaine 1");
    private BoutonInt bouton2 = new BoutonInt("Semaine 2");
    private BoutonInt bouton3 = new BoutonInt("Semaine 3");
    private BoutonInt bouton4 = new BoutonInt("Semaine 4");
    
    private JMenuBar Navigation = new JMenuBar();
    private BoutonInt boutonCal = new BoutonInt("Emploi du temps");
    private BoutonInt boutonRec = new BoutonInt("Recap");
    private BoutonInt boutonMaj = new BoutonInt("Mise à jour");
    private BoutonInt boutonRep = new BoutonInt("Reporting");
    
    private JPanel FenetreCalendrier = new JPanel();
    private JPanel FenetreRecap = new JPanel();
    private JPanel FenetreMaj = new JPanel();
    private JPanel FenetreReporting = new JPanel();
   
    
    //constructeur de la classe
    public Fenetre() throws ClassNotFoundException, SQLException {
        
    // déclaration de la fenetre
     String prof="Coudray";
     String id_cours="";
     String nomcours="Maths";
    String promo="Ing3";
     ArrayList<String> liste; 
    //modification des propriétés de la fenetre principale (titre, taille, position et action de fermeture)
    this.setSize(1500, 1000);
    this.setTitle("Mon Calendrier");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Test pour remplir une des cases du tableau, "recap" est le string dans lequel on écrit les informations qu'on souhaite afficher
    
    //String matiere = "VHDL";
   
    String salle = "Salle P416";
    String recap = nomcours + "\n" + prof + "\n" + promo + "\n" + salle + "\r\n" ;
    String idgr="";
   
    int cpt=0;
    //Panel dans lequel on place un JTextPane, en gros c'est la qu'on défini les cases de notre tableau donc la couleur la taille et surtout le text grace au setText(recap) avec "recap" le string vu plus haut
      liste=conn.Affich("Select id_groupe from etudiant Where Id_utilisateurs=4"); //boucle pour savoir combien de cours a l'utilisateurs
         for(int i=0;i<liste.size();i++)
       {
           
          idgr = liste.get(i);
           
       }
       
         liste=conn.Affich("Select id_seance from seance_groupe Where id_groupe=" + idgr);
       
     
         for(int i=0;i<liste.size();i++)
         {
             cpt++; // compteur pour savoir le nb de cours
            
         }
          JPanel firstPanel = new JPanel();
    JTextPane contenu = new JTextPane();
         for(int i=0;i<=2;i++)
         {
            
    contenu.setBackground(Color.magenta);
    contenu.setBounds( 12, 1210, 2200, 170 );
    
         }
    contenu.setEditable(false);
         
    contenu.setText(recap);
    //c'est avec ca qu'on centre le texte dans une case
    StyledDocument doc = contenu.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
doc.setParagraphAttributes(0, doc.getLength(), center, false);
    
    
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
    changePage.add(bouton1);
    changePage.add(bouton2);
    changePage.add(bouton3);
    changePage.add(bouton4);
    
   //test de case avec des données
    
     
     this.initCalendrier();
     this.initRecap();
      
   //Partie filtre : c'est la partie sur la droite de la page qui va contenir tout les filtres utiles sur notre tableau
    JPanel rightLayout = new JPanel();
    rightLayout.setLayout(new GridLayout(7,1));
    JPanel coursPanel = new JPanel();
    JComboBox cours = new JComboBox();
    //coursPanel.setBackground(Color.lightGray);
      liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      cours.addItem(liste.get(i));
      cours.addItem(liste.get(i+1));
      cours.addItem(liste.get(i+2));
      cours.addItem(liste.get(i+3));
       }
  
    JLabel coursLabel = new JLabel("Cours : ");
    coursPanel.add(coursLabel);
    coursPanel.add(cours);
    
    //juste un test 
    JComboBox professeur = new JComboBox();
    liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      professeur.addItem(liste.get(i));
      professeur.addItem(liste.get(i+1));
      professeur.addItem(liste.get(i+2));
      professeur.addItem(liste.get(i+3));
       }
  
    JLabel profLabel = new JLabel("Professeur : ");
    JPanel profPanel = new JPanel(); 
    profPanel.add(profLabel);
    profPanel.add(professeur);
    
    JLabel salleLabel = new JLabel("Salle : ");
    
//juste un test 
    JComboBox sallefiltre = new JComboBox();
    
      liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      sallefiltre.addItem(liste.get(i));
      sallefiltre.addItem(liste.get(i+1));
      sallefiltre.addItem(liste.get(i+2));
      sallefiltre.addItem(liste.get(i+3));
       }
    
    rightLayout.add(coursPanel);
    rightLayout.add(profPanel);
    
    
  
    
     Login monLogin = new Login();
    monLogin.setVisible(true);
    
    defineMaj();
    
    bouton1.addActionListener(this);
    bouton2.addActionListener(this);
    bouton3.addActionListener(this);
    bouton4.addActionListener(this);
    boutonCal.addActionListener(this);
    boutonRec.addActionListener(this);
    boutonMaj.addActionListener(this);
    boutonRep.addActionListener(this);
    
    monTableau.ajouterCours (contenu, 5, 5);
    
    JScrollPane conteneurCal = new JScrollPane(monTableau);
    

   // Login monLogin = new Login();
   // monLogin.setVisible(true);

    //partie barre de navigation

    
    this.Navigation.add(boutonCal);
    this.Navigation.add(boutonRec);
    this.Navigation.add(boutonMaj);
    this.Navigation.add(boutonRep);
    this.setJMenuBar(Navigation);
    
    //partie Layout du calendrier
    FenetreCalendrier.setLayout(new BorderLayout());
    //Contenu du centre
    FenetreCalendrier.add(conteneurCal, BorderLayout.CENTER);
    //contenu du bas 
    FenetreCalendrier.add(changePage,BorderLayout.SOUTH);
     //contenu de gauche
    FenetreCalendrier.add(firstColumnPane,BorderLayout.WEST);
    //contenu de Droite 
    FenetreCalendrier.add(rightLayout,BorderLayout.EAST);
    
    defineRecap();
    
  this.setContentPane(FenetreCalendrier);
    //Cacher la fenetre ou pas : bool 
    this.setVisible(true); 

    }    
 
  // nécessite des modifs mais permet d' 
    public void actionPerformed(ActionEvent arg0){
        if(arg0.getSource() == boutonCal )
        {
           System.out.println("J'ai cliqué sur le bouton Calendrier");
           this.setContentPane(FenetreCalendrier);
           this.setSize(1499, 1000);
           this.setSize(1500, 1000);
        }
        if(arg0.getSource() == boutonRec )
        {
           System.out.println("J'ai cliqué sur le bouton Recap");
           this.setContentPane(FenetreRecap);
           this.setSize(1499, 1000);
           this.setSize(1500, 1000);
        }
        if(arg0.getSource() == boutonMaj )
        {
           System.out.println("J'ai cliqué sur le bouton Mise à jour");
           this.setContentPane(FenetreMaj);
           this.setSize(1499, 1000);
           this.setSize(1500, 1000);
        }
        if(arg0.getSource() == boutonRep )
        {
           System.out.println("J'ai cliqué sur le bouton Reporting");
           this.setContentPane(FenetreReporting);
           this.setSize(1499, 1000);
           this.setSize(1500, 1000);
        }
    }

//initialise un calendrier
public void initCalendrier()
{
     //ici on déclare le meme type de composant mais il est vide, on va initialiser le tableau en le remplissant de Voidcontenu
    JTextPane Voidcontenu = new JTextPane();
    Voidcontenu.setEditable(false);
    Voidcontenu.setText("");
    Voidcontenu.setBounds( 51, 51, 200, 70);
    
   //partie Tableau
    
         Object[][] test_line = {
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu}   
    };  
         
    //1ere ligne du tableau avec les différents jours
    String[] Jour = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
    
   //modele de tableau permettant de définir la forme du tableau, par exemple on a changé la taille des cases 
   MonModel modelAgenda = new MonModel(test_line,Jour);
  
   this.monTableau = new Calendrier(modelAgenda); 
   this.monTableau.setRowHeight(100);
   this.monTableau.setDefaultRenderer(JTextPane.class, new ComposantTable());
    
} 

//initialise le tableau de récap
public void initRecap()
{
     Object[][] test_Recap = {
             {"Mathématique(Test)","15h30-17h","15 juin","Mme Coudray","1h30"},
             {"Mathématique(Test)","15h30-17h","15 juin","Mme Coudray","1h30"}
         };
     
     String[] recapTitle = {"Matière","Horaires", "Date","Professeur","durée"};
     
      MonModel modelRecap = new MonModel(test_Recap,recapTitle);
      this.monRecap = new JTable(modelRecap);
}

 public void defineRecap() throws ClassNotFoundException, SQLException
 {
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
     JComboBox cours = new JComboBox();
      liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      cours.addItem(liste.get(i));
      cours.addItem(liste.get(i+1));
      cours.addItem(liste.get(i+2));
      cours.addItem(liste.get(i+3));
       }
  
    JLabel coursLabel = new JLabel("Cours : ");
    
    //juste un test 
    JComboBox professeur = new JComboBox();
    liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      professeur.addItem(liste.get(i));
      professeur.addItem(liste.get(i+1));
      professeur.addItem(liste.get(i+2));
      professeur.addItem(liste.get(i+3));
       }
  
    JLabel profLabel = new JLabel("Professeur : ");
    JPanel profPanel = new JPanel(); 
    profPanel.add(profLabel);
    profPanel.add(professeur);
    
    JLabel salleLabel = new JLabel("Salle : ");
    
//juste un test 
    JComboBox sallefiltre = new JComboBox();
    
      liste=conn.Affich("Select Nom from cours ");
         for(int i=0;i<1;i++)
       {
      sallefiltre.addItem(liste.get(i));
      sallefiltre.addItem(liste.get(i+1));
      sallefiltre.addItem(liste.get(i+2));
      sallefiltre.addItem(liste.get(i+3));
       }
         
         
    JPanel FiltreRecap  = new JPanel();
    FiltreRecap.add(salleLabel);
    FiltreRecap.add(sallefiltre);
    FiltreRecap.add(coursLabel);
    FiltreRecap.add(cours);
    FiltreRecap.add(profLabel);
    FiltreRecap.add(professeur);
    
    JScrollPane conteneurRec = new JScrollPane(monRecap);
         
    FenetreRecap.setLayout(new BorderLayout());
   //Contenu du haut
    FenetreRecap.add(FiltreRecap,BorderLayout.NORTH);
    //Contenu du centre
    FenetreRecap.add(conteneurRec, BorderLayout.CENTER);
 }
 
public void defineMaj()
{
    JPanel filtreCours = new JPanel();
    JPanel coursActif = new JPanel();
    JPanel ModifCours = new JPanel();
    BoutonInt modifierB = new BoutonInt("modifier");
    BoutonInt AjouterB = new BoutonInt("Ajouter");
    BoutonInt SupprimerB = new BoutonInt("Supprimer");
    
    Object[][] coursActifTab = {
             {"Mathématique(Test)","15h30-17h","15 juin","Mme Coudray","1h30",modifierB,SupprimerB},
             {"Mathématique(Test)","15h30-17h","15 juin","Mme Coudray","1h30",modifierB,SupprimerB}
         };
     
     String[] coursActifTitle = {"Matière","Horaires", "Date","Professeur","durée","Modifier","Supprimer"};
    
    MonModel modelMaj = new MonModel(coursActifTab,coursActifTitle);
      JTable coursMaj = new JTable(modelMaj);
      coursMaj.setDefaultRenderer(BoutonInt.class, new ComposantTable());
      coursMaj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      TableColumn col1 = coursMaj.getColumnModel().getColumn(0);
      col1.setPreferredWidth(200);
      TableColumn col2 = coursMaj.getColumnModel().getColumn(1);
      col2.setPreferredWidth(100);
      TableColumn col4 = coursMaj.getColumnModel().getColumn(3);
      col4.setPreferredWidth(100);
      TableColumn col6 = coursMaj.getColumnModel().getColumn(5);
      col6.setPreferredWidth(200);
      TableColumn col7 = coursMaj.getColumnModel().getColumn(6);
      col7.setPreferredWidth(200);
      
      JScrollPane conteneurMaj = new JScrollPane(coursMaj);
      conteneurMaj.setPreferredSize(new Dimension(950,200));
     
      coursActif.add(conteneurMaj);
    
    FenetreMaj.setLayout(new GridLayout(3,1));
    FenetreMaj.add(filtreCours);
    FenetreMaj.add(coursActif);
    FenetreMaj.add(ModifCours);
    
    
}
}


