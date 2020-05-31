/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

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
        
        public String getColumnName(int col){
        return this.titre[col];
        }
                
        public int getColumnCount()
        {
        return this.titre.length;
        }
        
        @Override
       public int getRowCount() {
           return this.monContenu.length;
       }
       
       public Object getValueAt(int row, int col){
           return this.monContenu[row][col];
       }
       
       public void setValueAt (Object value, int row, int col){
           if(!this.getColumnName(col).equals("Age")&& !this.getColumnName(col).equals("Suppression"))
               this.monContenu[row][col] = value;
       }
       
        @Override
       public Class getColumnClass(int col){
           return this.monContenu[0][col].getClass();
       }
       
       public boolean isCellEditable(int row, int col)
       {
           return true;
       }
       
     
        
    }