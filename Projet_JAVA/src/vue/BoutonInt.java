/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author titig
 */
//classe qui redéfini un bouton pour pouvoir intéragir avec (en cours de développement)
public class BoutonInt extends JButton implements MouseListener {

    private final String nom;

    /**
     *
     * @param str
     */
    public BoutonInt(String str) {
        super(str);
        this.nom = str;
        this.addMouseListener(this);
    }

    /**
     *
     * @param event
     */
    @Override
    public void mouseClicked(MouseEvent event) {
    }

    /**
     *
     * @param event
     */
    @Override
    public void mouseEntered(MouseEvent event) {
    }

    /**
     *
     * @param event
     */
    @Override
    public void mouseExited(MouseEvent event) {
    }

    /**
     *
     * @param event
     */
    @Override
    public void mousePressed(MouseEvent event) {
    }

    /**
     *
     * @param event
     */
    @Override
    public void mouseReleased(MouseEvent event) {
    }

}
