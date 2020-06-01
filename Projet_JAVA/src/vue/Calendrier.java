/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JTable;
import javax.swing.JTextPane;

/**
 *
 * @author titig
 */
public class Calendrier extends JTable {
    private String[] titre;
    private Object[][] contenu;
    
    
    public Calendrier(MonModel model)
    { 
        super(model);
    
}
    public void ajouterCours (JTextPane monCours, int row , int col)
{
    this.getModel().setValueAt(monCours, row, col);
}
}

