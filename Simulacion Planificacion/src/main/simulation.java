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
    //Datos de PCB valores de registros, ID y nombre del proceso
    // INstruccion en Program Counter
    // Alternar politicas de planificacion
    JPanel cards;
    JFrame frame;
    CardLayout cardLayout;
    JTextArea name;
    JTextArea instNum;
    JTextArea exceptionGeneration;
    JTextArea exceptionSatifaction;
    JList cpus[];
    JList readyJList;
    JList blockJList;
    JList longTermJList;
    String processes[];
    String cpuNames[];
    String readyList[];
    String blockList[];
    String longTermList[];
    int threads = 2;
    
    public simulation(int cpuNumber) {
        JPanel page = new JPanel();
        JPanel cpuSide = new JPanel();
        JPanel readySide = new JPanel();
        JPanel blockSide = new JPanel();
        JPanel longTermSide = new JPanel();
        
        // Crea las lines con 4 procesadores por linea
        JPanel lines[] = new JPanel[cpuNumber/4];
        for (int i = 0; i < (cpuNumber/4); i++) {
            lines[i] = new JPanel();
        }
        
        // Crea la lista con el nombre del CPU y el Proceso
        processes = new String[cpuNumber];
        cpuNames = new String[cpuNumber];
        cpus = new JList[cpuNumber];
        for (int i = 0; i < cpuNumber; i++) {
            cpuNames[i] = "CPU " + i;
            processes[i] = "Process of CPU " + i;
            String[] temp = new String[threads+1];
            temp[0] = cpuNames[i];
            for (int j = 0; j < threads; j++) {
                temp[j+1] = processes[i];
            }
            cpus[i] = new JList(temp);
        }
        
        
        // Agregar todos los cpu
        cpuSide.setLayout(new BoxLayout(cpuSide, BoxLayout.Y_AXIS));
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < 4; j++) {
                lines[i].add(cpus[j + (i*4)]);
            }
            cpuSide.add(lines[i]);
        }
        
        // Agregar todos listos
        readySide.setLayout(new BoxLayout(readySide, BoxLayout.Y_AXIS));
        JLabel listosLabel = new JLabel("Listos");
        listosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        readySide.add(listosLabel); 
        readyJList = new JList();
        readySide.add(readyJList);
        
        // Agregar todos los bloqueados
        blockSide.setLayout(new BoxLayout(blockSide, BoxLayout.Y_AXIS));
        JLabel bloqueadosLabel = new JLabel("Bloqueados");
        bloqueadosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        blockSide.add(bloqueadosLabel); 
        blockJList = new JList();
        blockSide.add(blockJList);
        
        // Agregar todos los de largo plazo
        longTermSide.setLayout(new BoxLayout(longTermSide, BoxLayout.Y_AXIS));
        JLabel largoPlazoLabel = new JLabel("Largo Plazo");
        largoPlazoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        longTermSide.add(largoPlazoLabel); 
        longTermJList = new JList();
        longTermSide.add(longTermJList);

        // Agregar todas las partes para la simulacion
        page.add(cpuSide);
        page.add(readySide);
        page.add(blockSide);
        page.add(longTermSide);
        
        frame = new JFrame("Simulacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(page);
        
        addReadyList("Vacio");
        addBlockList("Vacio");
        addLongTermList("Vacio");
    }

    public void setProcess(int cpu, String process, int thread){
        String[] data = new String[threads + 1];
        for (int i = 0; i < (threads + 1); i++) {
            data[i] = cpus[cpu].getModel().getElementAt(i).toString();
        }
        data[thread] = process;
        cpus[cpu].setListData(data);
        cpus[cpu].repaint();
    }
    
    public void addReadyList(String process){
        if (readyList == null){
            readyList = new String[1];
            readyList[0] = process;
        }else{
            String[] data = new String[readyList.length + 1];
            for (int i = 0; i < readyList.length; i++) {
                data[i] = readyList[i];
            }
            data[readyList.length] = process;
            readyList = data;
        }
        readyJList.setListData(readyList);
        readyJList.repaint();
    }
    
    public String delReadyList(int processPos){
        String result = "Error";
        if (readyList == null){
            result = "Empty";
        }else{
            String[] data = new String[readyList.length - 1];
            for (int i = 0; i < readyList.length; i++) {
                if (i == processPos){
                    result = readyList[i];
                }else{
                    data[i] = readyList[i];
                }
            }
            readyList = data;
        }
        if (readyList.length == 0){
            readyList = new String[1];
            readyList[0] = "Vacio";
        }
        readyJList.setListData(readyList);
        readyJList.repaint();
        return result;
    }
    public void addBlockList(String process){
        if (blockList == null){
            blockList = new String[1];
            blockList[0] = process;
        }else{
            String[] data = new String[blockList.length + 1];
            for (int i = 0; i < blockList.length; i++) {
                data[i] = blockList[i];
            }
            data[blockList.length] = process;
            blockList = data;
        }
        blockJList.setListData(blockList);
        blockJList.repaint();
    }
    
    public String delBlockList(int processPos){
        String result = "Error";
        if (blockList == null){
            result = "Empty";
        }else{
            String[] data = new String[blockList.length - 1];
            for (int i = 0; i < blockList.length; i++) {
                if (i == processPos){
                    result = blockList[i];
                }else{
                    data[i] = blockList[i];
                }
            }
            blockList = data;
        }
        if (blockList.length == 0){
            blockList = new String[1];
            blockList[0] = "Vacio";
        }
        blockJList.setListData(blockList);
        blockJList.repaint();
        return result;
    }
    public void addLongTermList(String process){
        System.out.println("Me llamaron");
        if (longTermList == null){
            longTermList = new String[1];
            longTermList[0] = process;
        }else{
            String[] data = new String[longTermList.length + 1];
            for (int i = 0; i < longTermList.length; i++) {
                data[i] = longTermList[i];
            }
            data[longTermList.length] = process;
            longTermList = data;
        }
        longTermJList.setListData(longTermList);
        longTermJList.repaint();
    }
    
    public String delLongTermList(int processPos){
        String result = "Error";
        if (longTermList == null){
            result = "Empty";
        }else{
            String[] data = new String[longTermList.length - 1];
            for (int i = 0; i < longTermList.length; i++) {
                if (i == processPos){
                    result = longTermList[i];
                }else{
                    data[i] = longTermList[i];
                }
            }
            longTermList = data;
        }
        if (longTermList.length == 0){
            longTermList = new String[1];
            longTermList[0] = "Vacio";
        }
        longTermJList.setListData(longTermList);
        longTermJList.repaint();
        return result;
    }
    
    public void changeColorProcess(int cpu){
        if (cpus[cpu] != null){
            cpus[cpu].setSelectedIndex(1);
        }
        cpus[cpu].repaint();
    }
    
    public void changeColorBlockList(int processPos){
        if (blockJList != null){
            blockJList.setSelectedIndex(processPos);
        }
        blockJList.repaint();
    }
    
    public void changeColorLongTermList(int processPos){
        if (longTermJList != null){
            longTermJList.setSelectedIndex(processPos);
        }
        longTermJList.repaint();
    }
        
    public void changeColorReadyList(int processPos){
        if (readyJList != null){
            readyJList.setSelectedIndex(processPos);
        }
        readyJList.repaint();
    }
    
    public void showInterface() {
        frame.pack();
        frame.setVisible(true);
    }
}