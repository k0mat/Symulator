/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smib;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Mateusz
 */
public class DrawPanel extends JPanel {
    MainPanel panel;
    
    public DrawPanel(MainPanel panel){
        super();
        this.panel = panel;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.setBackground(new Color(70, 100, 230));
        super.paintComponent(g);
        Symulator sym = panel.getSymulator();
        if(sym != null){
            sym.draw(g);
        }

    }
}
