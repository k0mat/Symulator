/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Mateusz
 */
public class MainPanel extends JPanel implements ActionListener {

    final DrawPanel drawPanel = new DrawPanel();
    JTextField lamdaTextField = new JTextField("90", 10);
    JTextField rozmiarTextField = new JTextField("60", 10);
    JTextField miTextField = new JTextField("200", 10);
    JTextField czasTextField = new JTextField("60", 10);
    JLabel lamdaLabel = new JLabel("λ:");
    JLabel rozmiarLabel = new JLabel("Rozmiar kolejki:");
    JLabel miLabel = new JLabel("μ: ");
    JLabel czasLabel = new JLabel("Jednostka czasu(sekundy):");
    JButton startButton = new JButton("Start");

    GroupLayout layout = new GroupLayout(this);
    Timer timer;
    int timerDelay = 20; // w milisekundach, 20ms = 50 fps
    Symulator symulator;

    public MainPanel() {
        super();
        this.setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(drawPanel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lamdaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lamdaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(miLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(miTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(rozmiarLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(rozmiarTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(czasLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(czasTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(startButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(drawPanel)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(lamdaLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                                .addComponent(lamdaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(miLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                         GroupLayout.PREFERRED_SIZE)
                                .addComponent(miTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(rozmiarLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(rozmiarTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(czasLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(czasTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(startButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        )
                )
        );
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               startButtonEvent(evt);
            }
        });
        
        timer = new Timer(20, this);
        timer.start();
    }
    
    private void startButtonEvent(ActionEvent evt){
        double lambda = Double.valueOf(lamdaTextField.getText());
        double mi = Double.valueOf(miTextField.getText());
        int rozmiar = Integer.valueOf(rozmiarTextField.getText());
        int jednostka = Integer.valueOf(czasTextField.getText());
        symulator = new Symulator(lambda, mi, rozmiar, jednostka);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if( ae.getSource().equals(timer))
        {
           if(symulator != null)
           {
               symulator.update(timerDelay);
               symulator.draw(drawPanel.getGraphics());
           }
        }
        
    }
}
