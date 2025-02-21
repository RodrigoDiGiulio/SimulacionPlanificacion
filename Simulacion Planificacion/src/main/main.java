/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;
//import java.time.LocalTime;
//import java.util.concurrent.TimeUnit;
//import main.process;
//import main.simulation;
//import main.clock;
import primitivas.Procesos;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import main.process;
import main.simulation;
import main.clock;
import primitivas.List;


public class main {


    public static void main(String[] args) throws InterruptedException{
        //Procesos proceso = new Procesos();
        //proceso.change_Nombre("hola");
        //proceso.start();
        //proceso.interrupt();

        Sistema proceso = new Sistema();
        proceso.iniciar();
    }
}
 

 
    //public static void main(String[] args) throws InterruptedException {
        //process process = new process();
        //process.showInterface();
        //simulation simulation = new simulation(5);
        //simulation.showInterface();
        //clock clock = new clock();
        //clock.showInterface();
        
        //while (true){
            //System.out.println("Hola");
            //TimeUnit.MILLISECONDS.sleep(clock.tiempo * 100);
            //System.out.println(LocalTime.now());
        //}
    //}
    /**
     * @param args the command line arguments
     */
    
//    public static void main(String[] args) throws InterruptedException {
//        List procesosCargados = new List();
//        
//        process process = new process();
//        process.showInterface();
//        simulation simulation = new simulation(4);
//        simulation.showInterface();
//        clock clock = new clock();
//        clock.showInterface();
//        
//        while (true){
//            procesosCargados = process.getProcesosCargados();
//            TimeUnit.MILLISECONDS.sleep(clock.tiempo * 100);
//            System.out.println("Hello World");
//            
//            // Acceder al tiemmpo Actual (Stadisticas)
//            System.out.println(LocalTime.now());
//            
//            // Saber que proceso esta en que CPU
//            System.out.println(simulation.getProcess(0, 0));
//            
//            // Obtener estatus del boton de play o pausa
//            System.out.println(clock.statusFR);
//            
            //Como acceder a cada parametro de un proceso
//            String[] a = (String[]) procesosCargados.searchPos(0);
//            System.out.println(a[0]);
//            System.out.println(a[1]);
//            System.out.println(a[2]);
//            System.out.println(a[3]);
//            System.out.println(a[4]);
//            System.out.println(a[5]);
            
//        }
//    }
    
//}
