/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import main.process;
import main.simulation;
import main.clock;
import primitivas.List;
import main.fcfs;
import main.roundrobin;
import main.spn;
import main.srt;
import main.hrrn;
import main.feedback;

/**
 *
 * @author RDG
 */
public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int numCPUs = 4;
        process process = new process();
        process.showInterface();
        simulation simulation = new simulation(numCPUs);
        simulation.showInterface();
        clock clock = new clock();
        clock.showInterface();
        
        List toDoList = new List();
        //toDoList = roundrobin.generateList(process.getProcess(),5);
        toDoList = roundrobin.generateList(process.getProcess(),5);
        
        while (true){
            if (clock.statusFR){
                String[] proceso = (String[]) toDoList.searchStart();
                String[] procesoNext = (String[]) toDoList.searchPos(1);
    //            for (int i = 0; i < toDoList.isSize(); i++) {
    //                String[] temp = (String[]) toDoList.searchPos(i);
    //                if (temp[7].equals("READY")){
    //                    for (int j = 0; j < numCPUs; j++) {
    //                        if (!simulation.getProcess(j, 1).equals(temp[0])){
    //                            simulation.setProcess(j, temp[0], 1);
    //                        }
    //                    }
    //                }
    //            }
                if (proceso[7].equals("READY")){
                    if (procesoNext != null && !procesoNext[0].equals(proceso[0])){
                        simulation.addReadyList(proceso[0]);
                        simulation.changeColorReadyList(simulation.readyList.length-1);
                        simulation.setProcess(0, "System", 1);
                    }
                }
                if (proceso[7].equals("BLOCKED")){
                    if (procesoNext != null && !procesoNext[0].equals(proceso[0])){
                        simulation.addBlockList(proceso[0]);
                        simulation.changeColorBlockList(simulation.blockList.length-1);
                        simulation.setProcess(0, "System", 1);
                    }
                }
                if (proceso[7].equals("DONE")){
                    if (procesoNext != null && !procesoNext[0].equals(proceso[0])){
                        simulation.addLongTermList(proceso[0]);
                        simulation.changeColorLongTermList(simulation.longTermList.length-1);
                        simulation.setProcess(0, "System", 1);
                    }
                }
                if (proceso[7].equals("RUNNING")){
                    if (!simulation.getProcess(0,1).equals(proceso[0])){
                        if (!simulation.getProcess(0,1).equals("System")){
                            simulation.addReadyList(simulation.getProcess(0,1));
                        }
                        simulation.setProcess(0, proceso[0], 1);
                    }
                    if (simulation.readyList != null &&  simulation.readyList.length != 0 && simulation.readyList[0].equals(proceso[0])){
                        simulation.delReadyList(0);
                    } else if (simulation.blockList != null &&  simulation.blockList.length != 0 && simulation.blockList[0].equals(proceso[0])){
                        simulation.delBlockList(0);
                    }
                }
    //            simulation.setProcess(0, proceso[0], 1);
    //            simulation.setProcess(0, proceso[0], 1);
    //            simulation.setProcess(0, proceso[0], 1);
                System.out.print(proceso[0]);
                System.out.println(proceso[7]);
    //            System.out.println(Arrays.toString(proceso));
                toDoList.delStart();
                if (procesoNext == null){ 
                    simulation.addLongTermList(proceso[0]);
                    simulation.changeColorLongTermList(simulation.longTermList.length-1);
                    simulation.setProcess(0, "System", 1);
                    break;
                }
            }
            TimeUnit.MILLISECONDS.sleep(clock.tiempo * 100);
//            System.out.println("FIN");
            
            
            
            
            
            
            
            
            
            
            
//            System.out.println(LocalTime.now());
//            System.out.println(toDoList.searchStart());
//            System.out.println(toDoList.isSize());
//            System.out.println("FIN");
//            toDoList.delStart();
            

            
        }
    }
    
}
