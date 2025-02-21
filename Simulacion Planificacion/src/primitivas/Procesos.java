/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//clase usada para referenciar los procesos cargados a memoria, se puede sacar todoa la informacion de 
//los proceos aqui, por favor en caso de crear procesos en ventana llenar la clase tambien
package primitivas;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Procesos extends Thread{
int Id;
int make_exception = -1;
int satisface = -1;
int tiempo = (int)Math.floor(Math.random()*50+1);;
int prioridad = (int)Math.floor(Math.random()*20+1);
int cierre = (int)Math.floor(Math.random()*50+10);;
boolean ready = true;
boolean bloqueado = false;
boolean largo_plazo = false;
String Nombre;
int Estado_Pc = (int) Math.floor(Math.random()*50+10);
int Estado_Mar = (int) Math.floor(Math.random()*50+10);
Boolean Entrada_Salida = false;
Boolean Cpu_bound = false;

public int get_Id (){
    return Id;
}

public int get_make_exception (){
    return make_exception;
}

public int get_satisface (){
    return satisface;
}

public String get_Nombre (){
    return Nombre;
}

public int get_Estado_Pc (){
    return Estado_Pc;
}

public int get_Estado_Mar (){
    return Estado_Mar;
}

public Boolean get_Entrada_Salida (){
    return Entrada_Salida;
}

public Boolean get_Cpu_bound (){
    return Cpu_bound;
}

public Boolean get_ready (){
    return ready;
}

public Boolean get_bloqueado (){
    return bloqueado;
}

public Boolean get_largo_plazo (){
    return largo_plazo;
}

public void change__Id (int id){
    Id = id;
}

public void change_Nombre (String nombre){
     Nombre = nombre;
}

public void chnage_Estado_Pc (int id){
     Estado_Pc = id;
}

public void change_Estado_Mar (int id){
     Estado_Mar = id;
}

public void change_Entrada_Salida (boolean id){
     Entrada_Salida = id;
} 

public void change_Cpu_bound (boolean id){
     Cpu_bound = id;
}

public void change_ready (boolean id){
     ready = id;
}

public void change_bloqueado (boolean id){
     bloqueado = id;
}

public void change_largo_plazo (boolean id){
     largo_plazo = id;
}

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getCierre() {
        return cierre;
    }

    public void setCierre(int cierre) {
        this.cierre = cierre;
    }
    
//metodo para validar que el nombre no es vacio
public boolean  Validar_Nombre  (String nombre){
    if (nombre == null){
        return false;
    }else{
       return true; 
    } 
}
//metodo para validar que el numero de excepciones tiene sentido
public void cambiar_make_exception(int numero){
    if(numero > 0){
      make_exception = numero;  
    }else{
      System.out.println("numero equivocado" +1 ); 
    }
}


//metodo para validar que el numero para satisfacer la excepcion tiene sentido
public void cambiar_satisface(int numero){
    if(numero > 0){
      satisface = numero;  
    }else{
      System.out.println("numero equivocado"+ 2); 
    }
}

public boolean comprobar_excepcion(int a){
   if(this.Entrada_Salida){
     if(a == make_exception){
         return true;
     }
     return false;
   }
   return false;
}

//metodo para activar los threads y hagan algo falta saber como pararlos en mitad de ejecucion
 @Override
        public void run(){
            while(true){
                
                try {
                    System.out.println(prioridad);
                    sleep(1000);
                    break;
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(Procesos.class.getName());
                }
            }
        
        } 
        

        
        }             