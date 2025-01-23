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
public class process {
    // Cpu Bound, IO Bound
    JPanel cards;
    JFrame frame;
    CardLayout cardLayout;
    JTextArea name;
    JTextArea instNum;
    JTextArea exceptionGeneration;
    JTextArea exceptionSatifaction;

    public process() {
        JPanel card = new JPanel();

        // Procesos Header
        JPanel procesadores0 = new JPanel();
        procesadores0.add(new JLabel("Creador de Procesos"));

        // Nombre
        JPanel nombre = new JPanel();
        name = new JTextArea("Nombre");
        nombre.add(name);
        
        // Numero de Instrucciones
        JPanel numInst = new JPanel();
        instNum = new JTextArea("Numero de Instrucciones");
        numInst.add(instNum);
        
        // CPU Bound
        JPanel cpuBound = new JPanel();
        cpuBound.add(new Checkbox("CPU Bound"));
        
        // I/O Bound
        JPanel ioBound = new JPanel();
        ioBound.add(new Checkbox("I/O Bound"));
        
        // I/O Bound Extra Header
        JPanel ioExtra0 = new JPanel();
        ioExtra0.add(new JLabel("I/O Bound Extra"));
        
        // I/O Bound Extra
        JPanel ioExtra1 = new JPanel();
        exceptionGeneration = new JTextArea("Generar");
        exceptionSatifaction = new JTextArea("Satisfacer");
        ioExtra1.add(exceptionGeneration);
        ioExtra1.add(exceptionSatifaction);
        

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.add(nombre);
        card.add(numInst);
        card.add(cpuBound);
        card.add(ioBound);
        card.add(ioExtra0);
        card.add(ioExtra1);

        frame = new JFrame("Procesos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(card);
    }

    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}