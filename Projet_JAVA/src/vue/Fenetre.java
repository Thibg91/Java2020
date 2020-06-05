/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.Connexion_sql;
import controler.DAO;
import controler.DAOEtudiant;
import controler.DAOSeance;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Etudiant;
import modele.Seance;
import modele.Utilisateur;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author titig
 */
//classe principale de l'affichage ( il faut que je module ce programme en différentes fonctions)
public final class Fenetre extends JFrame implements ActionListener {

    //déclaration du tableau
    private Calendrier monTableau;
    private JTable monRecap;
    private JTable coursMaj;
    private JLabel salleLabel = new JLabel("Salle : ");
    private JLabel coursLabel = new JLabel("Cours : ");
    private JLabel profLabel = new JLabel("Professeur : ");
    private Connection conn = null;
    private Connexion_sql connliste = new Connexion_sql();
    private BoutonInt bouton1 = new BoutonInt("Semaine 1");
    private BoutonInt bouton2 = new BoutonInt("Semaine 2");
    private BoutonInt bouton3 = new BoutonInt("Semaine 3");
    private BoutonInt bouton4 = new BoutonInt("Semaine 4");
    private BoutonInt bouton5 = new BoutonInt("Filtrer");
    private JMenuBar Navigation = new JMenuBar();
    private BoutonInt boutonCal = new BoutonInt("Emploi du temps");
    private BoutonInt boutonRec = new BoutonInt("Recap");
    private BoutonInt boutonMaj = new BoutonInt("Mise à jour");
    private BoutonInt boutonRep = new BoutonInt("Reporting");
    private BoutonInt boutonAjout = new BoutonInt("Ajouter");
    private BoutonInt ValiderModif = new BoutonInt("Valider");
    private JComboBox sallefiltre = new JComboBox();
    private JComboBox professeur = new JComboBox();
    private JComboBox cours = new JComboBox();
    private JPanel FenetreCalendrier = new JPanel();
    private JPanel FenetreRecap = new JPanel();
    private JPanel FenetreMaj = new JPanel();
    private JPanel FenetreReporting = new JPanel();
    private Etudiant student;

    private JPanel ModifCours = new JPanel();
    private Object[] objetAjout = null;
    private ArrayList<String> liste;
    private ArrayList<String> liste2;
    private ArrayList<String> liste3;


    //constructeur de la classe
    public Fenetre(Connection conn, Utilisateur user) throws ClassNotFoundException, SQLException {
        this.conn = conn;
        // déclaration de la fenetre
        String prof = "Coudray";
        String id_cours = "";
        String nomcours = "Maths";
        String promo = "Ing3";

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
        
        FenetreReporting.setLayout(null);

        //c'est avec ca qu'on centre le texte dans une case
        StyledDocument doc = contenu.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        //ici on déclare le meme type de composant mais il est vide, on va initialiser le tableau en le remplissant de Voidcontenu
        JTextPane Voidcontenu = new JTextPane();
        Voidcontenu.setEditable(false);
        Voidcontenu.setText("");
        Voidcontenu.setBounds(501, 51, 200, 100);

        //Ici c'est juste la colonne qui affiche les heures du tableau
        JPanel firstColumnPane = new JPanel();
        JTextPane firstColumn = new JTextPane();
        firstColumn.setBackground(Color.lightGray);
        firstColumn.setEditable(false);

        firstColumn.setText("\r\n \r\n \r\n \r\n" + "8h30-10h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "10h15-11h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "12h00-13h30" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "13h45-15h15" + "\r\n \r\n \r\n \r\n \r\n \r\n \r\n" + "15h30-17h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "17h15-18h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "19h00-20h30");

        firstColumn.setBounds(0, 0, 80, 1000);
        firstColumnPane.setLayout(null);
        firstColumnPane.add(firstColumn);
        firstColumnPane.setPreferredSize(new Dimension(80, 1000));

        // Ici on a les boutons de bas de page qui vont permettre de passer d'une semaine a une autre
        JPanel changePage = new JPanel();
        changePage.setBackground(Color.lightGray);
        changePage.add(bouton1);
        changePage.add(bouton2);
        changePage.add(bouton3);
        changePage.add(bouton4);

        //test de case avec des données
        this.initCalendrier();

        //Partie filtre : c'est la partie sur la droite de la page qui va contenir tout les filtres utiles sur notre tableau
        JPanel rightLayout = new JPanel();
        rightLayout.setLayout(new GridLayout(7, 1));
        JPanel coursPanel = new JPanel();
        JComboBox cours = new JComboBox();
        //coursPanel.setBackground(Color.lightGray);
        liste = connliste.Affich("Select Nom from cours ");
        for (int i = 0; i < 1; i++) {
            cours.addItem(liste.get(i));
            cours.addItem(liste.get(i + 1));
            cours.addItem(liste.get(i + 2));
            cours.addItem(liste.get(i + 3));
        }
        System.out.println("le cours est : " + (String) liste.get(2));
        JLabel coursLabel = new JLabel("Cours : ");
        coursPanel.add(coursLabel);
        coursPanel.add(cours);

        //juste un test 
        JComboBox professeur = new JComboBox();
        liste = connliste.Affich("Select Nom from utilisateurs where Droit = 3");
        for (int i = 0; i < liste.size(); i++) {
            professeur.addItem(liste.get(i));

        }

        JLabel profLabel = new JLabel("Professeur : ");
        JPanel profPanel = new JPanel();
        profPanel.add(profLabel);
        profPanel.add(professeur);

        JLabel salleLabel = new JLabel("Salle: ");

//juste un test 
        JComboBox sallefiltre = new JComboBox();

        liste = connliste.Affich("Select Nom from salle ");
        for (int i = 0; i < liste.size(); i++) {
            sallefiltre.addItem(liste.get(i));

        }

        rightLayout.add(coursPanel);
        rightLayout.add(profPanel);

        defineMaj();
      

        bouton1.addActionListener(this);
        bouton2.addActionListener(this);
        bouton3.addActionListener(this);
        bouton4.addActionListener(this);
        boutonCal.addActionListener(this);
        boutonRec.addActionListener(this);
        boutonMaj.addActionListener(this);
        boutonAjout.addActionListener(this);
        boutonRep.addActionListener(this);
        ValiderModif.addActionListener(this);
        bouton5.addActionListener(new Filtre());
        Statement stmt = conn.createStatement();
        Statement stt = conn.createStatement();
        int cpt = 0;
        int idgr = 0;
        int idprof = 0;
        int idseance = 0;
        int id_promo = 0;
        int id_salle = 0;
        int idcours = 0;
        ResultSet rs = null;
        String nomprof = "";
        String nom_promo = "";
        String nom_salle = "";
        String nom_cours = "";
        if (user.getDroit() == 4) {
            this.student = (Etudiant) user;
            rs = stmt.executeQuery("Select id_groupe from etudiant Where Id_utilisateurs=" + student.getID());
            while (rs.next()) {
                idgr = rs.getInt("id_groupe");
            }
            rs = stmt.executeQuery("Select * from groupe Where ID=" + idgr);
            while (rs.next()) {
                id_promo = rs.getInt("ID_promotion");
            }
            rs = stmt.executeQuery("Select * from promotion Where ID=" + id_promo);
            while (rs.next()) {
                nom_promo = rs.getString("Nom");
            }
        }
        // Recherche des seances selon le groupe de l'etudiant
        rs = stt.executeQuery("Select id_seance from seance_groupe Where id_groupe=" + idgr);
        while (rs.next())//Ici on va créer les cases de cours
        {
            nomprof = "";
            nom_salle = "";
            nom_cours = "";
            idseance = rs.getInt("id_seance");
            Statement stm = conn.createStatement();
            // Recherche des profs
            ResultSet res = stm.executeQuery("Select id_enseignant from seance_enseignant Where id_seance=" + idseance);
            while (res.next()) {
                idprof = res.getInt("id_enseignant");
                Statement stmts = conn.createStatement();
                ResultSet ress = stmts.executeQuery("Select * from utilisateurs Where ID=" + idprof);
                while (ress.next()) {
                    nomprof = nomprof + " " + ress.getString("Nom");
                }
            }
            // Recherche de la salle
            res = stm.executeQuery("Select id_salle from seance_salle Where id_seance=" + idseance);
            while (res.next()) {
                id_salle = res.getInt("id_salle");
                Statement stmts = conn.createStatement();
                ResultSet ress = stmts.executeQuery("Select * from salle Where ID=" + id_salle);
                while (ress.next()) {
                    nom_salle = nom_salle + " " + ress.getString("Nom");
                }
            }
            // Recherche de la matiere
            res = stm.executeQuery("Select * from seance Where ID=" + idseance);
            while (res.next()) {
                idcours = res.getInt("Id_cours");
                Statement stmts = conn.createStatement();
                ResultSet ress = stmts.executeQuery("Select * from cours Where ID=" + idcours);
                while (ress.next()) {
                    nom_cours = ress.getString("Nom");
                }
            }
            DAO<Seance> amphi = new DAOSeance(conn);
            Seance seance = amphi.find(idseance);
            String row_col = "";
            row_col = insererSeance(seance);
            String recap = "";
            recap = nom_cours + "\n" + nomprof + "\n" + "\n" + nom_promo + "\n" + nom_salle + "\r\n";
            JTextPane contenue = new JTextPane();
            contenue.setText(recap);
            //System.out.println((String)contenue.getText());
            monTableau.ajouterCours(contenue, Integer.parseInt(row_col.substring(0, 1)), Integer.parseInt(row_col.substring(1, 2)));
            if (nom_cours.equals("Mathematiques")) {
                contenue.setBackground(Color.magenta);  //creation case
            }
            if (nom_cours.equals("Probabilites")) {
                contenue.setBackground(Color.CYAN);
            }
            if (nom_cours.equals("Electronique")) {
                contenue.setBackground(Color.YELLOW);
            }
            if (nom_cours.equals("Physique")) {
                contenue.setBackground(Color.RED);
            }
            contenue.setEditable(false);
        }

        JScrollPane conteneurCal = new JScrollPane(monTableau);

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
        FenetreCalendrier.add(changePage, BorderLayout.SOUTH);
        //contenu de gauche
        FenetreCalendrier.add(firstColumnPane, BorderLayout.WEST);
        //contenu de Droite 
        FenetreCalendrier.add(rightLayout, BorderLayout.EAST);
        this.initRecap("", "", "");
          defineReporting();
        defineRecap();

        this.setContentPane(FenetreCalendrier);
        //Cacher la fenetre ou pas : bool 
        this.setVisible(true);

    }

    // nécessite des modifs mais permet d' 
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == boutonCal) {
            System.out.println("J'ai cliqué sur le bouton Calendrier");
            this.setContentPane(FenetreCalendrier);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        if (arg0.getSource() == boutonRec) {
            System.out.println("J'ai cliqué sur le bouton Recap");
            this.setContentPane(FenetreRecap);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        if (arg0.getSource() == boutonMaj) {
            System.out.println("J'ai cliqué sur le bouton Mise à jour");
            this.setContentPane(FenetreMaj);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        if (arg0.getSource() == boutonRep) {
            System.out.println("J'ai cliqué sur le bouton Reporting");
            this.setContentPane(FenetreReporting);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        /// Ajouter un cours
        if (arg0.getSource() == boutonAjout) {
            System.out.println("J'ai cliqué sur le bouton Ajout");
            JPanel tempPanel = (JPanel) boutonAjout.getParent();
            JTextField matiere = (JTextField) tempPanel.getComponent(3);
            JTextField date = (JTextField) tempPanel.getComponent(5);
            JTextField debut = (JTextField) tempPanel.getComponent(7);
            JTextField fin = (JTextField) tempPanel.getComponent(9);
            JTextField etat = (JTextField) tempPanel.getComponent(11);
            JTextField type = (JTextField) tempPanel.getComponent(13);
            JTextField salle = (JTextField) tempPanel.getComponent(15);
            JTextField prof = (JTextField) tempPanel.getComponent(17);
            JTextField promo = (JTextField) tempPanel.getComponent(19);
            JTextField groupe = (JTextField) tempPanel.getComponent(21);
            Date jour = Date.valueOf((String) date.getText());
            Calendar cal = Calendar.getInstance();
            //La première semaine de l'année est celle contenant au moins 4 jours
            cal.setMinimalDaysInFirstWeek(4);
            cal.setTime(jour);
            int week = cal.get(Calendar.WEEK_OF_YEAR);
            int types = Integer.parseInt((String) type.getText());
            String mat = (String) matiere.getText();
            String room = (String) salle.getText();
            String groupes = (String) groupe.getText();
            String promos = (String) promo.getText();
            Time Debut = Time.valueOf((String) debut.getText());
            Time Fin = Time.valueOf((String) fin.getText());

            Statement stmt = null;
            int id_salle = 0;
            int id_groupe = 0;
            int id_promo = 0;
            int id_prof = 0;
            int id_matiere = 0;
            /// Recherche id de la matiere
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from cours WHERE Nom='" + mat + "'");
                while (rs.next()) {
                    id_matiere = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

            Seance seance = new Seance(0, week, jour, Debut, Fin, (String) etat.getText(), id_matiere, types);
            DAO<Seance> amphi = new DAOSeance(this.conn);
            seance = amphi.create(seance);

            /// Creation dans la table seance_salle
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from salle WHERE Nom='" + room + "'");
                while (rs.next()) {
                    id_salle = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT into seance_salle (id_seance, id_salle) VALUES ('" + seance.getId() + "','" + id_salle + "')");
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            /// Creation dans la table seance_enseignant
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from utilisateurs WHERE Nom='" + (String) prof.getText() + "'");
                while (rs.next()) {
                    id_prof = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT into seance_enseignant (id_seance, id_enseignant) VALUES ('" + seance.getId() + "','" + id_prof + "')");
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            /// Creation dans a table seance_groupe
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from promotion WHERE Nom='" + promos + "'");
                while (rs.next()) {
                    id_promo = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from groupe WHERE Nom='" + groupes + "' and ID_promotion='" + id_promo + "'");
                while (rs.next()) {
                    id_groupe = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT into seance_groupe (id_seance, id_groupe) VALUES ('" + seance.getId() + "','" + id_groupe + "')");
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Ma seance est:" + seance.getId());
        }
        /// Modifier un cours
        if (arg0.getSource() == ValiderModif) {
            System.out.println("J'ai cliqué sur le bouton Modifier");
            JPanel tempPanel = (JPanel) ValiderModif.getParent();
            JTextField matiere = (JTextField) tempPanel.getComponent(1);
            JTextField date = (JTextField) tempPanel.getComponent(3);
            JTextField debut = (JTextField) tempPanel.getComponent(5);
            JTextField fin = (JTextField) tempPanel.getComponent(7);
            JTextField etat = (JTextField) tempPanel.getComponent(9);
            JTextField type = (JTextField) tempPanel.getComponent(11);
            JTextField salle = (JTextField) tempPanel.getComponent(13);
            JTextField prof = (JTextField) tempPanel.getComponent(15);
            JTextField promo = (JTextField) tempPanel.getComponent(17);
            JTextField groupe = (JTextField) tempPanel.getComponent(19);
            Date jour = Date.valueOf((String) date.getText());
            Calendar cal = Calendar.getInstance();
            //La première semaine de l'année est celle contenant au moins 4 jours
            cal.setMinimalDaysInFirstWeek(4);
            cal.setTime(jour);
            int week = cal.get(Calendar.WEEK_OF_YEAR);
            String mat = (String) matiere.getText();
            String room = (String) salle.getText();
            String groupes = (String) groupe.getText();
            String promos = (String) promo.getText();
            Time Debut = Time.valueOf((String) debut.getText());
            Time Fin = Time.valueOf((String) fin.getText());

            Statement stmt = null;
            int id_salle = 0;
            int id_groupe = 0;
            int id_promo = 0;
            int id_prof = 0;
            int id_matiere = 0;
            int types = 0;
            /// Recherche id de la matiere
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from cours WHERE Nom='" + mat + "'");
                while (rs.next()) {
                    id_matiere = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from type_cours WHERE Nom='" + (String) type.getText() + "'");
                while (rs.next()) {
                    types = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }

            Seance seance = new Seance(0, week, jour, Debut, Fin, (String) etat.getText(), id_matiere, types);
            DAO<Seance> amphi = new DAOSeance(this.conn);
            seance = amphi.update(seance);
            /// Update dans la table seance_salle
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from salle WHERE Nom='" + room + "'");
                while (rs.next()) {
                    id_salle = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("Update seance_salle SET id_salle=" + id_salle + " WHERE id_seance=" + seance.getId());
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            /// Update dans la table seance_enseignant
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from utilisateurs WHERE Nom='" + (String) prof.getText() + "'");
                while (rs.next()) {
                    id_prof = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("Update seance_enseignant SET id_enseignant=" + id_prof + " WHERE id_seance=" + seance.getId());
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            /// Update dans a table seance_groupe
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from promotion WHERE Nom='" + promos + "'");
                while (rs.next()) {
                    id_promo = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select * from groupe WHERE Nom='" + groupes + "' and ID_promotion='" + id_promo + "'");
                while (rs.next()) {
                    id_groupe = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("Update seance_groupe SET id_groupe=" + id_groupe + " WHERE id_seance=" + seance.getId());
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            resize();
            FenetreMaj.removeAll();
            try {
                defineMaj();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

//initialise un calendrier
    public void initCalendrier() {
        //ici on déclare le meme type de composant mais il est vide, on va initialiser le tableau en le remplissant de Voidcontenu
        JTextPane Voidcontenu = new JTextPane();
        Voidcontenu.setEditable(false);
        Voidcontenu.setText("");
        Voidcontenu.setBounds(51, 51, 200, 70);

        //partie Tableau
        Object[][] test_line = {
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu},
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu},
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu},
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu},
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu},
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu},
            {Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu, Voidcontenu}
        };

        //1ere ligne du tableau avec les différents jours
        String[] Jour = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

        //modele de tableau permettant de définir la forme du tableau, par exemple on a changé la taille des cases 
        MonModel modelAgenda = new MonModel(test_line, Jour);

        this.monTableau = new Calendrier(modelAgenda);
        this.monTableau.setRowHeight(100);
        this.monTableau.setDefaultRenderer(JTextPane.class, new ComposantTable());

    }

//initialise le tableau de récap
    public void initRecap(String salle2, String Mat, String prof) throws SQLException, ClassNotFoundException {

        int idgrp = student.getGroupe();
        int idseance = 0;
        Seance amphi = null;
        ArrayList<Integer> arraylistProf = new ArrayList<Integer>();
        ArrayList<Integer> arraylistMatiere = new ArrayList<Integer>();
        ArrayList<Integer> arraylistSalle = new ArrayList<Integer>();
        int id = 0, semaine = 0, id_cours = 0, id_type = 0, id_promo = 0, id_groupe = 0, id_salle = 0, id_prof = 0, i = 0;
        Time debut = null, fin = null;
        Date date = null;
        String etat = null, nom_type = null, nom_matiere = null, nom_promo = null, nom_groupe = null, nom_salle = null, nom_prof = null;
        Object[][] coursRecap = new Object[100][10];
        Statement stmts = conn.createStatement();
        ResultSet r = stmts.executeQuery("select * from seance_groupe where id_groupe= " + idgrp);
        while (r.next()) {
            boolean okProf = false;
            boolean okMatiere = false;
            boolean okSalle = false;
            idseance = r.getInt("id_seance");
            Statement stmt = conn.createStatement();
            if (!prof.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where ID in (select id_seance from seance_enseignant Where id_enseignant in (select ID from utilisateurs where Nom='" + prof + "'))");
                while (ress.next()) {
                    arraylistProf.add(ress.getInt("ID"));
                }
                if (arraylistProf.contains(new Integer(idseance))) {
                    okProf = true;
                }
            } 
            if (prof.equals("")){
                okProf = true;
            }
            if (!Mat.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where Id_cours in (Select ID from cours Where Nom='" + Mat + "')");
                while (ress.next()) {
                    arraylistMatiere.add(ress.getInt("ID"));
                }
                System.out.println("");
                if (arraylistMatiere.contains(new Integer(idseance))) {
                    okMatiere = true;
                }
            } else {
                okMatiere = true;
            }
            if (!salle2.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where ID in (select id_seance from seance_salle Where id_salle in (select ID from salle where Nom='" + salle2 + "'))");
                while (ress.next()) {
                    arraylistSalle.add(ress.getInt("ID"));
                }
                if (arraylistSalle.contains(new Integer(idseance))) {
                    okSalle = true;
                }
            } else {
                okSalle = true;
            }
            if (okProf == true && okMatiere == true && okSalle == true) {
                ResultSet rs = stmt.executeQuery("select * from seance where ID=" + idseance);
                while (rs.next()) {
                    id = rs.getInt("ID");
                    etat = rs.getString("Etat");
                    semaine = rs.getInt("Semaine");
                    date = rs.getDate("Date");
                    debut = rs.getTime("Debut");
                    fin = rs.getTime("Fin");
                    id_cours = rs.getInt("Id_cours");
                    id_type = rs.getInt("Id_Typ");
                    Statement stt = conn.createStatement();
                    ResultSet res = stt.executeQuery("select * from seance_groupe WHERE id_seance=" + id);
                    while (res.next()) {
                        id_groupe = res.getInt("id_groupe");
                    }
                    res = stt.executeQuery("select * from groupe WHERE ID=" + id_groupe);
                    while (res.next()) {
                        nom_groupe = res.getString("Nom");
                        id_promo = res.getInt("ID_promotion");
                    }
                    res = stt.executeQuery("select * from promotion WHERE ID=" + id_promo);
                    while (res.next()) {
                        nom_promo = res.getString("Nom");
                    }
                    res = stt.executeQuery("select * from cours WHERE ID=" + id_cours);
                    while (res.next()) {
                        nom_matiere = res.getString("Nom");
                    }
                    res = stt.executeQuery("select * from type_cours WHERE ID=" + id_type);
                    while (res.next()) {
                        nom_type = res.getString("Nom");
                    }
                    res = stt.executeQuery("select * from seance_salle WHERE id_seance=" + id);
                    while (res.next()) {
                        id_salle = res.getInt("id_salle");
                    }
                    res = stt.executeQuery("select * from salle WHERE ID=" + id_salle);
                    while (res.next()) {
                        nom_salle = res.getString("Nom");
                    }
                    res = stt.executeQuery("select * from seance_enseignant WHERE id_seance=" + id);
                    while (res.next()) {
                        id_prof = res.getInt("id_enseignant");
                    }
                    res = stt.executeQuery("select * from utilisateurs WHERE ID=" + id_prof);
                    while (res.next()) {
                        nom_prof = res.getString("Nom");
                    }
                    coursRecap[i][0] = nom_matiere;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String text = df.format(date);
                    coursRecap[i][1] = text;
                    DateFormat def = new SimpleDateFormat("HH:mm:ss");
                    def.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String time_debut = def.format(debut);
                    coursRecap[i][2] = time_debut;
                    String time_fin = def.format(fin);
                    coursRecap[i][3] = time_fin;
                    coursRecap[i][4] = etat;
                    coursRecap[i][5] = nom_type;
                    coursRecap[i][6] = nom_salle;
                    coursRecap[i][7] = nom_prof;
                    coursRecap[i][8] = nom_promo;
                    coursRecap[i][9] = nom_groupe;
                    i++;
                }
            }
            String[] recapTitle = {"Matière", "Date", "Horaire début", "Horaire fin", "Etat", "Type", "Salle", "Professeur", "Promotion", "Groupe"};

            MonModel modelRecap = new MonModel(coursRecap, recapTitle);
            this.monRecap = new JTable(modelRecap);
        }
    }

    public void defineRecap() throws ClassNotFoundException, SQLException {
        String prof = "";
        String id_cours = "";
        String nomcours = "";
        ArrayList<String> liste;
        liste = connliste.Affich("Select Nom from utilisateurs Where ID=16");
        for (int i = 0; i < liste.size(); i++) {

            prof = liste.get(i);

        }
        liste = connliste.Affich("Select Id_cours from enseignant Where ID_utilisateurs=16");
        for (int i = 0; i < liste.size(); i++) {

            id_cours = liste.get(i);

        }
        liste = connliste.Affich("Select Nom  from cours Where ID=" + id_cours);
        for (int i = 0; i < liste.size(); i++) {

            nomcours = liste.get(i);

        }
        liste = connliste.Affich("Select Nom from cours ");
        cours.removeAllItems();
        cours.addItem("");
        for (int i = 0; i < liste.size(); i++) {
            cours.addItem(liste.get(i));

        }
        //juste un test 
        liste = connliste.Affich("Select Nom from utilisateurs where Droit=3 ");
        professeur.removeAllItems();
        professeur.addItem("");
        for (int i = 0; i < liste.size(); i++) {
            professeur.addItem(liste.get(i));

        }

        JPanel profPanel = new JPanel();
        profPanel.add(profLabel);
        profPanel.add(professeur);

        //LAAAAAAAa
//juste un test 
        liste = connliste.Affich("Select Nom from salle ");
        sallefiltre.removeAllItems();
        sallefiltre.addItem("");
        for (int i = 0; i < liste.size(); i++) {
            sallefiltre.addItem(liste.get(i));

        }

        JPanel FiltreRecap = new JPanel();
        FiltreRecap.add(salleLabel);
        FiltreRecap.add(sallefiltre);
        FiltreRecap.add(coursLabel);
        FiltreRecap.add(cours);
        FiltreRecap.add(profLabel);
        FiltreRecap.add(professeur);
        FiltreRecap.add(bouton5);
        JScrollPane conteneurRec = new JScrollPane(monRecap);

        FenetreRecap.setLayout(new BorderLayout());
        //Contenu du haut
        FenetreRecap.add(FiltreRecap, BorderLayout.NORTH);
        //Contenu du centre
        FenetreRecap.add(conteneurRec, BorderLayout.CENTER);
    }

    public void defineMaj() throws SQLException {
        Font font1 = new Font("Arial", Font.BOLD, 32);
        Font font2 = new Font("Arial", Font.BOLD, 18);
        JPanel filtreCours = new JPanel();
        filtreCours.setBackground(Color.white);
        JPanel coursActif = new JPanel();
        coursActif.setBackground(Color.LIGHT_GRAY);
        ModifCours.setVisible(false);
        ModifCours.setBackground(Color.white);
        JPanel AjouterCours = new JPanel();
        AjouterCours.setBackground(Color.LIGHT_GRAY);
        AjouterCours.setLayout(new GridLayout(6, 4));

        Seance amphi = null;
        int id = 0, semaine = 0, id_cours = 0, id_type = 0, id_promo = 0, id_groupe = 0, id_salle = 0, id_prof = 0;
        Time debut = null, fin = null;
        Date date = null;
        String etat = null, nom_type = null, nom_matiere = null, nom_promo = null, nom_groupe = null, nom_salle = null, nom_prof = null;
        Object[][] coursActifTab = new Object[100][12];;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from seance");
        int i = 0;
        while (rs.next()) {
            id = rs.getInt("ID");
            etat = rs.getString("Etat");
            semaine = rs.getInt("Semaine");
            date = rs.getDate("Date");
            debut = rs.getTime("Debut");
            fin = rs.getTime("Fin");
            id_cours = rs.getInt("Id_cours");
            id_type = rs.getInt("Id_Typ");
            Statement stt = conn.createStatement();
            ResultSet res = stt.executeQuery("select * from seance_groupe WHERE id_seance=" + id);
            while (res.next()) {
                id_groupe = res.getInt("id_groupe");
            }
            res = stt.executeQuery("select * from groupe WHERE ID=" + id_groupe);
            while (res.next()) {
                nom_groupe = res.getString("Nom");
                id_promo = res.getInt("ID_promotion");
            }
            res = stt.executeQuery("select * from promotion WHERE ID=" + id_groupe);
            while (res.next()) {
                nom_promo = res.getString("Nom");
            }
            res = stt.executeQuery("select * from cours WHERE ID=" + id_cours);
            while (res.next()) {
                nom_matiere = res.getString("Nom");
            }
            res = stt.executeQuery("select * from type_cours WHERE ID=" + id_type);
            while (res.next()) {
                nom_type = res.getString("Nom");
            }
            res = stt.executeQuery("select * from seance_salle WHERE id_seance=" + id);
            while (res.next()) {
                id_salle = res.getInt("id_salle");
            }
            res = stt.executeQuery("select * from salle WHERE ID=" + id_salle);
            while (res.next()) {
                nom_salle = res.getString("Nom");
            }
            res = stt.executeQuery("select * from seance_enseignant WHERE id_seance=" + id);
            while (res.next()) {
                id_prof = res.getInt("id_enseignant");
            }
            res = stt.executeQuery("select * from utilisateurs WHERE ID=" + id_prof);
            while (res.next()) {
                nom_prof = res.getString("Nom");
            }
            coursActifTab[i][0] = nom_matiere;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String text = df.format(date);
            coursActifTab[i][1] = text;
            DateFormat def = new SimpleDateFormat("HH:mm:ss");
            def.setTimeZone(TimeZone.getTimeZone("GMT"));
            String time_debut = def.format(debut);
            coursActifTab[i][2] = time_debut;
            String time_fin = def.format(fin);
            coursActifTab[i][3] = time_fin;
            coursActifTab[i][4] = etat;
            coursActifTab[i][5] = nom_type;
            coursActifTab[i][6] = nom_salle;
            coursActifTab[i][7] = nom_prof;
            coursActifTab[i][8] = nom_promo;
            coursActifTab[i][9] = nom_groupe;
            coursActifTab[i][10] = "modifier";
            coursActifTab[i][11] = "supprimer";
            i++;
        }
        String[] coursActifTitle = {"Matière", "Date", "Horaire début", "Horaire fin", "Etat", "Type", "Salle", "Professeur", "Promotion", "Groupe", "Modifier", "Supprimer"};

        MonModel modelMaj = new MonModel(coursActifTab, coursActifTitle);
        this.coursMaj = new JTable(modelMaj);
        coursMaj.setDefaultRenderer(JComponent.class, new ComposantTable());
        coursMaj.getColumn("Supprimer").setCellRenderer(new BoutonTableauSuppr());
        coursMaj.getColumn("Supprimer").setCellEditor(new ButtonTableauSuppr(new JCheckBox()));
        coursMaj.getColumn("Modifier").setCellRenderer(new BoutonTableau());
        coursMaj.getColumn("Modifier").setCellEditor(new ButtonTableauInt(new JCheckBox()));

        coursMaj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn col1 = coursMaj.getColumnModel().getColumn(0);
        col1.setPreferredWidth(100);
        TableColumn col2 = coursMaj.getColumnModel().getColumn(1);
        col2.setPreferredWidth(100);
        TableColumn col3 = coursMaj.getColumnModel().getColumn(2);
        col3.setPreferredWidth(100);
        TableColumn col4 = coursMaj.getColumnModel().getColumn(3);
        col4.setPreferredWidth(100);
        TableColumn col5 = coursMaj.getColumnModel().getColumn(4);
        col5.setPreferredWidth(100);
        TableColumn col6 = coursMaj.getColumnModel().getColumn(5);
        col6.setPreferredWidth(100);
        TableColumn col7 = coursMaj.getColumnModel().getColumn(6);
        col7.setPreferredWidth(100);
        TableColumn col8 = coursMaj.getColumnModel().getColumn(7);
        col8.setPreferredWidth(100);
        TableColumn col9 = coursMaj.getColumnModel().getColumn(8);
        col9.setPreferredWidth(100);
        TableColumn col10 = coursMaj.getColumnModel().getColumn(9);
        col10.setPreferredWidth(100);
        TableColumn col11 = coursMaj.getColumnModel().getColumn(10);
        col11.setPreferredWidth(100);
        TableColumn col12 = coursMaj.getColumnModel().getColumn(11);
        col12.setPreferredWidth(100);

        JScrollPane conteneurMaj = new JScrollPane(coursMaj);
        conteneurMaj.setPreferredSize(new Dimension(1300, 800));

        coursActif.add(conteneurMaj);

        JLabel LabelAjout = new JLabel("Ajouter un cours");
        LabelAjout.setFont(font1);
        JLabel labelMatiere = new JLabel("Matière :");
        labelMatiere.setFont(font2);
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
        JLabel labelProf = new JLabel("Professeur :");
        labelProf.setFont(font2);
        JLabel labelPromo = new JLabel("Promo :");
        labelPromo.setFont(font2);
        JLabel labelGroupe = new JLabel("Groupe :");
        labelGroupe.setFont(font2);

        JTextField TFMatiere = new JTextField("Mathematiques");
        JTextField TFDate = new JTextField("2020-06-04");
        JTextField TFHeureD = new JTextField("10:00:00");
        JTextField TFHeureF = new JTextField("11:30:00");
        JTextField TFEtat = new JTextField("validée");
        JTextField TFType = new JTextField("2");
        JTextField TFSalle = new JTextField("P330");
        JTextField TFProf = new JTextField("Segado");
        JTextField TFPromo = new JTextField("Ing2");
        JTextField TFGroupe = new JTextField("Gr1");

        AjouterCours.add(LabelAjout);
        AjouterCours.add(new JLabel(""));

        AjouterCours.add(labelMatiere);
        AjouterCours.add(TFMatiere);

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

        AjouterCours.add(labelProf);
        AjouterCours.add(TFProf);

        AjouterCours.add(labelPromo);
        AjouterCours.add(TFPromo);

        AjouterCours.add(labelGroupe);
        AjouterCours.add(TFGroupe);

        AjouterCours.add(new JLabel(""));
        AjouterCours.add(boutonAjout);

        FenetreMaj.setLayout(new GridLayout(3, 1));
        FenetreMaj.add(coursActif);
        FenetreMaj.add(ModifCours);
        FenetreMaj.add(AjouterCours);

    }


    public class ButtonTableauSuppr extends DefaultCellEditor {

        protected JButton button;
        private boolean isPushed;
        private ButtonListener boutList = new ButtonListener();

        public ButtonTableauSuppr(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(boutList);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
            boutList.setRow(row);
            boutList.setColumn(col);
            boutList.setTable(table);
            button.setText("Supprimer");
            return button;
        }

        class ButtonListener implements ActionListener {

            private int row, col;
            private JTable table;
            private int nbre = 0;

            public void setColumn(int col) {
                this.col = col;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public void setTable(JTable table) {
                this.table = table;
            }

            public void actionPerformed(ActionEvent event) {
                System.out.println("Je rentre dans le action listener de suppr");
                int NROW = this.row;
                Object groupeValue = coursMaj.getModel().getValueAt(this.row, this.col - 2);
                Object promoValue = coursMaj.getModel().getValueAt(this.row, this.col - 3);
                Object profValue = coursMaj.getModel().getValueAt(this.row, this.col - 4);
                Object salleValue = coursMaj.getModel().getValueAt(this.row, this.col - 5);
                Object typeValue = coursMaj.getModel().getValueAt(this.row, this.col - 6);
                Object etatValue = coursMaj.getModel().getValueAt(this.row, this.col - 7);
                Object HeureFValue = coursMaj.getModel().getValueAt(this.row, this.col - 8);
                Object HeureDValue = coursMaj.getModel().getValueAt(this.row, this.col - 9);
                Object DateValue = coursMaj.getModel().getValueAt(this.row, this.col - 10);
                Object MatValue = coursMaj.getModel().getValueAt(this.row, this.col - 11);
                DAO<Seance> classroom = new DAOSeance(conn);
                Statement stt;
                int idgrpe = 0;
                System.out.println(Time.valueOf((String) HeureDValue));
                try {
                    stt = conn.createStatement();
                    ResultSet res = stt.executeQuery("select * from seance WHERE Date='" + Date.valueOf((String) DateValue) + "' AND Debut='" + Time.valueOf((String) HeureDValue) + "'");
                    while (res.next()) {
                        idgrpe = res.getInt("ID");
                        Seance seance = classroom.find(idgrpe);
                        classroom.delete(seance);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("valeur de NROW :" + NROW);
                ((MonModel) table.getModel()).removeRow(NROW);
                resize();

            }
        }
    }

    public class ButtonTableauInt extends DefaultCellEditor {

        protected JButton button;
        private boolean isPushed;
        private ButtonListener boutList = new ButtonListener();

        public ButtonTableauInt(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(boutList);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
            boutList.setRow(row);
            boutList.setColumn(col);
            boutList.setTable(table);
            button.setText("modifier");
            return button;
        }

        class ButtonListener implements ActionListener {

            private int row, col;
            private JTable table;
            private int nbre = 0;

            public void setColumn(int col) {
                this.col = col;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public void setTable(JTable table) {
                this.table = table;
            }

            public void actionPerformed(ActionEvent event) {
                Object groupeValue = coursMaj.getModel().getValueAt(this.row, this.col - 1);
                Object promoValue = coursMaj.getModel().getValueAt(this.row, this.col - 2);
                Object profValue = coursMaj.getModel().getValueAt(this.row, this.col - 3);
                Object salleValue = coursMaj.getModel().getValueAt(this.row, this.col - 4);
                Object typeValue = coursMaj.getModel().getValueAt(this.row, this.col - 5);
                Object etatValue = coursMaj.getModel().getValueAt(this.row, this.col - 6);
                Object HeureFValue = coursMaj.getModel().getValueAt(this.row, this.col - 7);
                Object HeureDValue = coursMaj.getModel().getValueAt(this.row, this.col - 8);
                Object DateValue = coursMaj.getModel().getValueAt(this.row, this.col - 9);
                Object MatValue = coursMaj.getModel().getValueAt(this.row, this.col - 10);
                setModifPane(MatValue, DateValue, HeureDValue, HeureFValue, etatValue, typeValue, salleValue, profValue, promoValue, groupeValue);
            }
        }
    }

    public void setModifPane(Object matiere, Object Date, Object HeureD, Object HeureF, Object Etat, Object Type, Object Salle, Object Prof, Object Promo, Object Groupe) {
        ModifCours.removeAll();
        Font font2 = new Font("Arial", Font.BOLD, 18);

        JLabel labelMatiere = new JLabel("Matière :");
        labelMatiere.setFont(font2);
        JTextField TFMatiere = new JTextField((String) matiere);

        JLabel labelDate = new JLabel("Date :");
        labelDate.setFont(font2);
        JTextField TFDate = new JTextField((String) Date);

        JLabel labelHoraireD = new JLabel("Début du cours :");
        labelHoraireD.setFont(font2);
        JTextField TFHorD = new JTextField((String) HeureD);

        JLabel labelHoraireF = new JLabel("Fin du cours :");

        labelHoraireF.setFont(font2);
        JTextField TFHorF = new JTextField((String) HeureF);

        JLabel labelEtat = new JLabel("Etat :");
        labelEtat.setFont(font2);
        JTextField TFEtat = new JTextField((String) Etat);

        JLabel labelType = new JLabel("Type :");
        labelType.setFont(font2);
        JTextField TFType = new JTextField((String) Type);

        JLabel labelSalle = new JLabel("Salle :");
        labelSalle.setFont(font2);
        JTextField TFSalle = new JTextField((String) Salle);

        JLabel labelProf = new JLabel("Professeur :");
        labelProf.setFont(font2);
        JTextField TFProf = new JTextField((String) Prof);

        JLabel labelPromo = new JLabel("Promotion :");
        labelPromo.setFont(font2);
        JTextField TFPromo = new JTextField((String) Promo);

        JLabel labelGroupe = new JLabel("Groupe :");
        labelGroupe.setFont(font2);
        JTextField TFGroupe = new JTextField((String) Groupe);

        ModifCours.add(labelMatiere);
        ModifCours.add(TFMatiere);
        ModifCours.add(labelDate);
        ModifCours.add(TFDate);
        ModifCours.add(labelHoraireD);
        ModifCours.add(TFHorD);
        ModifCours.add(labelHoraireF);
        ModifCours.add(TFHorF);
        ModifCours.add(labelEtat);
        ModifCours.add(TFEtat);
        ModifCours.add(labelType);
        ModifCours.add(TFType);
        ModifCours.add(labelSalle);
        ModifCours.add(TFSalle);
        ModifCours.add(labelProf);
        ModifCours.add(TFProf);
        ModifCours.add(labelPromo);
        ModifCours.add(TFPromo);
        ModifCours.add(labelGroupe);
        ModifCours.add(TFGroupe);

        ModifCours.add(ValiderModif);

        ModifCours.setVisible(true);
        this.setSize(1499, 1000);
        this.setSize(1500, 1000);
    }

    public String insererSeance(Seance maSeance) {
        String Nrow = "";
        String Ncol = "";
        Date DateSeance = maSeance.getDate();
        Time heureSeance = maSeance.getDebut();
        Calendar calendarD = Calendar.getInstance();
        Calendar calendarH = Calendar.getInstance();
        calendarD.setTime(DateSeance);
        calendarH.setTime(heureSeance);
        int hours = calendarH.get(Calendar.HOUR_OF_DAY) - 1;
        int jour = calendarD.get(Calendar.DAY_OF_WEEK);
        // 1 si dimanche,2 si lundi ect...
        switch (jour) {
            case 1:
                break;
            case 2:
                Ncol = "0";
                break;
            case 3:
                Ncol = "1";
                break;
            case 4:
                Ncol = "2";
                break;
            case 5:
                Ncol = "3";
                break;
            case 6:
                Ncol = "4";
                break;
            case 7:
                Ncol = "5";
                break;
        }

        switch (hours) {
            case 8:
                Nrow = "0";
                break;
            case 10:
                Nrow = "1";
                break;
            case 12:
                Nrow = "2";
                break;
            case 13:
                Nrow = "3";
                break;
            case 15:
                Nrow = "4";
                break;
            case 17:
                Nrow = "5";
                break;
            case 19:
                Nrow = "6";
                break;
        }
        return Nrow + Ncol;

    }

    public void resize() {
        this.setSize(1499, 1000);
        this.setSize(1500, 1000);
    }
    private class Filtre implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String salle = sallefiltre.getSelectedItem().toString();
            salle = salle.replaceAll("[\n]+", "");
            String mat = cours.getSelectedItem().toString();
            mat = mat.replaceAll("[\n]+", "");

            String prof = professeur.getSelectedItem().toString();
            prof = prof.replaceAll("[\n]+", "");
            resize();
            FenetreRecap.removeAll();
            try {
                initRecap(salle, mat, prof);
                defineRecap();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public void defineReporting() throws SQLException, ClassNotFoundException
     {
         JPanel Ligne1 = new JPanel();
         JPanel Ligne2 = new JPanel();
         JPanel Ligne3 = new JPanel();
         JPanel Ligne4 = new JPanel();
         JPanel LigneTitre = new JPanel();
         JPanel LigneVide = new JPanel();
         
         Ligne1.setLayout(new BoxLayout(Ligne1, BoxLayout.LINE_AXIS));
         Ligne2.setLayout(new BoxLayout(Ligne2, BoxLayout.LINE_AXIS));
         Ligne3.setLayout(new BoxLayout(Ligne3, BoxLayout.LINE_AXIS));
         Ligne4.setLayout(new BoxLayout(Ligne4, BoxLayout.LINE_AXIS));
         
        JPanel FinalContent = new JPanel();
         FinalContent.setLayout(new BoxLayout(FinalContent,BoxLayout.PAGE_AXIS));
         
         //Premier graph : Ici il faut récupérer le nombre d'heure de chaque matière, le nombre d'heure de chaque matière qui n'ont pas encore été faites (ressemble aux barres du bas), et diviser le 2eme par le 1er
         String nbcoursmaths;
         double cpt1=0;
         double cpt2=0,cpt3=0,cpt4=0;
         int idgrp=student.getGroupe();
         int ids=0;
         System.out.println(idgrp);
          Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=1 and ID=" +ids);
        for (int i = 0; i < liste.size(); i++) {
           nbcoursmaths=liste.get(i);
           
        }
        for(int i=0;i<liste.size();i++)
        {
            cpt1=cpt1+1;
        }
       }
       rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=2 and ID=" +ids);
        for (int i = 0; i < liste.size(); i++) {
           nbcoursmaths=liste.get(i);
           
        }
        for(int i=0;i<liste.size();i++)
        {
            cpt2=cpt2+1;
        }
       }
       rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=3 and ID=" +ids);
        for (int i = 0; i < liste.size(); i++) {
           nbcoursmaths=liste.get(i);
           
        }
        for(int i=0;i<liste.size();i++)
        {
            cpt3=cpt3+1;
        }
       }
       rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=4 and ID=" +ids);
        for (int i = 0; i < liste.size(); i++) {
           nbcoursmaths=liste.get(i);
           
        }
        for(int i=0;i<liste.size();i++)
        {
            cpt4=cpt4+1;
        }
       }
        double somme= cpt1+cpt2+cpt3+cpt4;
         double cp1=(cpt1/somme)*100;
         double cp2=(cpt2/somme)*100;
         double cp3=(cpt3/somme)*100;
         double cp4=(cpt4/somme)*100;
      
         DefaultPieDataset HeureRestantes = new DefaultPieDataset();
         HeureRestantes.setValue("Mathématiques", cp1);
         HeureRestantes.setValue("Electronique", cp2);
         HeureRestantes.setValue("Physique", cp3);
         HeureRestantes.setValue("Probabilités", cp4);
         JFreeChart pieChart = ChartFactory.createPieChart("Heures restantes par matière",HeureRestantes,true,true,true);
         ChartPanel cPanel = new ChartPanel (pieChart);
         
         //_______________________Deuxième graph : ici c'est plus facile faut récupérer le nom de chaque salle et sa capacité associée
         DefaultCategoryDataset placeSalle = new DefaultCategoryDataset();
         placeSalle.addValue(55, "Quantité", "P330");
         placeSalle.addValue(30, "Quantité", "P445");
         placeSalle.addValue(200, "Quantité", "E110");
         placeSalle.addValue(35, "Quantité", "E20");
         placeSalle.addValue(60, "Quantité", "D230");
         placeSalle.addValue(20, "Quantité", "D356");
         placeSalle.addValue(150, "Quantité", "B245");
         placeSalle.addValue(100, "Quantité", "A123");
         placeSalle.addValue(40, "Quantité", "P190");
         placeSalle.addValue(230, "Quantité", "C100");
         JFreeChart BarChart = ChartFactory.createBarChart("Nombre de place par salle","Nombre de place","Salle",placeSalle,PlotOrientation.VERTICAL,true,true,false);
         ChartPanel BPanel = new ChartPanel (BarChart);
         
        
       //ici faut compter sur la semaine le nombre d'heure réalisé dans la semaine en tout pour un éleves, en gros faut faire une boucle avec i qui augmente de 1 pour chaque heure de l'éleve chaque jour et ensuite on multiplie par 1.5 pour les heures 
         int cptt=0;
         String oned="";
         int cptt2=0,cptt3=0,cptt4=0,cptt5=0,cptt6=0;
          rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
        liste = connliste.Affich("Select ID from seance where Date='2020-06-01' and ID=" + ids);
        for (int i = 0; i < liste.size(); i++) {
           cptt=cptt+1;
         
        }
       }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
        liste = connliste.Affich("Select ID from seance where Date='2020-06-02' and ID=" + ids);
        for (int i = 0; i < liste.size(); i++) {
           cptt2=cptt2+1;
         
        }
       }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
        liste = connliste.Affich("Select ID from seance where Date='2020-06-03' and ID=" + ids);
        for (int i = 0; i < liste.size(); i++) {
           cptt3=cptt3+1;
         
        }
       }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
        liste = connliste.Affich("Select ID from seance where Date='2020-06-04' and ID=" + ids);
        for (int i = 0; i < liste.size(); i++) {
           cptt4=cptt4+1;
         
        }
       }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
        liste = connliste.Affich("Select ID from seance where Date='2020-06-05' and ID=" + ids);
        for (int i = 0; i < liste.size(); i++) {
           cptt5=cptt5+1;
         
        }
       }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
       while(rs.next())
       {
           ids=rs.getInt("id_seance");
        liste = connliste.Affich("Select ID from seance where Date='2020-06-06' and ID=" + ids);
        for (int i = 0; i < liste.size(); i++) {
           cptt6=cptt6+1;
         
        }
       }
       
        System.out.println(cptt);
       DefaultCategoryDataset heureparSemaine = new DefaultCategoryDataset();
         heureparSemaine.addValue(cptt, "Heure", "Lundi");
         heureparSemaine.addValue(cptt+cptt2, "Heure", "Mardi");
         heureparSemaine.addValue(cptt+cptt2+cptt3, "Heure", "Mercredi");
         heureparSemaine.addValue(cptt+cptt2+cptt3+cptt4, "Heure", "Jeudi");
         heureparSemaine.addValue(cptt+cptt2+cptt3+cptt4+cptt5, "Heure", "Vendredi");
         heureparSemaine.addValue(cptt+cptt2+cptt3+cptt4+cptt5+cptt6, "Heure", "Samedi");
         JFreeChart lineChart = ChartFactory.createLineChart("Nombre d'heure de l'étudiant au cours de la semaine","Jours","compteur d'heure",heureparSemaine,PlotOrientation.VERTICAL,false,true,false);
         ChartPanel LPanel = new ChartPanel (lineChart);
         
         // la c'est pareil que le 1er mais dans l'autre sens vu que c'est les heures deja faites donc pas de nouvelles données a récuperer
         JProgressBar BarMaths = new JProgressBar();
         BarMaths.setValue(70);
         BarMaths.setMaximumSize(new Dimension(500,500));
         JProgressBar BarElec = new JProgressBar();
         BarElec.setValue(70);
         BarElec.setMaximumSize(new Dimension(500,500));
         JProgressBar BarPhy = new JProgressBar();
         BarPhy.setValue(70);
         BarPhy.setMaximumSize(new Dimension(500,500));
         JProgressBar BarProba = new JProgressBar();
         BarProba.setValue(70);
         BarProba.setMaximumSize(new Dimension(500,500));
         
         
          Font fonte = new Font(" TimesRoman ",Font.BOLD,24);
         Font fonte2 = new Font(" TimesRoman ",Font.BOLD,42);
         JLabel labelMaths = new JLabel("Mathématiques :");
         labelMaths.setFont(fonte);
         JLabel labelProba = new JLabel("     Electronique :");
         labelProba.setFont(fonte);
         JLabel labelPhy = new JLabel("     Physique :");
         labelPhy.setFont(fonte);
         JLabel labelElec = new JLabel("Probabilités :");
         labelElec.setFont(fonte);
         JLabel labelTitre = new JLabel("Avancement du cours :");
         labelTitre.setFont(fonte2);
         JLabel labelVide = new JLabel("                                                                                                                                              ");
         labelVide.setFont(fonte2);
         
        
         Ligne1.add(cPanel);
         Ligne2.add(BPanel);
         Ligne2.add(LPanel);
         LigneTitre.add(labelTitre);
         Ligne3.add(labelMaths);
         Ligne3.add(BarMaths);
         Ligne3.add(labelProba);
         Ligne3.add(BarProba);
         LigneVide.add(labelVide);
         Ligne4.add(labelElec);
         Ligne4.add(BarElec);
         Ligne4.add(labelPhy);
         Ligne4.add(BarPhy);
         
         
         FinalContent.add(Ligne1);
         FinalContent.add(Ligne2);
         FinalContent.add(LigneTitre);
         FinalContent.add(Ligne3);
         FinalContent.add(LigneVide);
         FinalContent.add(Ligne4);
         BPanel.setBounds(0, 1000,500, 500);
         FinalContent.setBounds(0, 0,1500, 800);
         FenetreReporting.add(FinalContent);
     }

    
    private class Filtre implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
          String cours1=cours.getSelectedItem().toString();
          cours1 = cours1.replaceAll("[\r\n]+", ""); //
           String IDcours="";
           String seance1="";
           String test="";
           String prof1=professeur.getSelectedItem().toString();
           String salle1=sallefiltre.getSelectedItem().toString();
       
        System.out.println("'"+cours1+"'");
       Statement stm;
            try {
                ResultSet res;
                stm = conn.createStatement();
                res = stm.executeQuery("select ID from cours where Nom = '"+cours1+"'");
                while (res.next()) {
                    IDcours=res.getString("ID");
         
            }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Recherche des profs
        
         try {
                liste2 = connliste.Affich("Select * from seance where Id_cours=" + IDcours);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        for (int i = 0; i < liste2.size(); i++) {
          seance1=liste2.get(i);
          System.out.println(liste2.get(i)+ "ici2");
        }
       }
    }


    
    public void hideCal()
    {
        this.boutonCal.setVisible(false);
    }
    public void hideRec()
    {
        this.boutonRec.setVisible(false);
    }
    public void hideMaj()
    {
        this.boutonMaj.setVisible(false);
    }
    public void hideRep()
    {
        this.boutonRep.setVisible(false);
    }
    public void showCal()
    {
        this.boutonCal.setVisible(true);
    }
    public void showRec()
    {
        this.boutonRec.setVisible(true);
    }
    public void showMaj()
    {
        this.boutonMaj.setVisible(true);
    }
    public void showRep()
    {
        this.boutonRep.setVisible(true);
    }
    public void setContentMaj()
    {
        this.setContentPane(FenetreMaj);
    }
    public void setContentRec()
    {
        this.setContentPane(FenetreRecap);
    }
}
