/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author titig
 */

//classe qui rédéfini le contenu d'une case de tableau, sans cette classe, on ne peut pas afficher de JTextPane dans une case
public class ComposantTable extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable monTableau,Object value, boolean isSelected,boolean hasFocus,int row,int col)
    {
        if(value instanceof JTextPane)
            return (JTextPane) value;
        else
            return this;
    }
    
}
