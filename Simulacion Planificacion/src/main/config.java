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

/**
 *
 * @author RDG
 */
public class config {
    JPanel cards;
    JFrame frame;
    CardLayout cardLayout;
    JTextArea tiempoArea;

    public config() {
        JPanel card = new JPanel();

        // Procesadores Header
        JPanel procesadores0 = new JPanel();
        procesadores0.add(new JLabel("Procesadores"));
        
        // Cantidad de Procesadores
        JPanel procesadores1 = new JPanel();
        procesadores1.add(new JButton(" CPU 0 "));
        procesadores1.add(new JButton(" CPU 1 "));
        procesadores1.add(new JButton(" CPU 2 "));
        procesadores1.add(new JButton(" CPU 3 "));
        
        JPanel procesadores2 = new JPanel();
        procesadores2.add(new JButton(" CPU 4 "));
        procesadores2.add(new JButton(" CPU 5 "));
        procesadores2.add(new JButton(" CPU 6 "));
        procesadores2.add(new JButton(" CPU 7 "));
        
        JPanel procesadores3 = new JPanel();
        procesadores3.add(new JButton(" CPU 8 "));
        procesadores3.add(new JButton(" CPU 9 "));
        procesadores3.add(new JButton("CPU 10"));
        procesadores3.add(new JButton("CPU 11"));

        // Tiempo
        JPanel tiempo = new JPanel();
        tiempo.add(new JLabel("Tiempo por Ciclo (ms)"));
        tiempoArea = new JTextArea("1000");
        tiempo.add(tiempoArea);

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.add(procesadores0);
        card.add(procesadores1);
        card.add(procesadores2);
        card.add(procesadores3);
        card.add(tiempo);

        frame = new JFrame("Configuracion General");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(card);
    }

    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}