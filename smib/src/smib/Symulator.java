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

    private int jednostkaCzasu;
    private int czasKtoryMinal = 0;
    private double nastepnaObsluga = 0;     //kiedy zostanie obsluzone zdarzenie
    private double nastepneZdarzenie = 0;   //kiedy pojawi sie nowe zdarzenie
    private double lambda;
    private double mi;
    private int rozmiarKolejki;
    
    private ArrayBlockingQueue<Zdarzenie> kolejka;
    private Random random = new Random();
    private int lastId = 0;

    public Symulator(double lambda, double mi, int rozmiarKolejki,
            int jednostkaCzasu) {
        this.lambda = lambda;
        this.mi = mi;
        this.rozmiarKolejki = rozmiarKolejki;
        this.jednostkaCzasu = jednostkaCzasu * 1000;

        kolejka = new ArrayBlockingQueue<Zdarzenie>(this.rozmiarKolejki);
        losujKiedyNastepneZdarzenie();
    }

    public void update(int delta) {
        if( nastepnaObsluga < czasKtoryMinal && !kolejka.isEmpty()){
            losujKiedyNastepneObsluzenie();
        }
        czasKtoryMinal += delta;
        if (czasKtoryMinal >= nastepnaObsluga && !kolejka.isEmpty()) {
            obsluzZdarzenie();
        }
        if (czasKtoryMinal >= nastepneZdarzenie) {
            noweZdarzenie();
        }
    }

    public void draw(Graphics g) {
    }

    private void noweZdarzenie() {
        
        Zdarzenie zdarzenie = new Zdarzenie(lastId++);
        System.out.println("Nowe zdarzenie " + zdarzenie.getId() + "at: " + nastepneZdarzenie);
        if (kolejka.offer(zdarzenie) == false) {
            System.out.println("Brak miejsca w kolejce. Zdarzenie zostalo odrzucone.");
        }
        losujKiedyNastepneZdarzenie();

    }

    private void losujKiedyNastepneZdarzenie() {
        double msNaZdarzenie = jednostkaCzasu / lambda;
        nastepneZdarzenie += msNaZdarzenie
                + (random.nextGaussian() * msNaZdarzenie * 0.1);    // +- 10% ustalonego czasu
    }

    private void losujKiedyNastepneObsluzenie() {
        double msNaObsluge = jednostkaCzasu / mi;
        nastepnaObsluga = czasKtoryMinal 
                + msNaObsluge
                + (random.nextGaussian() * msNaObsluge * 0.1);      // +- 10% ustalonego czasu
    }

    private void obsluzZdarzenie() {
        Zdarzenie zdarzenie = kolejka.poll();
        if(zdarzenie != null)
        {
            System.out.println("Zdarzenie " + zdarzenie.getId() 
                    + " zostalo obsluzone at: " + czasKtoryMinal);
        }
    }

}
