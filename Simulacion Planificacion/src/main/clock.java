/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import main.simulation;

/**
 *
 * @author RDG
 */
public class clock {
    JFrame frame;
    int tiempo = 1;
    JSlider slider;
    JLabel tiempoLabel;
    JButton status;
    Boolean statusFR;

    public clock() {       
        JPanel card = new JPanel();
        JPanel time = new JPanel();
        
        slider = new JSlider(1,100); // Tiempo ms * 100
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setValue(1);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                tiempo = source.getValue();
                updateTimeLabel();
            }
        });
        
        time.setLayout(new BoxLayout(time, BoxLayout.Y_AXIS));
        String temp = "Tiempo por Ciclo: 100 ms";
        tiempoLabel = new JLabel(temp);
        time.add(tiempoLabel);
        time.add(slider);
        
        card.setLayout(new BoxLayout(card, BoxLayout.X_AXIS));
        status = new JButton("Play");
        statusFR = false;
        
        status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusFR = !statusFR;
                if (statusFR == true){
                    status.setText("Pause");
                }else{
                    status.setText("Play");
                }
            }
        });
        
        card.add(status);
        card.add(time);

        frame = new JFrame("Configuracion General");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(card);
    }
    
    private void updateTimeLabel(){
        String temp = "Tiempo por Ciclo: " + tiempo + "00" + " ms";
        tiempoLabel.setText(temp);
    }

    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}