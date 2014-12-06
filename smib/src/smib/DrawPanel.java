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
    
     @Override
     public void paintComponent(Graphics g) {
        super.setBackground(Color.WHITE);
        super.paintComponent(g);
        
        g.fillRect(5, 25, 5, 25);
        
     }
     
     public void wyczysc(Graphics g)
     {
        super.setBackground(Color.WHITE);
        super.paintComponent(g);
     }
}
