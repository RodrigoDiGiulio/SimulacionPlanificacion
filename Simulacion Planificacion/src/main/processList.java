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
public class processList {
    JPanel cards;
    JFrame frame;
    JList listReady;
    JList listBlock;
    JList listLT;
    String ready[] = {"A","B"};
    String block[] = {"C","D"};
    String lt[] = {"E","F"};

    public processList() {
        JPanel card = new JPanel();

        // Header
        JPanel header = new JPanel();
        header.add(new JLabel("Listas de Procesos"));
        
        // Listas
        JPanel listas = new JPanel();
        
        // Ready
        JList listReady = new JList(ready);
        listas.add(listReady);
        
        // Block
        JList listBlock = new JList(block);
        listas.add(listBlock);
        
        // Long Term
        JList listLT = new JList(lt);
        listas.add(listLT);
        
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.add(header);
        card.add(listas);

        frame = new JFrame("Procesos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(card);
    }

    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}