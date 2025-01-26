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
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
    Checkbox cpuBoundCB;
    Checkbox ioBoundCB;
    JTextArea exceptionGeneration;
    JTextArea exceptionSatifaction;
    File file;
    List procesosCargados = new List();
    String[] listaDisplay;
    JList JListaDisplay;
    JButton search;
    Boolean searchFR;
    String fileNameGB;

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
        cpuBoundCB = new Checkbox("CPU Bound");
        cpuBound.add(cpuBoundCB);
        
        // I/O Bound
        JPanel ioBound = new JPanel();
        ioBoundCB = new Checkbox("I/O Bound");
        ioBound.add(ioBoundCB);
        
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
        JButton add = new JButton("Agregar Proceso");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cantidad de valores para un proceso
                String[] newProcess = new String[6];
                if (procesosCargados.isSize() == 0){
                    System.out.println("No hay donde agregar el proceso");
                } else {
                    if (name != null && !name.getText().equals("Nombre")) {
                        if (instNum != null && !instNum.getText().equals("Numero de Instrucciones")){
                            if (ioBoundCB.getState() == true){
                                if (exceptionGeneration != null && !exceptionGeneration.getText().equals("Generar")){
                                    if (exceptionSatifaction != null && !exceptionSatifaction.getText().equals("Satisfacer")){
                                        newProcess[0] = name.getText();
                                        newProcess[1] = instNum.getText();
                                        if (cpuBoundCB.getState()){
                                            newProcess[2] = "true";
                                        } else {
                                            newProcess[2] = "false";
                                        }
                                        newProcess[3] = "true";
                                        newProcess[4] = exceptionGeneration.getText();
                                        newProcess[5] = exceptionSatifaction.getText();
                                        procesosCargados.addEnd(newProcess);
                                        saveFile(fileNameGB);
                                        loadFile(fileNameGB);
                                    }
                                }
                            } else {
                                newProcess[0] = name.getText();
                                newProcess[1] = instNum.getText();
                                if (cpuBoundCB.getState()){
                                    newProcess[2] = "true";
                                } else {
                                    newProcess[2] = "false";
                                }
                                newProcess[3] = "false";
                                newProcess[4] = "null";
                                newProcess[5] = "null";
                                procesosCargados.addEnd(newProcess);
                                saveFile(fileNameGB);
                                loadFile(fileNameGB);
                            }
                        }
                    }
                }
            }
        });
        add.setAlignmentX(Component.CENTER_ALIGNMENT); 
        card.add(add);
        
        
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Procesos Cargados");
        label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        list.add(label);
        JListaDisplay = new JList();
        JListaDisplay.setAlignmentX(Component.CENTER_ALIGNMENT); 
        list.add(JListaDisplay); 
        search = new JButton("Cargar");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchFR = false;
        
        searchFile();
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchFR == true){
                    search.setText("Cargar");
                    searchFile();
                    searchFR = !searchFR;
                }else{
                    if (JListaDisplay.getSelectedValue() != null){
                        String selectedItem = JListaDisplay.getSelectedValue().toString();
                        search.setText("Buscar");
                        loadFile(selectedItem);
                        searchFR = !searchFR;
                    }
                }
            }
        });
        list.add(search);
        
        page.setLayout(new BoxLayout(page, BoxLayout.X_AXIS));
        page.add(card);    
        page.add(list);
        
        frame = new JFrame("Procesos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(page);
        
//        loadFile("Prueba.txt");
//        saveFile("Prueba2.txt");
    }

    public void loadFile(String fileName){
        fileNameGB = fileName;
        procesosCargados.empty();
        try {
            file = new File(System.getProperty("user.dir") + "/src/procesos/" + fileName);
            String filePath = System.getProperty("user.dir") + "/src/procesos/" + fileName;
            Scanner myReader = new Scanner(file);
//            long lines = Files.lines(Paths.get(filePath)).count();
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                // Cantidad de valores para un proceso
                String[] process = new String[6];
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
        listaDisplay = new String[procesosCargados.isSize()];
        for (int i = 0; i < procesosCargados.isSize(); i++) {
            String[] temp = (String[]) procesosCargados.searchPos(i);
            System.out.println(temp[1]);
            listaDisplay[i] = temp[1];
        }
        JListaDisplay.setListData(listaDisplay);
    }
    
    public void saveFile(String fileName){
        fileNameGB = fileName;
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
        procesosCargados.empty();
        String directoryPath = System.getProperty("user.dir") + "/src/procesos/";
        Path directory = Paths.get(directoryPath);
        List tempList = new List();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) { 
            for (Path file : stream) {
                tempList.addEnd(file.getFileName().toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(process.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listaDisplay = new String[tempList.isSize()];
        for (int i = 0; i < tempList.isSize(); i++) {
            String temp = (String) tempList.searchPos(i);
            System.out.println(temp);
            listaDisplay[i] = temp;
        }
        JListaDisplay.setListData(listaDisplay);
    }
    
    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}