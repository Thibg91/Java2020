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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    private Connection conn= null;
    private Connexion_sql connliste=new Connexion_sql();
    private BoutonInt bouton1 = new BoutonInt("Semaine 1");
    private BoutonInt bouton2 = new BoutonInt("Semaine 2");
    private BoutonInt bouton3 = new BoutonInt("Semaine 3");
    private BoutonInt bouton4 = new BoutonInt("Semaine 4");
    private BoutonInt modifierB = new BoutonInt("modifier");
    
    private JMenuBar Navigation = new JMenuBar();
    private BoutonInt boutonCal = new BoutonInt("Emploi du temps");
    private BoutonInt boutonRec = new BoutonInt("Recap");
    private BoutonInt boutonMaj = new BoutonInt("Mise à jour");
    private BoutonInt boutonRep = new BoutonInt("Reporting");
    
    private JPanel FenetreCalendrier = new JPanel();
    private JPanel FenetreRecap = new JPanel();
    private JPanel FenetreMaj = new JPanel();
    private JPanel FenetreReporting = new JPanel();
   
    private JPanel ModifCours = new JPanel();
    
   
    //constructeur de la classe
    public Fenetre(Connection conn) throws ClassNotFoundException, SQLException {
        this.conn=conn;
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
   
   
   
   
          JPanel firstPanel = new JPanel();
    JTextPane contenu = new JTextPane();
         
    contenu.setEditable(false);
         
 
    //c'est avec ca qu'on centre le texte dans une case
    StyledDocument doc = contenu.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
doc.setParagraphAttributes(0, doc.getLength(), center, false);
    
    
    //ici on déclare le meme type de composant mais il est vide, on va initialiser le tableau en le remplissant de Voidcontenu
    JTextPane Voidcontenu = new JTextPane();
    Voidcontenu.setEditable(false);
    Voidcontenu.setText("");
    Voidcontenu.setBounds( 501, 51, 200, 100);
    
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
      liste=connliste.Affich("Select Nom from cours ");
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
    liste=connliste.Affich("Select Nom from utilisateurs where Droit = 3");
         for(int i=0;i<liste.size();i++)
       {
      professeur.addItem(liste.get(i));
    
       }
  
    JLabel profLabel = new JLabel("Professeur : ");
    JPanel profPanel = new JPanel(); 
    profPanel.add(profLabel);
    profPanel.add(professeur);
    
    JLabel salleLabel = new JLabel("Salle : ");
    
//juste un test 
    JComboBox sallefiltre = new JComboBox();
    
      liste=connliste.Affich("Select Nom from cours ");
         for(int i=0;i<liste.size();i++)
       {
      sallefiltre.addItem(liste.get(i));
    
       }
    
    rightLayout.add(coursPanel);
    rightLayout.add(profPanel);
    
    

  
    
     //Login monLogin = new Login();
    //monLogin.setVisible(true);

    defineMaj();

    
    bouton1.addActionListener(this);
    bouton2.addActionListener(this);
    bouton3.addActionListener(this);
    bouton4.addActionListener(this);
    boutonCal.addActionListener(this);
    boutonRec.addActionListener(this);
    boutonMaj.addActionListener(this);
    boutonRep.addActionListener(this);
     Statement stmt = conn.createStatement();
     int cpt=0;
     int idgr=0;
     int idprof=0;
     String nomprof="";
     String idseance="";
     ResultSet rs=stmt.executeQuery("Select id_groupe from etudiant Where Id_utilisateurs=8");
 
        while(rs.next()){
           
          idgr = rs.getInt("Id_groupe");
           
       }
         
         liste=connliste.Affich("Select id_seance from seance_groupe Where id_groupe=" + idgr);
         cpt=liste.size();
    for(int i=0;i<=cpt-1;i++)      //Ici on va créer les cases de cours
         {
       
        idseance=liste.get(i);
        System.out.println(idseance);
        rs=stmt.executeQuery("Select id_enseignant from seance_enseignant Where id_seance=" + idseance);
        while(rs.next())
        {
           idprof=rs.getInt("id_enseignant");
           System.out.println("idprof:" +idprof);
        }
        rs=stmt.executeQuery("Select Nom from Utilisateurs Where ID=" + idprof);
        while(rs.next())
        {
           nomprof=rs.getString("Nom");
           
        }
           
        
       
         if(i==0)
         {
              String recap = nomprof + "\n"  + "\n" + promo + "\n" + salle + "\r\n" ;
                contenu.setText(recap);
             monTableau.ajouterCours (contenu, 6, 5);
         }
         if(i==1)
         {    
              String recap = nomprof + "\n" + prof + "\n" + promo + "\n" + salle + "\r\n" ;
                contenu.setText(recap);
                monTableau.ajouterCours (contenu, 5, 5);
         }
    contenu.setBackground(Color.magenta);  //creation case
    contenu.setEditable(false);
    
         }
    
    
    
    
    JScrollPane conteneurCal = new JScrollPane(monTableau);
    

    //Login monLogin = new Login();
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
        
        if(arg0.getSource() == modifierB )
        {
           System.out.println("J'ai cliqué sur le bouton Modifier");
           ModifCours.setVisible(true);
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
     liste=connliste.Affich("Select Nom from utilisateurs Where ID=16");
         for(int i=0;i<liste.size();i++)
       {
           
          prof = liste.get(i);
           
       }
         liste=connliste.Affich("Select Id_cours from enseignant Where ID_utilisateurs=16");
           for(int i=0;i<liste.size();i++)
       {
           
          id_cours = liste.get(i);
           
       }
                    liste=connliste.Affich("Select Nom  from cours Where ID="+ id_cours);
           for(int i=0;i<liste.size();i++)
       {
           
          nomcours = liste.get(i);
           
       }
     JComboBox cours = new JComboBox();
      liste=connliste.Affich("Select Nom from cours ");
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
    liste=connliste.Affich("Select Nom from cours ");
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
    
      liste=connliste.Affich("Select Nom from cours ");
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
    Font font1 = new Font("Arial",Font.BOLD,32);
     Font font2 = new Font("Arial",Font.BOLD,18);
    JPanel filtreCours = new JPanel();
    filtreCours.setBackground(Color.white);
    JPanel coursActif = new JPanel();
    coursActif.setBackground(Color.LIGHT_GRAY);
    ModifCours.setVisible(false);
    ModifCours.setBackground(Color.white);
    JPanel AjouterCours = new JPanel();
    AjouterCours.setBackground(Color.LIGHT_GRAY);
    AjouterCours.setLayout(new GridLayout(5,4));
    BoutonInt AjouterB = new BoutonInt("Ajouter");
    BoutonInt SupprimerB = new BoutonInt("Supprimer");
    
    Object[][] coursActifTab = {
             {"Mathématique(Test)","15h30-17h","15 juin","Mme Coudray","1h30",modifierB,SupprimerB},
             {"Mathématique(Test)","15h30-17h","15 juin","Mme Coudray","1h30",modifierB,SupprimerB}
         };
     
     String[] coursActifTitle = {"Matière","Horaires", "Date","Professeur","durée","Modifier","Supprimer"};
    
    MonModel modelMaj = new MonModel(coursActifTab,coursActifTitle);
      JTable coursMaj = new JTable(modelMaj);
      coursMaj.getColumn("Modifier").setCellRenderer(new BoutonTableau());
      coursMaj.getColumn("Supprimer").setCellRenderer(new BoutonTableau());
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
      conteneurMaj.setPreferredSize(new Dimension(925,1000));
     
      coursActif.add(conteneurMaj);
    
      JLabel LabelAjout = new JLabel("Ajouter un cours");
       LabelAjout.setFont(font1);
      JLabel labelMatiere = new JLabel("Matière :");
      labelMatiere.setFont(font2);
      JLabel labelSemaine = new JLabel("Semaine :");
      labelSemaine.setFont(font2);
      JLabel labelDate = new JLabel("Date :");
      labelDate.setFont(font2);
      JLabel labelHeureD = new JLabel("Heure de début :");
      labelHeureD.setFont(font2);
      JLabel labelHeureF = new JLabel("Heure de fin :");
      labelHeureF.setFont(font2);
      JLabel labelEtat = new JLabel("Etat :");
      labelEtat.setFont(font2);
      JLabel labelType = new JLabel("Type :");
      labelType.setFont(font2);
      JLabel labelSalle = new JLabel("Salle :");
      labelSalle.setFont(font2);
      
      JTextField TFMatiere = new JTextField();
      JTextField TFSemaine = new JTextField();
      JTextField TFDate = new JTextField();
      JTextField TFHeureD = new JTextField();
      JTextField TFHeureF = new JTextField();
      JTextField TFEtat = new JTextField();
      JTextField TFType = new JTextField();
      JTextField TFSalle = new JTextField();
     
      AjouterCours.add(LabelAjout);
      AjouterCours.add(new JLabel(""));
      
      AjouterCours.add(labelMatiere);
      AjouterCours.add(TFMatiere);
      
      AjouterCours.add(labelSemaine);
      AjouterCours.add(TFSemaine);
      
      AjouterCours.add(labelDate);
      AjouterCours.add(TFDate);
      
      AjouterCours.add(labelHeureD);
      AjouterCours.add(TFHeureD);
      
      AjouterCours.add(labelHeureF);
      AjouterCours.add(TFHeureF);
      
      AjouterCours.add(labelEtat);
      AjouterCours.add(TFEtat);
      
      AjouterCours.add(labelType);
      AjouterCours.add(TFType);
      
      AjouterCours.add(labelSalle);
      AjouterCours.add(TFSalle);
      
      AjouterCours.add(new JLabel(""));
      AjouterCours.add(AjouterB);
      
    FenetreMaj.setLayout(new GridLayout(4,1));
    FenetreMaj.add(filtreCours);
    FenetreMaj.add(coursActif);
    FenetreMaj.add(ModifCours);
    FenetreMaj.add(AjouterCours);
    
    
}

public void modifierUnCours (){
    Font font2 = new Font("Arial",Font.BOLD,18);
    JLabel labelMatiere = new JLabel("Matière :");
     labelMatiere.setFont(font2);
     JTextField TFMatiere = new JTextField("(Test)Maths");
   JLabel labelDate = new JLabel("Date :");
     labelDate.setFont(font2);
     JTextField TFDate = new JTextField("(Test)15 juin");
   JLabel labelHoraireD = new JLabel("Début du cours :");
     labelDate.setFont(font2);
     JTextField TFHorD = new JTextField("(Test)12h00");
   JLabel labelHoraireF = new JLabel("Fin du cours :");
     labelDate.setFont(font2);
     JTextField TFHorF = new JTextField("(Test)13h30");
   JLabel labelDuree = new JLabel("Durée :");
     labelDate.setFont(font2);
     JTextField TFduree = new JTextField("(Test)1H30");
}
   

}



