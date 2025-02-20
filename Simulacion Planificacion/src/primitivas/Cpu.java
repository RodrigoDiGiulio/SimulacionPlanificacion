/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


//metodo que representa los procesadores que tiene el ordenador
package primitivas;

public class Cpu {
int numero_procesos = 2;
int id;
List procesos = new List();

    public int getId() {
        return id;
    }

    public int getNumero_procesos() {
        return numero_procesos;
    }

    public List getProcesos() {
        return procesos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumero_procesos(int numero_procesos) {
        this.numero_procesos = numero_procesos;
    }
    
    
 //metodo que arranca todos los procesos ligados a este cpu
  public void arrancar(int a){
     int f = 0;
      while (f < a){
      Object g = getProcesos().search(f);
      Procesos e = Procesos.class.cast(g);
      //e.start();
          //System.out.println("este es el id:"+id +"este es el valor de f:"+ f);
      f++;
   }
    }
  
  
 //metodo que intenta (fracasa en ese intento) parar los procesos en el cpu 
    public void para(){
     int f = 0;
      while (f < 2){
      Object g = getProcesos().search(f);
      Procesos e = Procesos.class.cast(g);
      e.interrupt();
          //System.out.println("este es el id:"+id +"este es el valor de f:"+ f);
      f++;
   }
    }
  
 }

