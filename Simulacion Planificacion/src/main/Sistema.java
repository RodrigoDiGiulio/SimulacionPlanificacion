/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


//clase principal que ordena y gestiona a las cpus y sus procesos
package main;

import java.util.concurrent.TimeUnit;
import primitivas.Procesos;
import primitivas.CycleList;
import primitivas.List;
import primitivas.Stack;
import primitivas.Cpu;

public class Sistema {
  int ciclos = 0;
  int numero_cpus;
  int numero_proceso = 0;
  int numero_proceso_cpu = 2;
  int Planifiacion_vigente = 0;
  List lista_cpu = new List();
  List lista_ready = new List();
  List lista_bloqueados = new List();
  List lista_largo_plazo = new List();

    public void setNumero_cpus(int numero_cpus) {
        this.numero_cpus = numero_cpus;
    }

    public int getNumero_proceso_cpu() {
        return numero_proceso_cpu;
    }

    public void setNumero_proceso(int numero_proceso) {
        this.numero_proceso = numero_proceso;
    }

    public void setLista_cpu(List lista_cpu) {
        this.lista_cpu = lista_cpu;
    }

    public List getLista_ready() {
        return lista_ready;
    }

    public void setLista_ready(List lista_ready) {
        this.lista_ready = lista_ready;
    }
   
    public void setLista_bloqueados(List lista_bloqueados) {
        this.lista_bloqueados = lista_bloqueados;
    }
  
    
 //metodo que crea un proceso entrada/salida con todos los datos requeridos   
  public void crear_proceso_IO(String nombre, int excepcion, int satisface){
      Procesos proceso = new Procesos();
      proceso.change__Id(numero_proceso);
      proceso.change_Nombre(nombre);
      proceso.change_Entrada_Salida(true);
      proceso.cambiar_satisface(satisface);
      proceso.cambiar_make_exception(excepcion);
      if(proceso.Validar_Nombre(nombre)){
        lista_ready.addEnd(proceso);  
      }else{
          System.out.println("error: falta un nombre"); 
      }
  }
 
  
 //metodo para crear proceso exigentes en cpu 
  public void crear_proceso_Cpu(String nombre){
 Procesos proceso = new Procesos();
 proceso.change__Id(numero_proceso);
 proceso.change_Nombre(nombre);
 proceso.change_Cpu_bound(true);
 if(proceso.Validar_Nombre(nombre)){
     lista_ready.addEnd(proceso);  
     }else{
          System.out.println("error: falta un nombre"); 
      }
}

  
//metodo para crear las cpus  
public void crear_cpus(int id){
  Cpu cpu = new Cpu();
 cpu.setId(id);
 cpu.setNumero_procesos(2);
 lista_cpu.addEnd(cpu);   
}


//metodo que arranca el sistema operativo
public void iniciar() throws InterruptedException{
 setNumero_cpus(5);
 setNumero_proceso(15);
int l =0;
int p = 1;
 for(int i=0; i < numero_cpus;i++){
 //System.out.println("el valor del primer i es"+i);
 crear_cpus(i);
  }
 for (int i = 0; i< numero_proceso; i++){
 //System.out.println("el valor del segundo i es"+i);
 int k = 2;
 crear_proceso_Cpu("proceso"+ l);
 crear_proceso_IO("proceso" +p, k, k++);
 l = l+2;
 p = p+2;
 k++;
 }
 setLista_ready(lista_ready);
    //System.out.println("primera lista de ready");
    //lista_ready.print();
 FCFS();
 //lista_cpu.print();
  }


 //primer forma de planificacion que nos piden 
 public void FCFS() throws InterruptedException{
  int a = 0;
  int b = getNumero_proceso_cpu();
  int n = 0;
     //System.out.println("verificacion de data");
     //lista_ready.print();
     //System.out.println("el tamano de la lista es"+ lista_cpu.isSize());
 for (int i=0; i < lista_cpu.isSize(); i++){
      int h = 0;
     //System.out.println("el valor de i es "+i);
     Object c = lista_cpu.search(i);
     Cpu d = Cpu.class.cast(c);
     while (h < b){
      Object g = lista_ready.search(a);
      Procesos e = Procesos.class.cast(g);
      //System.out.println(e.get_Nombre());
      d.getProcesos().llenar(e);
      h++;
      a++;
     }
    }
     //System.out.println("cabeza");
     //Object prueba = lista_ready.getFirst().getElement();
     //Procesos prueba1 =Procesos.class.cast(prueba);
     //System.out.println(prueba1.get_Nombre());
     //System.out.println("siguiente");
     //Object prueba2 = lista_ready.getFirst().getNext().getElement();
     //Procesos prueba3 =Procesos.class.cast(prueba2);
     //System.out.println(prueba3);
     while (n < numero_proceso_cpu * numero_cpus){
         //System.out.println("el valor de n es"+n);
         //lista_ready.print();
     lista_ready.deleteBegin();
      n++;
     }
   iniciar_nuevo_ciclo();
 }

 
 //metodo que comienza un nuevo ciclo
 public void iniciar_nuevo_ciclo() throws InterruptedException{
     System.out.println("ciclo "+ciclos);
       for (int i=0; i < lista_cpu.isSize(); i++){
      //System.out.println("valode de i:"+i);
     Object c = lista_cpu.search(i);
     Cpu d = Cpu.class.cast(c);
      //System.out.println("cpu"+0);
      //d.getProcesos().print();
      d.arrancar(numero_proceso_cpu);
      Excepcion();
     }
  ciclos++;
 Para_ciclo_actual();
 }
 
 
 //metodo que intenta parar los threads actuales (no funciona, actualmente no hace nada)
 public void Para_ciclo_actual () throws InterruptedException{
     for (int i=0; i < lista_cpu.isSize(); i++){
      //System.out.println("valode de i:"+i);
     Object c = lista_cpu.search(i);
     Cpu d = Cpu.class.cast(c);
      //System.out.println("cpu"+0);
      //d.getProcesos().print();
      d.para();
      Excepcion();
     }
   vaciar(lista_cpu.isSize(), 0);
 }
 
 
 //metodo que su titulo explica todo
 public void Verificar_Politica_Planificacion() throws InterruptedException{
     int a  = 0;
     if(a == Planifiacion_vigente){
        FCFS();
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
         System.out.println("la planificacion 2 es Round Robin");
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
       System.out.println("la planificacion 3 es SPN");
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
        System.out.println("la planificacion 4 es SRT");
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
       System.out.println("la planificacion 5 es Round HRRN");
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
        System.out.println("la planificacion 6 es Feedback");
     }else{
       a++;  
     }
 }
 
 
 //metodo que vacia las cpus y agrega los procesos que estaban ahi a la lista de listos
 public void vaciar(int numero_cpus, int r) throws InterruptedException{
     //System.out.println(" Primer antes");
     //lista_ready.print();
     for (int i=0; i < numero_cpus; i++){
     int a =0;
     int b = 0;
     Object c = lista_cpu.search(r);
     Cpu d = Cpu.class.cast(c);
     while (a < numero_proceso_cpu){
      Object g = d.getProcesos().search(a);
      Procesos e = Procesos.class.cast(g);
      lista_ready.addEnd(e);
      a++;
     }
     //System.out.println("antes");
     //lista_ready.print();
     while (b < numero_proceso_cpu){
      d.getProcesos().deleteBegin();
      b++;
     }
     r++;
    }
     //System.out.println("despues");
     //lista_ready.print();
   Verificar_Politica_Planificacion(); 
 } 
 
 
 //excepcion que se usa para ordenar los procesos cuando se inician
 public void Excepcion() throws InterruptedException {
  TimeUnit.MILLISECONDS.sleep(  100);   
 }
 
}
  



