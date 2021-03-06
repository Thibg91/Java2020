/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.Connexion_sql;
import controler.DAO;
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
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;
import modele.Enseignant;
import modele.Etudiant;
import modele.Seance;
import modele.Utilisateur;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
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
    private BoutonInt bouton1 = new BoutonInt("Semaine 23");
    private BoutonInt bouton2 = new BoutonInt("Semaine 24");
    private BoutonInt bouton3 = new BoutonInt("Semaine 25");
    private BoutonInt bouton4 = new BoutonInt("Semaine 26");
    private BoutonInt bouton5 = new BoutonInt("Filtrer");
    private BoutonInt filtre_cal = new BoutonInt("Filtrer");
    private JMenuBar Navigation = new JMenuBar();
    private BoutonInt boutonCal = new BoutonInt("Emploi du temps");
    private BoutonInt boutonRec = new BoutonInt("Recap");
    private BoutonInt boutonMaj = new BoutonInt("Mise à jour");
    private BoutonInt boutonRep = new BoutonInt("Reporting");
    private BoutonInt boutonAjout = new BoutonInt("Ajouter");
    private BoutonInt ValiderModif = new BoutonInt("Valider");
    private JComboBox sallefiltre = new JComboBox();
    private JComboBox sallesfiltre = new JComboBox();
    private JComboBox professeur = new JComboBox();
    private JComboBox professeurs = new JComboBox();
    private JComboBox cours = new JComboBox();
    private JComboBox Cours = new JComboBox();
    private JComboBox debut_heure = new JComboBox();
    private JComboBox debut_fin = new JComboBox();
    private JComboBox jour = new JComboBox();
    private JComboBox etat_cours = new JComboBox();
    private JComboBox promo = new JComboBox();
    private JComboBox type_cours = new JComboBox();
    private JComboBox groupes = new JComboBox();
    private JComboBox promo_groupe = new JComboBox();
    private JPanel FenetreCalendrier = new JPanel();
    private JPanel FenetreRecap = new JPanel();
    private JPanel FenetreMaj = new JPanel();
    private JPanel FenetreReporting = new JPanel();
    private Etudiant student = null;
    private Enseignant prof = null;
    private Utilisateur user;
    private JSpinner spinner_date;
    private JSpinner spinner_fin;
    private JSpinner spinner_time;
    private JLabel promoLabel = new JLabel("Groupe: ");
    private int week;

    private JPanel ModifCours = new JPanel();
    private Object[] objetAjout = null;
    private ArrayList<String> liste;
    private ArrayList<String> liste2;
    private ArrayList<String> liste3;

    //constructeur de la classe

    /**
     *
     * @param conn
     * @param user
     * @param week
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Fenetre(Connection conn, Utilisateur user, int week) throws ClassNotFoundException, SQLException {
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
        FenetreCalendrier.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        FenetreRecap .setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        FenetreMaj.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        FenetreReporting.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));

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

        //Login monLogin = new Login();
        //monLogin.setVisible(true);
        defineMaj();
        //defineReporting();
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
        filtre_cal.addActionListener(this);
        this.user = user;
        if (user.getDroit() == 4) {
            this.student = (Etudiant) user;
            afficheCalendrier(student, week, "", "");
            defineReporting();
        }

        if (user.getDroit() == 3) {
            this.prof = (Enseignant) user;
            afficheCalendrierProf(this.prof, week, "", "");
        }
        if (user.getDroit() != 1) {
            this.initRecap("", "", "", "", "");
            defineRecap();
        }
        this.setContentPane(FenetreCalendrier);
        //Cacher la fenetre ou pas : bool 
        this.setVisible(true);

    }

    /**
     *
     * @param prof
     * @param week
     * @param nom_matiere
     * @param nom_Salle
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void afficheCalendrierProf(Enseignant prof, int week, String nom_matiere, String nom_Salle) throws SQLException, ClassNotFoundException {
        this.week = week;
        JPanel firstColumnPane = new JPanel();
        JTextPane firstColumn = new JTextPane();
        firstColumn.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
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
        ArrayList<String> listes = null;
        //test de case avec des données
        this.initCalendrier();

        //Partie filtre : c'est la partie sur la droite de la page qui va contenir tout les filtres utiles sur notre tableau
        JPanel rightLayout = new JPanel();
        rightLayout.setLayout(new GridLayout(16, 1));
        JPanel coursPanel = new JPanel();
        //coursPanel.setBackground(Color.lightGray);
        Statement sstm = conn.createStatement();
        ResultSet rres = sstm.executeQuery("Select Nom from cours");
        Cours.removeAllItems();
        Cours.addItem("");
        while (rres.next()) {
            Cours.addItem(rres.getString("Nom"));
        }
        JLabel coursLabel = new JLabel("Cours : ");
        coursPanel.add(coursLabel);
        coursPanel.add(Cours);

        sallesfiltre.removeAllItems();
        sallesfiltre.addItem("");
        rres = sstm.executeQuery("Select Nom from salle");
        while (rres.next()) {
            sallesfiltre.addItem(rres.getString("Nom"));
        }
        JPanel sallePanel = new JPanel();
        JLabel salleLabel = new JLabel("Salle: ");
        coursPanel.add(salleLabel);
        coursPanel.add(sallesfiltre);

        rightLayout.add(coursPanel);
        coursPanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        sallePanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        rightLayout.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));


        
        rightLayout.add(sallePanel);
        JPanel tempanel = new JPanel();
        tempanel.setPreferredSize(new Dimension(50,40));
        tempanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        filtre_cal.setPreferredSize(new Dimension(100,40));
        tempanel.add(filtre_cal);
        rightLayout.add(tempanel);


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
        // Recherche des seances selon le professeur
        rs = stt.executeQuery("Select id_seance from seance_enseignant Where id_enseignant=" + prof.getID() + " And id_seance in (Select ID from seance where Semaine=" + week + ")");
        while (rs.next())//Ici on va créer les cases de cours
        {
            nomprof = "";
            nom_salle = "";
            nom_cours = "";
            idseance = rs.getInt("id_seance");
            Statement stm = conn.createStatement();
            // Recherche des profs
            boolean okMatiere = false;
            boolean okSalle = false;
            ArrayList<Integer> arraylistSalle = new ArrayList<Integer>();
            ArrayList<Integer> arraylistMatiere = new ArrayList<Integer>();
            if (!nom_Salle.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where ID in (select id_seance from seance_salle Where id_salle in (select ID from salle where Nom='" + nom_Salle + "'))");
                while (ress.next()) {
                    arraylistSalle.add(ress.getInt("ID"));
                }
                if (arraylistSalle.contains(new Integer(idseance))) {
                    okSalle = true;
                }
            }
            if (nom_Salle.equals("")) {
                okSalle = true;
            }
            if (!nom_matiere.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where Id_cours in (Select ID from cours Where Nom='" + nom_matiere + "')");
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
            if (okMatiere == true && okSalle == true) {
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
                if (nom_cours.equals("Electronique")) {
                    contenue.setBackground(Color.YELLOW);
                }
                if (nom_cours.equals("Physique")) {
                    contenue.setBackground(Color.RED);
                }
                if (nom_cours.equals("Probabilités")) {
                    contenue.setBackground(Color.BLUE);
                }
                contenue.setEditable(false);
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
            if (nom_cours.equals("Electronique")) {
                contenue.setBackground(Color.YELLOW);
            }
            if (nom_cours.equals("Physique")) {
                contenue.setBackground(Color.RED);
            }
            if (nom_cours.equals("Probabilités")) {
                contenue.setBackground(Color.CYAN);
            }
            contenue.setEditable(false);

        }
        JScrollPane conteneurCal = new JScrollPane(monTableau);
        conteneurCal.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        monTableau.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));

        //Login monLogin = new Login();
        // monLogin.setVisible(true);
        //partie barre de navigation
        this.Navigation.add(boutonCal);
        this.Navigation.add(boutonRec);
        this.Navigation.add(boutonMaj);
        this.Navigation.add(boutonRep);
        this.setJMenuBar(Navigation);
        
        changePage.setBackground(new Color((float)0.3,(float)0.3,(float)0.3));

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
    }

    /**
     *
     * @param student
     * @param week
     * @param nom_matiere
     * @param nom_prof
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void afficheCalendrier(Etudiant student, int week, String nom_matiere, String nom_prof) throws SQLException, ClassNotFoundException {
        this.week = week;
        JPanel firstColumnPane = new JPanel();
        JTextPane firstColumn = new JTextPane();
        firstColumn.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        firstColumn.setEditable(false);

        firstColumn.setText("\r\n \r\n \r\n \r\n" + "8h30-10h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "10h15-11h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "12h00-13h30" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "13h45-15h15" + "\r\n \r\n \r\n \r\n \r\n \r\n \r\n" + "15h30-17h00" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "17h15-18h45" + "\r\n \r\n \r\n \r\n \r\n \r\n" + "19h00-20h30");

        firstColumn.setBounds(0, 0, 80, 1000);
        firstColumnPane.setLayout(null);
        firstColumnPane.add(firstColumn);
        firstColumnPane.setPreferredSize(new Dimension(80, 1000));

        // Ici on a les boutons de bas de page qui vont permettre de passer d'une semaine a une autre
        JPanel changePage = new JPanel();
        changePage.setBackground(new Color((float)0.3,(float)0.3,(float)0.3));
        changePage.add(bouton1);
        changePage.add(bouton2);
        changePage.add(bouton3);
        changePage.add(bouton4);

        //test de case avec des données
        this.initCalendrier();

        //Partie filtre : c'est la partie sur la droite de la page qui va contenir tout les filtres utiles sur notre tableau
        JPanel rightLayout = new JPanel();
        rightLayout.setLayout(new GridLayout(16, 1));
        rightLayout.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        JPanel coursPanel = new JPanel();
        //coursPanel.setBackground(Color.lightGray);
        Cours.removeAllItems();
        liste = connliste.Affich("Select Nom from cours ");
        Cours.addItem("");
        for (int i = 0; i < 1; i++) {
            Cours.addItem(liste.get(i));
            Cours.addItem(liste.get(i + 1));
            Cours.addItem(liste.get(i + 2));
            Cours.addItem(liste.get(i + 3));
        }
        JLabel coursLabel = new JLabel("Cours : ");
        coursPanel.add(coursLabel);
        coursPanel.add(Cours);
        //juste un test 
        liste = connliste.Affich("Select Nom from utilisateurs where Droit = 3");
        professeurs.removeAllItems();
        professeurs.addItem("");
        for (int i = 0; i < liste.size(); i++) {
            professeurs.addItem(liste.get(i));
        }

        JLabel profLabel = new JLabel("Professeur : ");
        JPanel profPanel = new JPanel();
        profPanel.add(profLabel);
        profPanel.add(professeurs);

        JLabel salleLabel = new JLabel("Salle: ");

//juste un test 
        JComboBox sallefiltre = new JComboBox();

        liste = connliste.Affich("Select Nom from salle ");
        for (int i = 0; i < liste.size(); i++) {
            sallefiltre.addItem(liste.get(i));

        }
        rightLayout.add(coursPanel);
        rightLayout.add(profPanel);

        coursPanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        profPanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
  
        JPanel tempanel = new JPanel();
        tempanel.setPreferredSize(new Dimension(50,40));
        tempanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        filtre_cal.setPreferredSize(new Dimension(100,40));
        tempanel.add(filtre_cal);
        rightLayout.add(tempanel);
        

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
        // Recherche des seances selon le groupe de l'etudiant
        rs = stt.executeQuery("Select id_seance from seance_groupe Where id_groupe=" + idgr + " And id_seance in (Select ID from seance where Semaine=" + week + ")");
        while (rs.next())//Ici on va créer les cases de cours
        {
            nomprof = "";
            nom_salle = "";
            nom_cours = "";
            idseance = rs.getInt("id_seance");
            Statement stm = conn.createStatement();
            boolean okMatiere = false;
            boolean okProf = false;
            ArrayList<Integer> arraylistProf = new ArrayList<Integer>();
            ArrayList<Integer> arraylistMatiere = new ArrayList<Integer>();
            if (!nom_prof.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where ID in (select id_seance from seance_enseignant Where id_enseignant in (select ID from utilisateurs where Nom='" + nom_prof + "'))");
                while (ress.next()) {
                    arraylistProf.add(ress.getInt("ID"));
                }
                if (arraylistProf.contains(new Integer(idseance))) {
                    okProf = true;
                }
            }
            if (nom_prof.equals("")) {
                okProf = true;
            }
            if (!nom_matiere.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("Select * from seance where Id_cours in (Select ID from cours Where Nom='" + nom_matiere + "')");
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
            if (okMatiere == true && okProf == true) {
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
                if (nom_cours.equals("Probabilités")) {
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
        }
        JScrollPane conteneurCal = new JScrollPane(monTableau);
        conteneurCal.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        monTableau.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));

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

        this.setContentPane(FenetreCalendrier);
        //Cacher la fenetre ou pas : bool 
        this.setVisible(true);
    }

    // nécessite des modifs mais permet d' 

    /**
     *
     * @param arg0
     */
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == bouton1) {
            resize();
            FenetreCalendrier.removeAll();
            this.week = 23;
            try {
                if (user.getDroit() == 4) {
                    afficheCalendrier(this.student, 23, "", "");
                }
                if (user.getDroit() == 3) {
                    afficheCalendrierProf(this.prof, 23, "", "");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arg0.getSource() == bouton2) {
            resize();
            FenetreCalendrier.removeAll();
            this.week = 24;
            try {
                if (user.getDroit() == 4) {
                    afficheCalendrier(this.student, 24, "", "");
                }
                if (user.getDroit() == 3) {
                    afficheCalendrierProf(this.prof, 24, "", "");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arg0.getSource() == bouton3) {
            resize();
            FenetreCalendrier.removeAll();
            this.week = 25;
            try {
                if (user.getDroit() == 4) {
                    afficheCalendrier(this.student, 25, "", "");
                }
                if (user.getDroit() == 3) {
                    afficheCalendrierProf(this.prof, 25, "", "");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arg0.getSource() == bouton4) {
            resize();
            FenetreCalendrier.removeAll();
            this.week = 26;
            try {
                if (user.getDroit() == 4) {
                    afficheCalendrier(this.student, 26, "", "");
                }
                if (user.getDroit() == 3) {
                    afficheCalendrierProf(this.prof, 26, "", "");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arg0.getSource() == filtre_cal) {
            String prof = null;
            String mat = Cours.getSelectedItem().toString();
            mat = mat.replaceAll("[\n]+", "");
            if (user.getDroit() == 4) {
                prof = professeurs.getSelectedItem().toString();
                prof = prof.replaceAll("[\n]+", "");
            }
            if (user.getDroit() == 3) {
                prof = sallesfiltre.getSelectedItem().toString();
                prof = prof.replaceAll("[\n]+", "");
            }
            resize();
            FenetreCalendrier.removeAll();
            try {
                if (user.getDroit() == 4) {
                    afficheCalendrier(this.student, this.week, mat, prof);
                }
                if (user.getDroit() == 3) {
                    afficheCalendrierProf(this.prof, this.week, mat, prof);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (arg0.getSource() == boutonCal) {
            this.setContentPane(FenetreCalendrier);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        if (arg0.getSource() == boutonRec) {
            this.setContentPane(FenetreRecap);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        if (arg0.getSource() == boutonMaj) {
            this.setContentPane(FenetreMaj);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        if (arg0.getSource() == boutonRep) {
            this.setContentPane(FenetreReporting);
            this.setSize(1499, 1000);
            this.setSize(1500, 1000);
        }
        /// Ajouter un cours
        if (arg0.getSource() == boutonAjout) {
            JPanel tempPanel = (JPanel) boutonAjout.getParent();
            String matiere = cours.getSelectedItem().toString();
            matiere = matiere.replaceAll("[\n]+", "");
            String etat = etat_cours.getSelectedItem().toString();
            etat = etat.replaceAll("[\n]+", "");
            String type = type_cours.getSelectedItem().toString();
            type = type.replaceAll("[\n]+", "");
            String salle = sallefiltre.getSelectedItem().toString();
            salle = salle.replaceAll("[\n]+", "");
            String prof = professeur.getSelectedItem().toString();
            prof = prof.replaceAll("[\n]+", "");
            String prom = promo.getSelectedItem().toString();
            prom = prom.replaceAll("[\n]+", "");
            String groupe = groupes.getSelectedItem().toString();
            groupe = groupe.replaceAll("[\n]+", "");
            String date = new SimpleDateFormat("yyyy-MM-dd").format(spinner_date.getValue());
            System.out.println(date);
            String debut = new SimpleDateFormat("HH:mm:ss").format(spinner_time.getValue());
            System.out.println(debut);
            String fin = new SimpleDateFormat("HH:mm:ss").format(spinner_fin.getValue());
            System.out.println(fin);

            Date jour = Date.valueOf(date);
            Calendar cal = Calendar.getInstance();
            //La première semaine de l'année est celle contenant au moins 4 jours
            cal.setMinimalDaysInFirstWeek(4);
            cal.setTime(jour);
            int week = cal.get(Calendar.WEEK_OF_YEAR);
            //int types = Integer.parseInt((String) type.getText());
            String mat = matiere;
            String room = salle;
            String groupes = groupe;
            String promos = prom;
            Time Debut = Time.valueOf(debut);
            Time Fin = Time.valueOf(fin);

            Statement stmt = null;
            int id_salle = 0;
            int id_groupe = 0;
            int id_promo = 0;
            int id_prof = 0;
            int id_matiere = 0;
            int id_type = 0;
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
                ResultSet rs = stmt.executeQuery("Select * from type_cours WHERE Nom='" + type + "'");
                while (rs.next()) {
                    id_type = rs.getInt("ID");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            Seance seance = new Seance(0, week, jour, Debut, Fin, etat, id_matiere, id_type);
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
                ResultSet rs = stmt.executeQuery("Select * from utilisateurs WHERE Nom='" + prof + "'");
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
            resize();
            FenetreMaj.removeAll();
            try {
                defineMaj();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            JTextField id = (JTextField) tempPanel.getComponent(21);
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
            int id_seance = Integer.parseInt((String) id.getText());
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

            Seance seance = new Seance(id_seance, week, jour, Debut, Fin, (String) etat.getText(), id_matiere, types);
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
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

//initialise un calendrier

    /**
     *
     */
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

    /**
     *
     * @param salle2
     * @param Mat
     * @param prof
     * @param pro
     * @param grp
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void initRecap(String salle2, String Mat, String prof, String pro, String grp) throws SQLException, ClassNotFoundException {
        int idgrp = 0;
        if (user.getDroit() == 4) {
            idgrp = student.getGroupe();
        }
        int idseance = 0;
        Seance amphi = null;
        int id = 0, semaine = 0, id_cours = 0, id_type = 0, id_promo = 0, id_groupe = 0, id_salle = 0, id_prof = 0, i = 0;
        Time debut = null, fin = null;
        Date date = null;
        String etat = null, nom_type = null, nom_matiere = null, nom_promo = null, nom_groupe = null, nom_salle = null, nom_prof = null;
        Object[][] coursRecap = new Object[100][10];
        Statement stmts = conn.createStatement();
        ResultSet r = null;
        if (user.getDroit() == 2) {
            r = stmts.executeQuery("select Distinct(id_seance) from seance_groupe");
        }
        if (user.getDroit() == 4) {
            r = stmts.executeQuery("select * from seance_groupe where id_groupe= " + idgrp);
        }
        if (user.getDroit() == 3) {
            r = stmts.executeQuery("select * from seance_enseignant where id_enseignant= " + this.prof.getID());
        }
        while (r.next()) {
            boolean okProf = false;
            boolean okMatiere = false;
            boolean okSalle = false;
            boolean okPromo = false;
            ArrayList<Integer> arraylistProf = new ArrayList<Integer>();
            ArrayList<Integer> arraylistMatiere = new ArrayList<Integer>();
            ArrayList<Integer> arraylistSalle = new ArrayList<Integer>();
            ArrayList<Integer> arraylistPromo = new ArrayList<Integer>();
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
            if (prof.equals("")) {
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
            if (!pro.equals("")) {
                Statement st = conn.createStatement();
                ResultSet ress = st.executeQuery("select Distinct(id_seance) from seance_groupe Where id_groupe in (Select ID from groupe where Nom='" + grp + "' and ID_promotion in (Select ID from promotion where Nom='" + pro + "'))");
                while (ress.next()) {
                    arraylistPromo.add(ress.getInt("id_seance"));
                }
                if (arraylistPromo.contains(new Integer(idseance))) {
                    okPromo = true;
                }
            } else {
                okPromo = true;
            }
            if (okProf == true && okMatiere == true && okSalle == true && okPromo == true) {
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

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void defineRecap() throws ClassNotFoundException, SQLException {

        JPanel FiltreRecap = new JPanel();
        ArrayList<String> liste;
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

        if (user.getDroit() == 2) {
            liste = connliste.Affich("Select Nom from promotion");
            promo_groupe.removeAllItems();
            promo_groupe.addItem("");
            for (int i = 0; i < liste.size(); i++) {
                promo_groupe.addItem(liste.get(i) + " - Gr1");
                promo_groupe.addItem(liste.get(i) + " - Gr2");
            }
            FiltreRecap.add(promoLabel);
            FiltreRecap.add(promo_groupe);
        }

        FiltreRecap.add(salleLabel);
        salleLabel.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        FiltreRecap.add(sallefiltre);
        FiltreRecap.add(coursLabel);
        coursLabel.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        FiltreRecap.add(cours);
        FiltreRecap.add(profLabel);
        profLabel.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        FiltreRecap.add(professeur);
        FiltreRecap.add(bouton5);
        FiltreRecap.setBackground(new Color((float)0.3,(float)0.3,(float)0.3));
        monRecap.setRowHeight(40);
        monRecap.setPreferredSize(new Dimension(1200,600));
        monRecap.setEnabled(false);
        JScrollPane conteneurRec = new JScrollPane(monRecap);
        conteneurRec.setPreferredSize(new Dimension(1450,600));
        JPanel contentScroll = new JPanel();
        contentScroll.add(conteneurRec);
        contentScroll.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        

        FenetreRecap.setLayout(new BorderLayout());
        //Contenu du haut
        FenetreRecap.add(FiltreRecap, BorderLayout.NORTH);
        //Contenu du centre
        FenetreRecap.add(contentScroll, BorderLayout.CENTER);
    }

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void defineMaj() throws SQLException, ClassNotFoundException {
        Font font1 = new Font("Arial", Font.BOLD, 32);
        Font font2 = new Font("Arial", Font.BOLD, 18);
        JPanel filtreCours = new JPanel();
        filtreCours.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        JPanel coursActif = new JPanel();
        coursActif.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        ModifCours.setVisible(false);
        ModifCours.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        ModifCours.setLayout(new GridLayout(4, 6));
        JPanel AjouterCours = new JPanel();
        AjouterCours.setBackground(new Color((float)0.3,(float)0.3,(float)0.3));
        AjouterCours.setLayout(new GridLayout(6, 4));

        Seance amphi = null;
        int id = 0, semaine = 0, id_cours = 0, id_type = 0, id_promo = 0, id_groupe = 0, id_salle = 0, id_prof = 0;
        Time debut = null, fin = null;
        Date date = null;
        String etat = null, nom_type = null, nom_matiere = null, nom_promo = null, nom_groupe = null, nom_salle = null, nom_prof = null;
        Statement st = conn.createStatement();
        ResultSet resss = st.executeQuery("select count(*) as total from seance");
        resss.next();
        Object[][] coursActifTab = new Object[resss.getInt("total")][13];;
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
            coursActifTab[i][0] = id;
            coursActifTab[i][1] = nom_matiere;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String text = df.format(date);
            coursActifTab[i][2] = text;
            DateFormat def = new SimpleDateFormat("HH:mm:ss");
            def.setTimeZone(TimeZone.getTimeZone("GMT"));
            String time_debut = def.format(debut);
            coursActifTab[i][3] = time_debut;
            String time_fin = def.format(fin);
            coursActifTab[i][4] = time_fin;
            coursActifTab[i][5] = etat;
            coursActifTab[i][6] = nom_type;
            coursActifTab[i][7] = nom_salle;
            coursActifTab[i][8] = nom_prof;
            coursActifTab[i][9] = nom_promo;
            coursActifTab[i][10] = nom_groupe;
            coursActifTab[i][11] = "modifier";
            coursActifTab[i][12] = "supprimer";
            i++;
        }
        String[] coursActifTitle = {"Id", "Matière", "Date", "Horaire début", "Horaire fin", "Etat", "Type", "Salle", "Professeur", "Promotion", "Groupe", "Modifier", "Supprimer"};

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
        TableColumn col13 = coursMaj.getColumnModel().getColumn(12);
        col12.setPreferredWidth(100);

        JScrollPane conteneurMaj = new JScrollPane(coursMaj);
        conteneurMaj.setPreferredSize(new Dimension(1300, 300));

        coursActif.add(conteneurMaj);

        JLabel LabelAjout = new JLabel("Ajouter un cours");
        LabelAjout.setFont(font1);
        LabelAjout.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelMatiere = new JLabel("Matière :");
        labelMatiere.setFont(font2);
        labelMatiere.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelDate = new JLabel("Date :");
        labelDate.setFont(font2);
        labelDate.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelHeureD = new JLabel("Heure de début :");
        labelHeureD.setFont(font2);
        labelHeureD.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelHeureF = new JLabel("Heure de fin :");
        labelHeureF.setFont(font2);
        labelHeureF.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelEtat = new JLabel("Etat :");
        labelEtat.setFont(font2);
        labelEtat.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelType = new JLabel("Type :");
        labelType.setFont(font2);
        labelType.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelSalle = new JLabel("Salle :");
        labelSalle.setFont(font2);
        labelSalle.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelProf = new JLabel("Professeur :");
        labelProf.setFont(font2);
        labelProf.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelPromo = new JLabel("Promo :");
        labelPromo.setFont(font2);
        labelPromo.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));
        JLabel labelGroupe = new JLabel("Groupe :");
        labelGroupe.setFont(font2);
        labelGroupe.setForeground(new Color((float)0.99,(float)0.99,(float)0.99));

        liste = connliste.Affich("Select Nom from cours ");
        cours.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            cours.addItem(liste.get(i));

        }

        liste = connliste.Affich("Select Nom from utilisateurs where Droit=3 ");
        professeur.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            professeur.addItem(liste.get(i));
        }

        liste = connliste.Affich("Select Nom from salle ");
        sallefiltre.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            sallefiltre.addItem(liste.get(i));

        }

        liste = connliste.Affich("Select Nom from type_cours ");
        type_cours.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            type_cours.addItem(liste.get(i));

        }
        liste = connliste.Affich("Select Distinct(Etat) from seance ");
        etat_cours.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            etat_cours.addItem(liste.get(i));

        }
        liste = connliste.Affich("Select Nom from promotion ");
        promo.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            promo.addItem(liste.get(i));

        }
        liste = connliste.Affich("Select Distinct(Nom) from groupe");
        groupes.removeAllItems();
        for (i = 0; i < liste.size(); i++) {
            groupes.addItem(liste.get(i));

        }

        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.DATE);
        spinner_date = new JSpinner(model);
        spinner_date = new JSpinner();
        spinner_date.setModel(model);
        spinner_date.setEditor(new JSpinner.DateEditor(spinner_date, "yyyy-MM-dd"));

        SpinnerDateModel model_time = new SpinnerDateModel();
        model_time.setCalendarField(Calendar.SECOND);
        SpinnerDateModel model_fin = new SpinnerDateModel();
        model_fin.setCalendarField(Calendar.SECOND);
        this.spinner_time = new JSpinner(model_time);
        this.spinner_fin = new JSpinner(model_fin);
        spinner_fin = new JSpinner();
        spinner_fin.setModel(model_fin);
        spinner_fin.setEditor(new JSpinner.DateEditor(spinner_fin, "HH:mm:ss"));
        spinner_time = new JSpinner();
        spinner_time.setModel(model_time);
        spinner_time.setEditor(new JSpinner.DateEditor(spinner_time, "HH:mm:ss"));

        AjouterCours.add(LabelAjout);
        AjouterCours.add(new JLabel(""));

        AjouterCours.add(labelMatiere);
        AjouterCours.add(cours);

        AjouterCours.add(labelDate);
        AjouterCours.add(spinner_date);

        AjouterCours.add(labelHeureD);
        AjouterCours.add(this.spinner_time);

        AjouterCours.add(labelHeureF);
        AjouterCours.add(this.spinner_fin);

        AjouterCours.add(labelEtat);
        AjouterCours.add(etat_cours);

        AjouterCours.add(labelType);
        AjouterCours.add(type_cours);

        AjouterCours.add(labelSalle);
        AjouterCours.add(sallefiltre);

        AjouterCours.add(labelProf);
        AjouterCours.add(professeur);

        AjouterCours.add(labelPromo);
        AjouterCours.add(promo);

        AjouterCours.add(labelGroupe);
        AjouterCours.add(groupes);

        AjouterCours.add(new JLabel(""));
        AjouterCours.add(boutonAjout);

          ModifCours.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        
        FenetreMaj.setLayout(new GridLayout(3, 1));
        FenetreMaj.add(coursActif);
        FenetreMaj.add(ModifCours);
        FenetreMaj.add(AjouterCours);

    }

    /**
     *
     */
    public class ButtonTableauSuppr extends DefaultCellEditor {

        /**
         *
         */
        protected JButton button;
        private boolean isPushed;
        private ButtonListener boutList = new ButtonListener();

        /**
         *
         * @param checkBox
         */
        public ButtonTableauSuppr(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(boutList);
        }

        /**
         *
         * @param table
         * @param value
         * @param isSelected
         * @param row
         * @param col
         * @return
         */
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

    /**
     *
     */
    public class ButtonTableauInt extends DefaultCellEditor {

        /**
         *
         */
        protected JButton button;
        private boolean isPushed;
        private ButtonListener boutList = new ButtonListener();

        /**
         *
         * @param checkBox
         */
        public ButtonTableauInt(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(boutList);
        }

        /**
         *
         * @param table
         * @param value
         * @param isSelected
         * @param row
         * @param col
         * @return
         */
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
                Object IdValue = coursMaj.getModel().getValueAt(this.row, this.col - 11);
                setModifPane(IdValue, MatValue, DateValue, HeureDValue, HeureFValue, etatValue, typeValue, salleValue, profValue, promoValue, groupeValue);
            }
        }
    }

    /**
     *
     * @param Id
     * @param matiere
     * @param Date
     * @param HeureD
     * @param HeureF
     * @param Etat
     * @param Type
     * @param Salle
     * @param Prof
     * @param Promo
     * @param Groupe
     */
    public void setModifPane(Object Id, Object matiere, Object Date, Object HeureD, Object HeureF, Object Etat, Object Type, Object Salle, Object Prof, Object Promo, Object Groupe) {
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

        JLabel labelId = new JLabel("Id :");
        labelId.setFont(font2);
        JTextField TFId = new JTextField(String.valueOf(Id));
        TFId.setEditable(false);

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
        ModifCours.add(labelId);
        ModifCours.add(TFId);
        ModifCours.add(new JLabel(""));
        ModifCours.add(ValiderModif);

        ModifCours.setVisible(true);
        this.setSize(1499, 1000);
        this.setSize(1500, 1000);
    }

    /**
     *
     * @param maSeance
     * @return
     */
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

    /**
     *
     */
    public void resize() {
        this.setSize(1499, 1000);
        this.setSize(1500, 1000);
    }

    private class Filtre implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String prm = "";
            String grpe = "";
            String salle = sallefiltre.getSelectedItem().toString();
            salle = salle.replaceAll("[\n]+", "");
            String mat = cours.getSelectedItem().toString();
            mat = mat.replaceAll("[\n]+", "");

            String prof = professeur.getSelectedItem().toString();
            prof = prof.replaceAll("[\n]+", "");
            if (user.getDroit() == 2) {
                String pro = promo_groupe.getSelectedItem().toString();
                pro = pro.replaceAll("[\n]+", "");
                if(!pro.equals(""))
                { prm = pro.substring(0, 4);
                grpe = pro.substring(7);
                }
            }
            resize();
            FenetreRecap.removeAll();
            try {
                initRecap(salle, mat, prof, prm, grpe);
                defineRecap();
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void defineReporting() throws SQLException, ClassNotFoundException {
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
        FinalContent.setLayout(new BoxLayout(FinalContent, BoxLayout.PAGE_AXIS));

        //Premier graph : Ici il faut récupérer le nombre d'heure de chaque matière, le nombre d'heure de chaque matière qui n'ont pas encore été faites (ressemble aux barres du bas), et diviser le 2eme par le 1er
        String nbcoursmaths;
        double cpt1 = 0;
        double cpt2 = 0, cpt3 = 0, cpt4 = 0;
        int idgrp = student.getGroupe();
        int ids = 0;
        System.out.println(idgrp);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=1 and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                nbcoursmaths = liste.get(i);

            }
            for (int i = 0; i < liste.size(); i++) {
                cpt1 = cpt1 + 1;
            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=2 and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                nbcoursmaths = liste.get(i);

            }
            for (int i = 0; i < liste.size(); i++) {
                cpt2 = cpt2 + 1;
            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=3 and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                nbcoursmaths = liste.get(i);

            }
            for (int i = 0; i < liste.size(); i++) {
                cpt3 = cpt3 + 1;
            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Id_cours=4 and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                nbcoursmaths = liste.get(i);

            }
            for (int i = 0; i < liste.size(); i++) {
                cpt4 = cpt4 + 1;
            }
        }
        double somme = cpt1 + cpt2 + cpt3 + cpt4;
        double cp1 = (cpt1 / somme) * 100;
        double cp2 = (cpt2 / somme) * 100;
        double cp3 = (cpt3 / somme) * 100;
        double cp4 = (cpt4 / somme) * 100;

        DefaultPieDataset HeureRestantes = new DefaultPieDataset();
        HeureRestantes.setValue("Mathématiques", cp1);
        HeureRestantes.setValue("Electronique", cp2);
        HeureRestantes.setValue("Physique", cp3);
        HeureRestantes.setValue("Probabilités", cp4);
        JFreeChart pieChart = ChartFactory.createPieChart("Heures restantes par matière", HeureRestantes, true, true, true);
        ChartPanel cPanel = new ChartPanel(pieChart);

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
        JFreeChart BarChart = ChartFactory.createBarChart("Nombre de place par salle", "Nombre de place", "Salle", placeSalle, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel BPanel = new ChartPanel(BarChart);

        //ici faut compter sur la semaine le nombre d'heure réalisé dans la semaine en tout pour un éleves, en gros faut faire une boucle avec i qui augmente de 1 pour chaque heure de l'éleve chaque jour et ensuite on multiplie par 1.5 pour les heures 
        int cptt = 0;
        String oned = "";
        int cptt2 = 0, cptt3 = 0, cptt4 = 0, cptt5 = 0, cptt6 = 0;
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Date='2020-06-01' and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                cptt = cptt + 1;

            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Date='2020-06-02' and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                cptt2 = cptt2 + 1;

            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Date='2020-06-03' and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                cptt3 = cptt3 + 1;

            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Date='2020-06-04' and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                cptt4 = cptt4 + 1;

            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Date='2020-06-05' and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                cptt5 = cptt5 + 1;

            }
        }
        rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
        while (rs.next()) {
            ids = rs.getInt("id_seance");
            liste = connliste.Affich("Select ID from seance where Date='2020-06-06' and ID=" + ids);
            for (int i = 0; i < liste.size(); i++) {
                cptt6 = cptt6 + 1;

            }
        }

        System.out.println(cptt);
        DefaultCategoryDataset heureparSemaine = new DefaultCategoryDataset();
        heureparSemaine.addValue(cptt, "Heure", "Lundi");
        heureparSemaine.addValue(cptt + cptt2, "Heure", "Mardi");
        heureparSemaine.addValue(cptt + cptt2 + cptt3, "Heure", "Mercredi");
        heureparSemaine.addValue(cptt + cptt2 + cptt3 + cptt4, "Heure", "Jeudi");
        heureparSemaine.addValue(cptt + cptt2 + cptt3 + cptt4 + cptt5, "Heure", "Vendredi");
        heureparSemaine.addValue(cptt + cptt2 + cptt3 + cptt4 + cptt5 + cptt6, "Heure", "Samedi");
        JFreeChart lineChart = ChartFactory.createLineChart("Nombre d'heure de l'étudiant au cours de la semaine", "Jours", "compteur d'heure", heureparSemaine, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel LPanel = new ChartPanel(lineChart);
        LPanel.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));

        // la c'est pareil que le 1er mais dans l'autre sens vu que c'est les heures deja faites donc pas de nouvelles données a récuperer
      DateFormat format= new SimpleDateFormat("yyyy/MM/dd");
Calendar calendar=Calendar.getInstance();
int IDseance=0;
double ccpt=0;
double coursm=0;
rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
String date= format.format(calendar.getTime());
while(rs.next())
{
    IDseance=rs.getInt("id_seance");
    liste2=connliste.Affich("select ID from seance where Id_cours=1 and ID=" + IDseance);
    liste=connliste.Affich("select ID from seance where ID= "+ IDseance + " and Id_cours=1 and Date<= '" +date + "'");
    for(int i =0;i<liste.size();i++)
    {
        ccpt++;
    }
    for(int i=0;i<liste2.size();i++)
    {
        coursm=coursm+1;
    }
}
int ccpt2=0;
double course=0;
rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
while(rs.next())
{
    IDseance=rs.getInt("id_seance");
    liste2=connliste.Affich("select ID from seance where Id_cours=2 and ID=" + IDseance);
    liste=connliste.Affich("select ID from seance where ID= "+ IDseance + " and Id_cours=2 and Date<= '" +date + "'");
    for(int i =0;i<liste.size();i++)
    {
        ccpt2++;
    }
    for(int i=0;i<liste2.size();i++)
    {
        course=coursm+1;
    }
}
double ccpt3=0;
double coursph=0;

rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
while(rs.next())
{
    IDseance=rs.getInt("id_seance");
    liste2=connliste.Affich("select ID from seance where Id_cours=3 and ID=" + IDseance);
    liste=connliste.Affich("select ID from seance where ID= "+ IDseance + " and Id_cours=3 and Date<= '" +date + "'");
    for(int i =0;i<liste.size();i++)
    {
        ccpt3++;
    }
    for(int i=0;i<liste2.size();i++)
    {
        coursph=coursm+1;
    }
}
double probasp=0;
double ccpt4=0;
double courspro=0;
rs = stmt.executeQuery("select id_seance from seance_groupe where id_groupe=" + idgrp);
while(rs.next())
{
    IDseance=rs.getInt("id_seance");
    liste2=connliste.Affich("select ID from seance where Id_cours=4 and ID=" + IDseance);
    liste=connliste.Affich("select ID from seance where ID= "+ IDseance + " and Id_cours=4 and Date<= '" +date + "'");
    for(int i =0;i<liste.size();i++)
    {
        ccpt4++;
    }
    for(int i=0;i<liste2.size();i++)
    {
        courspro=courspro+1;
    }
}



double mathsp= ccpt/coursm;
mathsp=mathsp*100;
int mathspi=(int)mathsp;

double elecsp= ccpt2/course;
elecsp=elecsp*100;
int elecspi=(int) elecsp;

double physp=ccpt3/coursph;
physp=physp*100;
int physpi=(int)physp;

double probsp=ccpt4/courspro;
probsp=probsp*100;
int probspi=(int)probsp;
        JProgressBar BarMaths = new JProgressBar();
        BarMaths.setValue(mathspi);
        BarMaths.setMaximumSize(new Dimension(500, 500));
        JProgressBar BarElec = new JProgressBar();
        BarElec.setValue(elecspi);
        BarElec.setMaximumSize(new Dimension(500, 500));
        JProgressBar BarPhy = new JProgressBar();
        BarPhy.setValue(physpi);
        BarPhy.setMaximumSize(new Dimension(500, 500));
        JProgressBar BarProba = new JProgressBar();
        BarProba.setValue(probspi);
        BarProba.setMaximumSize(new Dimension(500, 500));

        
        Font fonte = new Font(" TimesRoman ", Font.BOLD, 27);
        Font fonte2 = new Font(" TimesRoman ", Font.BOLD, 42);
        JLabel labelMaths = new JLabel("Mathématiques :");
        labelMaths.setFont(fonte);
        JLabel labelProba = new JLabel(" Electronique :");
        labelProba.setFont(fonte);
        JLabel labelPhy = new JLabel(" Physique :");
        labelPhy.setFont(fonte);
        JLabel labelElec = new JLabel("Probabilités :");
        labelElec.setFont(fonte);
        JLabel labelTitre = new JLabel("Avancement du cours :");
        labelTitre.setFont(fonte2);
        JLabel labelVide = new JLabel("                                                                                                                                              ");
        labelVide.setFont(fonte2);

        Ligne1.add(cPanel);
        Ligne1.setBorder(BorderFactory.createEmptyBorder(0,300,0,300));
        Ligne1.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        Ligne2.add(BPanel);
        Ligne2.add(LPanel);
        LigneTitre.add(labelTitre);
        LigneTitre.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        Ligne3.add(labelMaths);
        Ligne3.add(BarMaths);
        Ligne3.add(labelProba);
        Ligne3.add(BarProba);
        Ligne3.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        LigneVide.add(labelVide);
        LigneVide.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        Ligne4.add(labelElec);
        Ligne4.add(BarElec);
        Ligne4.add(labelPhy);
        Ligne4.add(BarPhy);
        Ligne4.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));
        FinalContent.setBackground(new Color((float)0.27,(float)0.83,(float)0.4));

        FinalContent.add(Ligne1);
        FinalContent.add(Ligne2);
        FinalContent.add(LigneTitre);
        FinalContent.add(Ligne3);
        FinalContent.add(LigneVide);
        FinalContent.add(Ligne4);
        BPanel.setBounds(0, 1000, 500, 500);
        FinalContent.setBounds(0, 0, 1500, 800);
        FenetreReporting.add(FinalContent);
    }

    /**
     *
     */
    public void hideCal() {
        this.boutonCal.setVisible(false);
    }

    /**
     *
     */
    public void hideRec() {
        this.boutonRec.setVisible(false);
    }

    /**
     *
     */
    public void hideMaj() {
        this.boutonMaj.setVisible(false);
    }

    /**
     *
     */
    public void hideRep() {
        this.boutonRep.setVisible(false);
    }

    /**
     *
     */
    public void showCal() {
        this.boutonCal.setVisible(true);
    }

    /**
     *
     */
    public void showRec() {
        this.boutonRec.setVisible(true);
    }

    /**
     *
     */
    public void showMaj() {
        this.boutonMaj.setVisible(true);
    }

    /**
     *
     */
    public void showRep() {
        this.boutonRep.setVisible(true);
    }

    /**
     *
     */
    public void setContentMaj() {
        this.setContentPane(FenetreMaj);
    }

    /**
     *
     */
    public void setContentRec() {
        this.setContentPane(FenetreRecap);
    }
}
