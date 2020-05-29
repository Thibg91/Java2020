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
public class Fenetre extends JFrame {
    
    private JTable monTableau;
    private JButton testButton = new JButton("je suis un bouton de test");
    
    public Fenetre() {
    // déclaration de la fenetre
    this.setSize(1500, 1000);
    this.setTitle("Mon Calendrier");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
   
    
    String matiere = "VHDL";
    String prof = "Lopes";
    String groupe = "ING3 Gr9";
    String salle = "Salle P416";
    String recap = matiere + "\r\n"+ prof + "\r\n"+ groupe + "\r\n"+ salle + "\r\n" ;
    
    JPanel firstPanel = new JPanel();
    //firstPanel.setBackground(Color.blue);
    firstPanel.add(new JButton ("T'y es en mode lacoste tn le boss"));
    firstPanel.add(new JButton ("moi oui "));
    
  
    JTextPane contenu = new JTextPane();
    contenu.setBackground(Color.magenta);
    contenu.setEditable(false);
    contenu.setText(recap);
    contenu.setBounds( 51, 51, 200, 70 );
    
    JTextPane Voidcontenu = new JTextPane();
    Voidcontenu.setEditable(false);
    Voidcontenu.setText("");
    Voidcontenu.setBounds( 51, 51, 200, 70 );
    
    
    
    
    
    JPanel firstColumnPane = new JPanel();
    JTextPane firstColumn = new JTextPane();
    firstColumn.setBackground(Color.lightGray);
    firstColumn.setEditable(false);
    firstColumn.setText("\r\n \r\n \r\n \r\n" + "8h30-10h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"10h15-11h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"12h00-13h30" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"13h45-15h15" + "\r\n \r\n \r\n \r\n \r\n \r\n \r\n" +"15h30-17h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"17h15-18h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" +"19h30-21h00");
    firstColumn.setBounds( 0, 0, 80, 1000 );
    firstColumnPane.setLayout(null);
    firstColumnPane.add(firstColumn);
    firstColumnPane.setPreferredSize(new Dimension (80,1000));
    
    
    
    JPanel changePage = new JPanel();
    changePage.setBackground(Color.lightGray);
    JButton bouton1 = new JButton("1");
    JButton bouton2 = new JButton("2");
    JButton bouton3 = new JButton("3");
    JButton bouton4 = new JButton("4");
    
    changePage.add(bouton1);
    changePage.add(bouton2);
    changePage.add(bouton3);
    changePage.add(bouton4);
    
    
//partie Tableau
    
   /* 
    Object[][] test_line = {
        {"","","","","",""},
        {"","","","","",""},
        {"","","","","",""},
        {"","","","","",""},
        {"","","","","",""},
        {"","","","","",""},
        {"","","","","",""}
    };  */
    
    
    
    
     Object[][] test_line = {
        {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
        {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
        {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu},
         {Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu,Voidcontenu}   
    };  
     
    
      test_line[1][3] = contenu;
     
    
    String[] test_column = {"Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
   
   
   MonModel model = new MonModel(test_line,test_column);
   
   this.monTableau = new JTable(model); 
   this.monTableau.setRowHeight(100);
   this.monTableau.setDefaultRenderer(JTextPane.class, new ComposantTable());
   
   
   //Partie filtre
   
    JPanel SecondPanel = new JPanel();
    SecondPanel.setBackground(Color.lightGray);
    JComboBox cours = new JComboBox();
    cours.addItem("maths");
    cours.addItem("physique");
    cours.addItem("traitement du signal");
    cours.addItem("Java");
    cours.addItem("Droit du travail");
    JLabel coursLabel = new JLabel("Cours : ");
    SecondPanel.add(coursLabel);
    SecondPanel.add(cours);
    
    JScrollPane conteneurTab = new JScrollPane(monTableau);
    
    
    
    
    
    //partie Layout 
    this.setLayout(new BorderLayout());
    
    
    //Contenu du centre
    
    //this.getContentPane().add(firstPanel,BorderLayout.CENTER);
    //this.getContentPane().add("",BorderLayout.CENTER);
    //this.getContentPane().add(firstColumnPane,BorderLayout.CENTER);
    this.getContentPane().add(conteneurTab, BorderLayout.CENTER);
    
    //Contenu du haut
    this.getContentPane().add(new JButton("Barre de navigation"),BorderLayout.NORTH);
    
    //contenu du bas (surement rien mais a voir)
    this.getContentPane().add(changePage,BorderLayout.SOUTH);
    
    //contenu de gauche
    this.getContentPane().add(firstColumnPane,BorderLayout.WEST);
    
    //contenu de Droite ( surement le menu )
    this.getContentPane().add(SecondPanel,BorderLayout.EAST);
    
   
    //Cacher la fenetre ou pas : bool 
    this.setVisible(true); 
   
    }    
    
   

}
