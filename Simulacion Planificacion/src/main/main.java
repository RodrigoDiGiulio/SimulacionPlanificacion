/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import main.process;
import main.simulation;
import main.clock;

/**
 *
 * @author RDG
 */
public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        process process = new process();
        process.showInterface();
        simulation simulation = new simulation(16);
        simulation.showInterface();
        clock clock = new clock();
        clock.showInterface();
        
        while (true){
            System.out.println("Hola");
            TimeUnit.MILLISECONDS.sleep(clock.tiempo * 100);
            System.out.println(LocalTime.now());
        }
    }
    
}
