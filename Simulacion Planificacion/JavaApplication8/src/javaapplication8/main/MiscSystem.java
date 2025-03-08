/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javaapplication8.primitivas.File;
import javaapplication8.primitivas.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author RDG
 */
public class MiscSystem {
    public JFrame frame;
    public JButton save;
    public JButton load;
    public JTextArea log;
    public String logLocation;
    public String logData;
    public String stateLocation;
    public String stateData;
    
    public MiscSystem(){
        frame = new JFrame("Misc System");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        
        save = new JButton("Guardar");
        buttons.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveState();
            }
        });
        
        load = new JButton("Cargar");
        buttons.add(load);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadState();
            }
        });
        
        panel.add(new JPanel());
        panel.add(buttons);
        log = new JTextArea();
        log.setEditable(false);
        panel.add(log);
        
        frame.add(panel);
        frame.setSize(400, 800);
        frame.setLocation(800, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        updateLog("MiscSystem started Correctly");
    }
    
    public void dispose(){
        frame.dispose();
    }
    
    public void updateLog(String newData){
        String prevData = log.getText();
        if (prevData.equals("")){
            log.setText(prevData + newData);
        } else {
            log.setText(prevData + "\n" + newData);
        }
        
    }
    
    public void saveState(){
        String filePath = System.getProperty("user.dir") + "\\src\\javaapplication8\\data\\stateSaved.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < main.files.isSize(); i++) {
                File tempFile = (File) main.files.searchPos(i);
                writer.write(tempFile.name + "|" + tempFile.size + "|");
                writer.write(tempFile.color.getRed() + "," + tempFile.color.getGreen() + "," + tempFile.color.getBlue() + "|");
                for (int j = 0; j < tempFile.locations.length; j++) {
                    writer.write(String.valueOf(tempFile.locations[j]));
                    if (j < tempFile.locations.length-1){
                        writer.write(",");
                    }
                }
                writer.write("|" + tempFile.level + "|" + tempFile.parent);
                writer.newLine();
            }
            writer.write("Log|");
            writer.newLine();
            writer.write(log.getText());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: "+ e.getMessage());
        }
    }
    
    public void loadState(){
        String filePath = System.getProperty("user.dir") + "\\src\\javaapplication8\\data\\stateSaved.txt";
        boolean isLog = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            main.blockDisplay.dispose();

            main.tableDisplay.dispose();
            main.blockDisplay = new BlockDisplay(main.Storage);
            main.tableDisplay = new TableDisplay();
            main.files.empty();
            while ((line = reader.readLine()) != null) {
                if (!isLog){
                    if (line.equals("Log|")){
                       isLog = true;
                       log.setText("");
                    } else {
                        int barCount = 0;
                        String tempName = "";
                        String tempSize = "";
                        String tempColor = "";
                        String tempLocation = "";
                        String tempLevel = "";
                        String tempParent = "";
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == '|'){
                                barCount++;
                            } else {
                                switch(barCount) {
                                    case 0 -> {
                                        tempName = tempName + line.charAt(i);
                                    }
                                    case 1 -> {
                                        tempSize = tempSize + line.charAt(i);
                                    }
                                    case 2 -> {
                                        tempColor = tempColor + line.charAt(i);
                                    }
                                    case 3 -> {
                                        tempLocation = tempLocation + line.charAt(i);
                                    }
                                    case 4 -> {
                                        tempLevel = tempLevel + line.charAt(i);
                                    }
                                    case 5 -> {
                                        tempParent = tempParent + line.charAt(i);
                                    }
                                }
                            }
                        }
                        File tempFile = new File();
                        tempFile.name = tempName;
                        tempFile.size = Integer.parseInt(tempSize);
                        String tempRed = "";
                        String tempGreen = "";
                        String tempBlue = "";
                        int rgbIndex = 0;
                        for (int i = 0; i < tempColor.length(); i++) {
                            if (tempColor.charAt(i) == ','){
                                rgbIndex++;
                            } else { 
                                switch(rgbIndex) {
                                    case 0 -> {
                                        tempRed = tempRed + tempColor.charAt(i);
                                    }
                                    case 1 -> {
                                        tempGreen = tempGreen + tempColor.charAt(i);
                                    }
                                    case 2 -> {
                                        tempBlue = tempBlue + tempColor.charAt(i);
                                    }
                                }
                            }
                        }
                        tempFile.color = new Color(Integer.parseInt(tempRed),Integer.parseInt(tempGreen),Integer.parseInt(tempBlue));
                        int locationIndex = 0;
                        List tempLocations = new List();
                        String tempLocationValue = "";
                        for (int i = 0; i < tempLocation.length(); i++) {
                            if (tempLocation.charAt(i) == ','){
                                locationIndex++;
                                tempLocations.addEnd(Integer.valueOf(tempLocationValue));
                                tempLocationValue = "";
                            } else {
                                tempLocationValue = tempLocationValue + tempLocation.charAt(i);
                            }
                        }
                        int[] realLocations = new int[tempLocations.isSize()];
                        for (int i = 0; i < tempLocations.isSize(); i++) {
                            realLocations[i] = (int) tempLocations.searchPos(i);
                        }
                        tempFile.locations = realLocations;
                        tempFile.level = Integer.parseInt(tempLevel);
                        tempFile.parent = tempParent;
                        main.blockDisplay.addFile(tempFile);
                        if (tempFile.parent == null){
                        } else {
                            List folderLadder = new List();
                            int ladderLevel = 0;
                            String tempLadder = "";
                            for (int i = 0; i < tempParent.length(); i++) {
                                if (tempParent.charAt(i) == ','){
                                    ladderLevel++;
                                    folderLadder.addStart(tempLadder);
                                    tempLadder = "";
                                } else {
                                    tempLadder = tempLadder + tempParent.charAt(i);
                                }
                            }
                            for (int i = 0; i < folderLadder.isSize()-1; i++) {
                                File folderFile = new File();
                                folderFile.name = (String) folderLadder.searchPos(i+1);
                                System.out.println("FUUUCK"+(String) folderLadder.searchPos(i));
                               
                            }
                        
                        }
                        main.tableDisplay.addFile(tempFile);
                        main.files.addEnd(tempFile);
                    }
                } else {
                    updateLog(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
