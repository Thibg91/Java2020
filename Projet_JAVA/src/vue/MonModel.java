/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author titig
 */
 //Classe définissant mon modele de tableau, c'est du contenu d'open classroom ,il n'y a rien a modifier ici normalement
    class MonModel extends AbstractTableModel{
        final private Object[][] monContenu;
        final private String[] titre;
        
        public MonModel(Object[][] monContenu , String[] titre){
            this.monContenu = monContenu;
            this.titre = titre;
        }
        
        @Override
        public String getColumnName(int col){
        return this.titre[col];
        }
                
        @Override
        public int getColumnCount()
        {
        return this.titre.length;
        }
        
        @Override
       public int getRowCount() {
           return this.monContenu.length;
       }
       
        @Override
       public Object getValueAt(int row, int col){
           return this.monContenu[row][col];
       }
       
        @Override
       public void setValueAt (Object value, int row, int col){
               this.monContenu[row][col] = value;
       }
       
        @Override
       public Class getColumnClass(int col){
           return this.monContenu[0][col].getClass();
       }
       
        @Override
       public boolean isCellEditable(int row, int col)
       {if(getValueAt(0,col) instanceof JButton)
           return false;
       else
           return true;
       }
       
     
        
    }