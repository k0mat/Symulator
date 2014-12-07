/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smib;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


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

    private ArrayList<ZdarzenieRuchome> ruchome;
    private ArrayBlockingQueue<Zdarzenie> kolejka;
    private Random random = new Random();
    private int lastId = 0;
    Font f = new Font("Dialog", Font.PLAIN, 40);

    public Symulator(double lambda, double mi, int rozmiarKolejki,
            int jednostkaCzasu) {
        this.lambda = lambda;
        this.mi = mi;
        this.rozmiarKolejki = rozmiarKolejki;
        this.jednostkaCzasu = jednostkaCzasu * 1000;

        ruchome = new ArrayList<ZdarzenieRuchome>();
        kolejka = new ArrayBlockingQueue<Zdarzenie>(this.rozmiarKolejki);
        losujKiedyNastepneZdarzenie();
        ZdarzenieRuchome.setSymulator(this);
        
    }

    public void update(int delta) {
        if (nastepnaObsluga < czasKtoryMinal && !kolejka.isEmpty()) {
            losujKiedyNastepneObsluzenie();
        }
        czasKtoryMinal += delta;
        if (czasKtoryMinal >= nastepnaObsluga && !kolejka.isEmpty()) {
            obsluzZdarzenie();
        }
        if (czasKtoryMinal >= nastepneZdarzenie) {
            noweZdarzenie();
        }
        for (ZdarzenieRuchome zd : ruchome) {
            zd.update(delta);
        }
        //usuwamy stare
        for (Iterator<ZdarzenieRuchome> iterator = ruchome.iterator(); iterator.hasNext();) {
            ZdarzenieRuchome zdarzenie = iterator.next();
            if (zdarzenie.czyUsunac()) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
    }

    public void draw(Graphics g) {        
        for (ZdarzenieRuchome zd : ruchome) {
            zd.draw(g);
        }
        g.setColor(Color.BLACK);
        g.fillRect(350, 200, 400, 250);
        if(kolejka.size() < rozmiarKolejki){
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.RED);
        }    
        g.fillRect(350 + 100, 200 + 50, 200, 150);
        g.setColor(Color.BLACK);
        g.setFont(f);
        g.drawString("" + kolejka.size(), 350 + 175, 200 + 125);
    
    }

    public void dodajZdarzenie(Zdarzenie zdarzenie) {
        if (kolejka.offer(zdarzenie) == false) {
            System.out.println("Brak miejsca w kolejce. Zdarzenie zostalo odrzucone.");
        }
    }

    private void noweZdarzenie() {

        ZdarzenieRuchome zdarzenie
                = new ZdarzenieRuchome(lastId++, 0, 350, true);
        ruchome.add(zdarzenie);
        System.out.println("Nowe zdarzenie "
                + zdarzenie.getId() + " at: " + (int) (nastepneZdarzenie / 1000));

        losujKiedyNastepneZdarzenie();

    }

    private void losujKiedyNastepneZdarzenie() {
        double msNaZdarzenie = jednostkaCzasu / lambda;
        nastepneZdarzenie += msNaZdarzenie
                + (random.nextGaussian() * msNaZdarzenie * 0.2);    // +- 10% ustalonego czasu
    }

    private void losujKiedyNastepneObsluzenie() {
        double msNaObsluge = jednostkaCzasu / mi;
        nastepnaObsluga = czasKtoryMinal
                + msNaObsluge
                + (random.nextGaussian() * msNaObsluge * 0.2);      // +- 10% ustalonego czasu
    }

    private void obsluzZdarzenie() {
        Zdarzenie zdarzenie = kolejka.poll();
        if (zdarzenie != null) {
            System.out.println("Zdarzenie " + zdarzenie.getId()
                    + " zostalo obsluzone at: " + (int) (czasKtoryMinal / 1000));
            Color color = ((ZdarzenieRuchome)zdarzenie).getColor();
            ZdarzenieRuchome zdarzenieRuchome = new ZdarzenieRuchome(zdarzenie.getId(), 700, 1000, false);
            zdarzenieRuchome.setColor(color);
            ruchome.add(zdarzenieRuchome);
        }
    }
    
    public Random getRandom(){
        return random;
    }
    
}
