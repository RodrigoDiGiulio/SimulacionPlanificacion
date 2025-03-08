/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javaapplication8.primitivas.File;
import javaapplication8.primitivas.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author RDG
 */
public class BlockDisplay {
    public JFrame frame;
    public JPanel panel;
    public JButton[] blocks;
    public int size;
    public int availableSpace;
    public List colors;
    
    public BlockDisplay(int MB) {
        size = MB;
        availableSpace = MB;
        panel = new JPanel(new GridLayout((int)Math.sqrt(MB),(int)Math.sqrt(MB),2,0));
        frame = new JFrame("Block Display");
        blocks = new JButton[size];
        for (int i = 0; i < size; i++) {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(2,1,0,0));
            JLabel blockPos = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            blockPos.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(blockPos);
            
            JButton button = new JButton("");
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(4, 4));
            button.setBackground(Color.lightGray);
            button.setForeground(Color.black);
            button.setBorderPainted(false);
            buttonPanel.add(button);
            
            blocks[i] = button;
            panel.add(buttonPanel);
        }
        
        colors = new List();
        int count = 0;
        for (int r = 51; r < 255; r = r + 51) {
            for (int g = 51; g < 255; g = g + 51) {
                for (int b = 51; b < 255; b = b + 51) {
                    colors.addEnd(new Color(r,g,b));
                    count++;
                }
            }
        }
        frame.setContentPane(panel);
        frame.setSize(400, 400);
        frame.setLocation(0, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        main.miscSystem.updateLog("BlockDisplay started Correctly");
    }
    
    public void dispose(){
        frame.dispose();
    }
    
    public Color getColor(){
        for (int i = 0; i < colors.isSize(); i++) {
            if (colors.searchPos(i) != Color.lightGray){
                Color tempColor = (Color) colors.searchPos(i);
                colors.delPos(i);
                colors.addEnd(new Color(Color.lightGray.getRGB()));
                return tempColor;
            } 
        }
        return Color.lightGray;
    }
    
    public void setColor(Color color){
        for (int i = 0; i < colors.isSize(); i++) {
            if (colors.searchPos(i) == Color.lightGray){
                colors.delPos(i);
                colors.addEnd(color);
                break;
            } 
        }
    }
    
    public boolean addFile(File file){
        int[] tempLocations = new int[file.size];
        int blocksDone = 0;
        Color color = getColor();
        if (availableSpace >= file.size){
            for (int i = 0; i < size; i++) {
                if (blocks[i].getBackground() == Color.lightGray && blocksDone < file.size){
                    blocks[i].setBackground(color);
                    blocks[i].setText(String.valueOf(blocksDone));
                    tempLocations[blocksDone] = i;
                    blocksDone++;
                    availableSpace--;
                }
            }
            file.updateLocations(tempLocations);
            file.updateColor(color);
            return true;
        } else {
            return false;
        }
    }
    
    public void delFile(File file){
        int location = 0;
        for (int i = 0; i < size; i++) {
            if (location < file.locations.length && file.locations[location] == i){
                blocks[i].setBackground(Color.lightGray);
                blocks[i].setText("");
                location++;
                availableSpace++;
            }
        }
        file.updateColor(Color.lightGray);
    }
}
