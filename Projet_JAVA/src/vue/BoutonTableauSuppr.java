/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author titig
 */
public class BoutonTableauSuppr extends JButton implements TableCellRenderer{

    /**
     *
     * @param table
     * @param value
     * @param isSelected
     * @param isFocus
     * @param row
     * @param col
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
        this.setText("supprimer");
        
        return this;
        
    }
}
