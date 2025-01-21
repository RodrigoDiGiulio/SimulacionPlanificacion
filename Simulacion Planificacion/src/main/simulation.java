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
public class simulation {
    JPanel cards;
    JFrame frame;
    CardLayout cardLayout;
    JTextArea name;
    JTextArea instNum;
    JTextArea exceptionGeneration;
    JTextArea exceptionSatifaction;

    public simulation() {
        JPanel card = new JPanel();
        
        // Procesadores Header
        JPanel procesadores0 = new JPanel();
        procesadores0.add(new JLabel("Procesadores"));
        
        // Cantidad de Procesadores 0-3
        JPanel procesadores1 = new JPanel();
        procesadores1.add(new JButton(" CPU 0 "));
        procesadores1.add(new JButton(" CPU 1 "));
        procesadores1.add(new JButton(" CPU 2 "));
        procesadores1.add(new JButton(" CPU 3 "));
        
        // Procesos 0-3
        JPanel procesos1 = new JPanel();
        procesos1.add(new JButton("A"));
        procesos1.add(new JButton("B"));
        procesos1.add(new JButton("C"));
        procesos1.add(new JButton("D"));
        
        // Cantidad de Procesadores 4-7
        JPanel procesadores2 = new JPanel();
        procesadores2.add(new JButton(" CPU 4 "));
        procesadores2.add(new JButton(" CPU 5 "));
        procesadores2.add(new JButton(" CPU 6 "));
        procesadores2.add(new JButton(" CPU 7 "));        

        // Procesos 4-7
        JPanel procesos2 = new JPanel();
        procesos2.add(new JButton("E"));
        procesos2.add(new JButton("F"));
        procesos2.add(new JButton("G"));
        procesos2.add(new JButton("H"));
        
        // Cantidad de Procesadores 8-11
        JPanel procesadores3 = new JPanel();
        procesadores3.add(new JButton(" CPU 8 "));
        procesadores3.add(new JButton(" CPU 9 "));
        procesadores3.add(new JButton("CPU 10"));
        procesadores3.add(new JButton("CPU 11"));
        
        // Procesos 8-11
        JPanel procesos3 = new JPanel();
        procesos3.add(new JButton("I"));
        procesos3.add(new JButton("J"));
        procesos3.add(new JButton("K"));
        procesos3.add(new JButton("L"));

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.add(procesadores0);
        card.add(procesadores1);
        card.add(procesos1);
        card.add(procesadores2);
        card.add(procesos2);
        card.add(procesadores3);
        card.add(procesos3);

        frame = new JFrame("Procesos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(card);
    }

    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}