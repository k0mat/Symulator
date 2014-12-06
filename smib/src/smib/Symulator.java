/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smib;

import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;


/**
 *
 * @author Mateusz
 */
public class Symulator {
    private double lambda;
    private double mi;
    private int rozmiarKolejki;
    private ArrayBlockingQueue <Zdarzenie> kolejka;
    private Random random = new Random();
    
    public Symulator(double lambda, double mi, int rozmiarKolejki)
    {
        this.lambda = lambda;
        this.mi = mi;
        this.rozmiarKolejki = rozmiarKolejki;
        
        kolejka = new ArrayBlockingQueue<Zdarzenie>(this.rozmiarKolejki);
        
    }
    
    public void update(int delta)
    {
    }
    
    public void draw(Graphics g)
    {
    }
}
