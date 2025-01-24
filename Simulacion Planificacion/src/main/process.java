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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import primitivas.List;

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
    File file;
    List procesosCargados = new List();

    public process() {
        JPanel page = new JPanel();
        JPanel card = new JPanel();
        JPanel list = new JPanel();

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
        
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        list.add(new JLabel("Procesos Cargados")); 
        list.add(new JList());
        
        page.setLayout(new BoxLayout(page, BoxLayout.X_AXIS));
        page.add(card);    
        page.add(list);
        
        frame = new JFrame("Procesos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(page);
        
        loadFile("Prueba.txt");
        saveFile("Prueba2.txt");
    }

    public void loadFile(String fileName){    
        try {
            file = new File(System.getProperty("user.dir") + "/src/procesos/" + fileName);
            String filePath = System.getProperty("user.dir") + "/src/procesos/" + fileName;
            Scanner myReader = new Scanner(file);
//            long lines = Files.lines(Paths.get(filePath)).count();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] process = new String[2];
                String word = "";
                int pos = 0;
                for (char letter : line.toCharArray()){
                    if (letter == ',') {
                        process[pos] = word;
                        pos++;
                        word = "";
                    } else {
                        word = word + letter;
                    }
                }
                process[pos] = word;
                procesosCargados.addEnd(process);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void saveFile(String fileName){
        try {
            file = new File(System.getProperty("user.dir") + "/src/procesos/" + fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir") + "/src/procesos/" + fileName);
            String data = "";
            for (int i = 0; i < procesosCargados.isSize(); i++) {
                String[] array = (String[]) procesosCargados.searchPos(i);
                for (int j = 0; j < array.length; j++) {
                    if (j < array.length - 1){
                        data = data + array[j] + ',';
                    } else {
                        data = data + array[j] + '\n';
                    }
                }
            }
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void searchFile(){
        
    }
    
    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}