/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author titig
 */
public class ButtonTableauInt extends DefaultCellEditor {

    /**
     *
     */
    protected JButton button;
    private boolean isPushed;
    private final ButtonListener boutList = new ButtonListener();
    
    /**
     *
     * @param checkBox
     */
    public ButtonTableauInt(JCheckBox checkBox){
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
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col){
        boutList.setRow(row);
        boutList.setColumn(col);
        boutList.setTable(table);
        button.setText((value == null) ? "" : value.toString());
        return button;
    }
    
    class ButtonListener implements ActionListener{
        private int row, col;
        private JTable table;
        private final int nbre = 0;
        
        public void setColumn(int col){this.col = col;}
        public void setRow(int row){this.row = row;}
        public void setTable(JTable table){this.table = table;}
        
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("On verra pour ca plus ");
            
        }
        
        
    }
}
