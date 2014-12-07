/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smib;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mateusz
 */
class ZdarzenieRuchome extends Zdarzenie{
    private static Symulator symulator;
    private double pozycja;
    private double koniec;
    private boolean przychodzace;
    private boolean usunac = false;
    private Color color;
    
    
    public ZdarzenieRuchome(int id, int poczatek, int koniec, boolean przychodzace){
        super(id);
        this.pozycja = poczatek;
        this.koniec = koniec;
        this.przychodzace = przychodzace;
        if(przychodzace){
              losujColor();
        }
    }
    
    public void update(int delta){
        double ruch = 0.2 * delta;
        if(pozycja >= koniec){
            usunac = true;
            return;
        }
        if(pozycja + ruch >= koniec){
            pozycja = koniec;
            skoncz();
        } else {
            pozycja += ruch;
        }
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        if(przychodzace){
           g.fillRect((int) pozycja, 300, 50, 50);
        } else {
           g.fillOval((int) pozycja, 300, 50, 50);
        }
        g.setColor(Color.WHITE);
        g.fillRect((int) (pozycja + 12), 300 + 12, 25, 25);
        g.setColor(Color.BLACK);
        g.drawString("" + getId(), (int) (pozycja + 15), 300 + 27);
        
    }
    public void setWychodzace(int poczatek, int koniec){
        usunac = false;
        przychodzace = false;
        this.pozycja = poczatek;
        this.koniec = koniec;
    }
    
    public static void setSymulator(Symulator symulator){
       ZdarzenieRuchome.symulator = symulator;
    }

    public boolean czyUsunac(){
        return usunac;
    }
    
    private void skoncz() {
        if(przychodzace){
            symulator.dodajZdarzenie(this);
        }
    }
    
    private void losujColor(){
        color = new Color(symulator.getRandom().nextInt(255), symulator.getRandom().nextInt(255), symulator.getRandom().nextInt(255));  
    }
    
    public Color getColor(){
        return color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
}
