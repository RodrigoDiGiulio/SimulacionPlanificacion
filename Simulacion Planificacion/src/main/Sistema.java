/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//NetBeans

//clase principal que ordena y gestiona a las cpus y sus procesos
package main;

import java.util.concurrent.TimeUnit;
import primitivas.Procesos;
import primitivas.CycleList;
import primitivas.List;
import primitivas.Stack;
import primitivas.Cpu;
import main.process;
import main.simulation;
import main.clock;

public class Sistema {
  int ciclos = 0;
  int numero_cpus;
  int numero_proceso = 0;
  int numero_proceso_cpu = 1;
  int Planifiacion_vigente = 0;
  boolean no_SPN = true;
  boolean no_HRRN = true;
  List lista_cpu = new List();
  List lista_ready = new List();
  List lista_bloqueados = new List();
  List lista_largo_plazo = new List();
  simulation sim;
  clock clock;
  process fman;

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
        String[] temp = new String[lista_ready.isSize()];
        for (int i = 0; i < lista_ready.isSize(); i++) {
            Procesos tempP = (Procesos) lista_ready.searchPos(i);
            temp[i] = tempP.get_Nombre();
        }
        sim.readyList = temp;
        sim.updateReady();
    }
   
    public void setLista_bloqueados(List lista_bloqueados) {
        this.lista_bloqueados = lista_bloqueados;
        String[] temp = new String[lista_bloqueados.isSize()];
        for (int i = 0; i < lista_bloqueados.isSize(); i++) {
            Procesos tempP = (Procesos) lista_bloqueados.searchPos(i);
            temp[i] = tempP.get_Nombre();
        }
        sim.blockList = temp;
        sim.updateBlock();
    }
  
    
 //metodo que crea un proceso entrada/salida con todos los datos requeridos   
  public void crear_proceso_IO(String nombre, int excepcion, int satisface){
      Procesos proceso = new Procesos();
      proceso.change__Id(numero_proceso);
      proceso.change_Nombre(nombre);
      proceso.change_Entrada_Salida(true);
      proceso.cambiar_satisface(satisface);
      int a = (int)Math.floor(Math.random()*10+1);
      proceso.setCierre(satisface+a);
      proceso.cambiar_make_exception(excepcion);
      if(proceso.Validar_Nombre(nombre)){
        lista_ready.addEnd(proceso);
        setLista_ready(lista_ready);
        
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
     setLista_ready(lista_ready);
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
 setNumero_cpus(4);
 setNumero_proceso(15);
 sim = new simulation(numero_cpus);
 sim.showInterface();
 clock = new clock();
 clock.showInterface();
 fman = new process();
 fman.showInterface();
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
    Verificar_Politica_Planificacion();
 //FCFS();
 //SPN();
 //SRT();
 //HRRN();
 //Feedback();
 //lista_cpu.print();
  }


 //primer forma de planificacion que nos piden 
 public void FCFS() throws InterruptedException{
  int a = 0;
  int b = getNumero_proceso_cpu();
  int n = 0;
 for (int i=0; i < lista_cpu.isSize(); i++){
      int h = 0;
     Object c = lista_cpu.search(i);
     Cpu d = Cpu.class.cast(c);
     while (h < b){
      Object g = lista_ready.search(a);
      Procesos e = Procesos.class.cast(g);
      d.getProcesos().llenar(e);
      h++;
      a++;
     }
    }
     while (n < numero_proceso_cpu * numero_cpus){
         //System.out.println("el valor de n es"+n);
         //lista_ready.print();
         lista_ready.deleteBegin();
     setLista_ready(lista_ready);
      n++;
     }
   iniciar_nuevo_ciclo();
 }

 //metodo de planificacion round robin el segundo que nos piden, por las especificaciones
 //del projecto es igual al FCFS
 public void Round_Robin() throws InterruptedException{
  int a = 0;
  int b = getNumero_proceso_cpu();
  int n = 0;
 for (int i=0; i < lista_cpu.isSize(); i++){
      int h = 0;
     Object c = lista_cpu.search(i);
     Cpu d = Cpu.class.cast(c);
     while (h < b){
      Object g = lista_ready.search(a);
      Procesos e = Procesos.class.cast(g);
      d.getProcesos().llenar(e);
      h++;
      a++;
     }
    }
     while (n < numero_proceso_cpu * numero_cpus){
         //System.out.println("el valor de n es"+n);
         //lista_ready.print();
          lista_ready.deleteBegin();
          setLista_ready(lista_ready);
      n++;
     }
   iniciar_nuevo_ciclo();    
 }
 
 //Tercera politica de planificacion, agarra los de menor tiempo disponibles
 public void SPN() throws InterruptedException{
  if(no_SPN){
   List nueva = new List();
   int b = 0;
   int c = numero_proceso*2;
   int d = lista_ready.isSize();
   while(b< c-2){            
     int a =0;
     Object g = lista_ready.search(0);
     Procesos e = Procesos.class.cast(g);
    for (int i = 0; i< d-1; i++){
      Object h = lista_ready.search(i);
      Procesos f = Procesos.class.cast(h);
      if(e.getTiempo()> f.getTiempo()){
         e = f; 
         a=i; 
      }
      if(e.getTiempo() == f.getTiempo() && e.get_Id() > f.get_Id()){
          e = f;
         a=i; 
      }
    }
     Object v = lista_ready.search(a);
     Procesos n = Procesos.class.cast(v);
     nueva.addEnd(n);
     lista_ready.Borrar_especifico(v);
     setLista_ready(lista_ready);
     d--;
     b++;
   }
     Object v = lista_ready.search(0);
     if(lista_ready.search(1) != null){
         Object h = lista_ready.search(1);
         Procesos f = Procesos.class.cast(h);
         nueva.addEnd(f);
         lista_ready.deleteBegin();
         setLista_ready(lista_ready);
     }
     Procesos n = Procesos.class.cast(v);
     nueva.addEnd(n);
     lista_ready.Borrar_especifico(v);
     setLista_ready(lista_ready);
     setLista_ready(nueva);
  }
  no_SPN= false;
  FCFS();
 }
 
 //Cuarta politica de planificacion agarra los que les queda menor tiempo para
 //terminarse
 public void SRT() throws InterruptedException{
        List nueva = new List();
   int b = 0;
   int c = numero_proceso*2;
   int d = lista_ready.isSize();
   while(b< c-2){            
     int a =0;
     Object g = lista_ready.search(0);
     Procesos e = Procesos.class.cast(g);
    for (int i = 0; i< d-1; i++){
      Object h = lista_ready.search(i);
      Procesos f = Procesos.class.cast(h);
      if(e.getTiempo() > f.getTiempo()){
         e = f; 
         a=i; 
      }
      if(e.getTiempo() == f.getTiempo() && e.get_Id() > f.get_Id()){
         e = f; 
         a=i; 
      }
    }
     Object v = lista_ready.search(a);
     Procesos n = Procesos.class.cast(v);
     nueva.addEnd(n);
     lista_ready.Borrar_especifico(v);
     setLista_ready(lista_ready);
     d--;
     b++;
   }
     Object g = lista_ready.search(0);
     if(lista_ready.search(1) != null){
         Object h = lista_ready.search(1);
         Procesos f = Procesos.class.cast(h);
         nueva.addEnd(f);
         lista_ready.deleteBegin();
         setLista_ready(lista_ready);
     }
     Procesos e = Procesos.class.cast(g);
     nueva.addEnd(e);
     lista_ready.Borrar_especifico(g);
     setLista_ready(lista_ready);
     setLista_ready(nueva);
     for (int i=0; i < lista_ready.isSize(); i++){
         Object z = lista_ready.search(i);
         Procesos v = Procesos.class.cast(z);
         if(v.getTiempo() > 5){
            int p = v.getTiempo();
            v.setTiempo(p-5); 
         }
     }
       FCFS();
 }
 
 //quinta politica de planifcacion agarra los que tenga el mas
 //alto ratio de respuesta
 public void HRRN() throws InterruptedException{
   if(no_HRRN){
   List nueva = new List();
   int b = 0;
   int c = numero_proceso*2;
   int d = lista_ready.isSize();
   int z=ciclos*5;
   while(b< c-2){            
     int a =0;
      Object g = lista_ready.search(0);
      Procesos e = Procesos.class.cast(g);
      int q = z +e.getTiempo();
      int t1 = q/e.getTiempo();
    for (int i = 0; i< d-1; i++){
      Object h = lista_ready.search(i);
      Procesos f = Procesos.class.cast(h);
      int w = z + f.getTiempo();
      int t2 = w/f.getTiempo();
      if(t1 < t2){
         e = f; 
         a=i; 
      }
      if(t1 == t2 && e.get_Id() > f.get_Id()){
         e = f; 
         a=i; 
      }
    }
     Object v = lista_ready.search(a);
     Procesos n = Procesos.class.cast(v);
     nueva.addEnd(n);
     lista_ready.Borrar_especifico(v);
     setLista_ready(lista_ready);
     d--;
     b++;
     z = z+5;
   }
     Object g = lista_ready.search(0);
     if(lista_ready.search(1) != null){
         Object h = lista_ready.search(1);
         Procesos f = Procesos.class.cast(h);
         nueva.addEnd(f);
         lista_ready.deleteBegin();
         setLista_ready(lista_ready);
     }
     Procesos e = Procesos.class.cast(g);
     nueva.addEnd(e);
     lista_ready.Borrar_especifico(g);
     setLista_ready(lista_ready);
     setLista_ready(nueva);
  }
  no_HRRN= false;
  FCFS();

 }
 
 
 //Sexta politica de planificacion, esta es por prioridades
 public void Feedback() throws InterruptedException{
  List nueva = new List();
   int b = 0;
   int c = numero_proceso*2;
   int d = lista_ready.isSize();
   while(b< c-2){            
     int a =0;
     Object g = lista_ready.search(0);
     Procesos e = Procesos.class.cast(g);
    for (int i = 0; i< d-1; i++){
      Object h = lista_ready.search(i);
      Procesos f = Procesos.class.cast(h);
      if(e.getPrioridad()> f.getPrioridad()){
         e = f; 
         a=i; 
      }
      if(e.getPrioridad()== f.getPrioridad()&& e.get_Id() > f.get_Id()){
          e = f;
         a=i; 
      }
    }
     Object v = lista_ready.search(a);
     Procesos n = Procesos.class.cast(v);
     nueva.addEnd(n);
     lista_ready.Borrar_especifico(v);
     setLista_ready(lista_ready);
     d--;
     b++;
   }
     Object v = lista_ready.search(0);
     if(lista_ready.search(1) != null){
         Object h = lista_ready.search(1);
         Procesos f = Procesos.class.cast(h);
         nueva.addEnd(f);
         lista_ready.deleteBegin();
         setLista_ready(lista_ready);
     }
     Procesos n = Procesos.class.cast(v);
     nueva.addEnd(n);
     lista_ready.Borrar_especifico(v);
     setLista_ready(lista_ready);
     setLista_ready(nueva);
     FCFS();   
 }
 
 //metodo que comienza un nuevo ciclo
 public void iniciar_nuevo_ciclo() throws InterruptedException{
     //System.out.println("posicion 1"+lista_ready.isSize());
     boolean leave = false;
     while (!leave){ 
         TimeUnit.MILLISECONDS.sleep(clock.tiempo * 100);
        if (clock.statusFR){
            setLista_ready(lista_ready);
                System.out.println("ciclo "+ciclos);
                  for (int i=0; i < lista_cpu.isSize(); i++){
                 //System.out.println("valode de i:"+i);
                Object c = lista_cpu.search(i);
                Cpu d = Cpu.class.cast(c);
                 //System.out.println("cpu"+0);
                 //d.getProcesos().print();

                 //d.arrancar(numero_proceso_cpu);
                 System.out.println("lista de los threads que estan en la cpu "+i);
                 d.arrancar(numero_proceso_cpu);
                 d.getProcesos().print();
                 Excepcion();
                }
             ciclos++;
             for (int i=0; i < numero_cpus; i++){
                int a =0;
                int b = 0;
                Object c = lista_cpu.search(i);
                Cpu d = Cpu.class.cast(c);
                while (a < numero_proceso_cpu){
                 Object g = d.getProcesos().search(a);
                 Procesos e = Procesos.class.cast(g);
                    String temp = e.get_Nombre();
                    sim.setProcess(i, temp, 1);
                    a++;
                }}
                         leave = true;
            Para_ciclo_actual();
        }
     }
 }
 
 
 //metodo que intenta parar los threads actuales (no funciona, actualmente no hace nada)
 public void Para_ciclo_actual () throws InterruptedException{
     System.out.println("posicion 2"+lista_ready.isSize());
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
     //System.out.println("no es aqui, es la bruja");
     int a  = 0;
     if(a == Planifiacion_vigente){
        FCFS();
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
         Round_Robin();
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
       SPN();
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
        SRT();
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
       HRRN();
     }else{
       a++;  
     }
     if(a == Planifiacion_vigente){
        Feedback();
     }
 }
 
 
 //metodo que vacia las cpus y agrega los procesos que estaban ahi a la lista de listos
 public void vaciar(int numero_cpus, int r) throws InterruptedException{
     //System.out.println(" Primer antes");
     //lista_ready.print();
     //System.out.println("posicion 3"+lista_ready.isSize());
     for (int i=0; i < numero_cpus; i++){
     int a =0;
     int b = 0;
     Object c = lista_cpu.search(r);
     Cpu d = Cpu.class.cast(c);
     while (a < numero_proceso_cpu){
      Object g = d.getProcesos().search(a);
      Procesos e = Procesos.class.cast(g);
      if(e.getCierre() != ciclos){
         if(e.comprobar_excepcion(ciclos)){
          lista_bloqueados.addEnd(e);
          //setLista_ready(lista_bloqueados);
      }else{
      lista_ready.addEnd(e);
      //setLista_ready(lista_ready);
      }
      }else{
         lista_largo_plazo.addEnd(e);
      }
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
   chequear_bloqueados(); 
 } 
 
 //metodo que verifica cuales procesos pueden salir de la lista de bloqueados
 public void chequear_bloqueados() throws InterruptedException{
     //System.out.println("posicion 4"+lista_ready.isSize());
     System.out.println("lista de bloqueados");
     lista_bloqueados.print();
     if(lista_bloqueados.getFirst() != null){
     for (int i=0; i < lista_bloqueados.isSize(); i++){
     System.out.println(lista_bloqueados.isSize());
     Object c = lista_bloqueados.search(i);
     Procesos d = Procesos.class.cast(c);
     if(d.get_satisface() == ciclos){
         lista_ready.addEnd(d);
         //setLista_ready(lista_ready);
         }
       }   
     }
     Verificar_Politica_Planificacion(); 
 }
 
 //excepcion que se usa para ordenar los procesos cuando se inician
 public void Excepcion() throws InterruptedException  {
  TimeUnit.MILLISECONDS.sleep(  100);   
 }
 
}
  



