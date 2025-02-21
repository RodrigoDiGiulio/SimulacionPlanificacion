/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

//import java.util.HashSet;

/**
 *
 * @author RDG
 * @param <T>
 */
public class List<T>{
    //Atributos
    private MyNode<T> first;
    private MyNode<T> last;
    private MyNode<T> aux;
    private MyNode<T> next;
    private MyNode<T> prev;
    private MyNode<T> temp;
    private int size;
    
    public List(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    public boolean isEmpty(){
        return first == null;
    }

    public MyNode<T> getFirst() {
        return first;
    }

    public MyNode<T> getLast() {
        return last;
    }

    public MyNode<T> getNext() {
        return next;
    }
      
    public int isSize(){
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFirst(MyNode<T> first) {
        this.first = first;
    }
    
    public void empty(){
        this.first = null;
        this.last = null;
        this.aux = null;
        this.next = null;
        this.prev = null;
        this.temp = null;
        this.size = 0;
    }
    
    public void addStart(T data){
        MyNode<T> newData = new MyNode(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            newData.setData(data);
            newData.setNext(first);
            first.setPrev(newData);
            first = newData;
//            System.out.println("newData " + newData);
        }
        size++;
    }
    
    public void addEnd(T data){
        MyNode<T> newData = new MyNode(data);
//        System.out.println("Data "+ data);
//        System.out.println("NewData "+ newData);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            newData.setData(data);
            last.setNext(newData);
            newData.setPrev(last);
            last = newData;

        }
        size++;
    }
    
    public void addPos(int pos,T data){
        MyNode<T> newData = new MyNode(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }
        else if (pos == 0){
            this.addStart(data);
        }
        else if (pos == (this.isSize() - 1)){
            this.addEnd(data);
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            prev = aux;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                prev = aux;
                aux = aux.getNext();
                i++;
            }
            newData.setData(data);
//            System.out.println("Aux "+aux);
//            System.out.println("Prev "+prev);
            
            prev.setNext(newData);
//            System.out.println("Next de Prev "+prev.getNext());
            
            newData.setNext(aux);
//            System.out.println("Next de NewData "+newData.getNext());
            
            aux.setPrev(newData);
//            System.out.println("Prev de Aux "+aux.getPrev());
            
            newData.setPrev(prev);
//            System.out.println("Prev de NewData "+newData.getPrev());
            
            size++;
        }
    }
    
    public void delStart(){
        if(!this.isEmpty()){
            if (this.getNext() == null) {
                System.out.println("entre en la zona de muerte");
                this.empty();
            }
            else{
                first = first.getNext();
                first.setPrev(null);
                
            }
        }
    }
    
    public void delEnd(){
        if(!this.isEmpty()){
            if (size == 1){
                this.empty();
            }
            else{
                last = last.getPrev();
                last.setNext(null);
            }
        }
    }
    
    public void delPos(int pos){
        if(this.isEmpty()){
            first = last = null;
        }
        else if (pos == 0){
            this.delStart();
        }
        else if (pos == (this.isSize() - 1)){
            this.delEnd();
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            prev = aux;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                prev = aux;
                aux = aux.getNext();
                i++;
            }
            prev.setNext(aux.getNext());
            aux.setPrev(null);
            temp = aux.getNext();
            aux.setNext(null);            
            temp.setPrev(prev);
            
            size--;
        }
    }
    
    public void replaceStart(T data){
        MyNode<T> newData = new MyNode(data);
        if(!this.isEmpty()){
            if (size == 1) {
                newData.setData(data);
                first = last = newData;
            }
            else{
                first.setData(data);
            }
        }
    }
    
    public void replaceEnd(T data){
        MyNode<T> newData = new MyNode(data);
        if(!this.isEmpty()){
            if (size == 1){
                newData.setData(data);
                first = last = newData;
            }
            else{
                last.setData(data);
            }
        }
    }
    
    public void replacePos(int pos, T data){
        MyNode<T> newData = new MyNode(data);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }
        else if (pos == 0){
            this.replaceStart(data);
        }
        else if (pos == (this.isSize() - 1)){
            this.replaceEnd(data);
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                aux = aux.getNext();
                i++;
            }
            aux.setData(data);
        }
    }
    
    public T searchStart(){
        if(!this.isEmpty()){
            T data = first.getData();
            return data;
        }
        else{
            System.out.println("Error: List is empty");
            return null;
        }
    }
    
    public T searchEnd(){
        if(!this.isEmpty()){
            T data = last.getData();
            return data;
        }
        else{
            System.out.println("Error: List is empty");
            return null;
        }
    }
    
    public T searchPos(int pos){
        T data = null;
        if(this.isEmpty()){
            System.out.println("Error: List is empty");
            return null;
        }
        else if (pos == 0){
            data = this.searchStart();
        }
        else if (pos == (this.isSize() - 1)){
            data = this.searchEnd();
        }
        else if (pos > (this.isSize() - 1) || pos < 0){
            System.out.println("Error: Size");
        }
        else{
//            System.out.println("POS NORMAL");
            aux = first;
            int i = 0;
            while (i != pos){
//                System.out.println("I");
//                System.out.println(i);
                if (aux.getNext() == null){
                    break;
                }
                aux = aux.getNext();
                i++;
            }
            data = aux.getData();
            return data;
        }
        return data;
    }

// DO NOT DELETE, JUST IN CASE
    
//    public void print(){
//        MyNode temp = first;
//        if(this.isEmpty()){
//            System.out.println("Error: list is empty.");
//        }
//        while(temp != null){
////            data = temp.getData();
////            for (int i : data){
//                System.out.println(temp);
//                System.out.println(temp.getData());
//                temp = temp.getNext();
//            }
//        }
    
    public void revert(){
        MyNode temp = first;
        MyNode aux = null;
        MyNode prev = null;
        if(this.isEmpty()){
            System.out.println("Error: list is empty.");
        }else{      
            System.out.println("PRE");
            System.out.println("TEMP " + temp);
            System.out.println("aux " + aux);
            while (temp != null){

//                    aux = temp;
//                    temp = temp.getNext();
//                    aux.setNext(temp);
//                    temp.setPrev(aux);
                    
                    aux = temp.getNext();
                    temp.setNext(prev);
                    prev = temp;
                    temp = aux;


                    System.out.println("POS2");
                    System.out.println("TEMP " + temp);
                    System.out.println("Aux " + aux);
            }
            first = prev;
        }
    }

    public void print(){
        MyNode temp = first;
        if(this.isEmpty()){
            System.out.println("Error: list is empty.");
        }
        while(temp != null){
//          int, String, byte, long, float, double, boolean, char and short
            Object data = temp.getData();
            //
            //INT INT INT INT INT
            //
            if (data instanceof int[]) {
                int[] dataArray = (int[]) data;
                for (int i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //STRING STRING STRING STRING STRING
            //
            else if (data instanceof String[]) {
                String[] dataArray = (String[]) data;
                for (String i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //BYTE BYTE BYTE BYTE BYTE
            //
            else if (data instanceof byte[]) {
                byte[] dataArray = (byte[]) data;
                for (byte i : dataArray) {
                    System.out.println(i);
                }
            } 
            //
            //LONG LONG LONG LONG LONG
            //
            else if (data instanceof long[]) {
                long[] dataArray = (long[]) data;
                for (long i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //FLOAT FLOAT FLOAT FLOAT FLOAT
            //
            else if (data instanceof float[]) {
                float[] dataArray = (float[]) data;
                for (float i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //DOUBLE DOUBLE DOUBLE DOUBLE DOUBLE
            //
            else if (data instanceof double[]) {
                double[] dataArray = (double[]) data;
                for (double i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //BOOLEAN BOOLEAN BOOLEAN BOOLEAN BOOLEAN
            //
            else if (data instanceof boolean[]) {
                boolean[] dataArray = (boolean[]) data;
                for (boolean i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //CHAR CHAR CHAR CHAR CHAR
            //
            else if (data instanceof char[]) {
                char[] dataArray = (char[]) data;
                for (char i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //SHORT SHORT SHORT SHORT SHORT
            //
            else if (data instanceof short[]) {
                short[] dataArray = (short[]) data;
                for (short i : dataArray) {
                    System.out.println(i);
                }
            }
            //
            //ELSE ELSE ELSE ELSE ELSE
            //
            else {
                //TEMP IS MEMORY LOCATION
//                System.out.println(temp);
                System.out.println(data);
            }
            temp = temp.getNext();
        }
    }
    
    
    //busca un node en el valor del numero indicado
    public Object search(int numero){
      MyNode pointer = getFirst(); 
        int a = 0;
        while(pointer.getNext() != null && a<numero ){
            pointer = pointer.getNext();
            a++;
        }
        if(numero == 0){
            return getFirst().getData();
        }
        if (numero == isSize()){
           return getLast().getData();
        }else{
            return pointer.getElement();
        }  
    }
    
    //metodo para llenar las listas sin aumentar el size
   public void llenar(T data){     
       MyNode<T> newData = new MyNode(data);
//        System.out.println("Data "+ data);
//        System.out.println("NewData "+ newData);
        if(this.isEmpty()){
            newData.setData(data);
            first = last = newData;
        }else{
            newData.setData(data);
            last.setNext(newData);
            newData.setPrev(last);
            last = newData;

        }   
     } 
   
   
   //metodo para revisar si hay espacio en la lista de cpus para un nuevo proceso
   public boolean Revisar(int a){
      MyNode temp = first;
      int i = 0;
      while (i < a){
        if (temp != null){  
         temp = temp.getNext();
         i++;
      }else{
          return true;
        }        
   }
      return false;
 }
     //metodo de delete que funciona correctamente en bucle
       public void deleteBegin(){
        if (isEmpty()) {
            System.out.println("La lista esta vacia");
        } else {
            MyNode pointer = getFirst();
            setFirst(pointer.getNext());
            pointer.setNext(null);         
        }
        
    }
       
          public void deleteFinal(){
        if (isEmpty()) {
            System.out.println("La lista esta vacia");
        } else {
            MyNode pointer = getFirst();
            while (pointer.getNext().getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(null);
            size--;
        }
    }     
       
  public void Borrar_especifico(Object camino){
         if (isEmpty()) {
            System.out.println("La lista esta vacia");
        } else {
            MyNode pointer = getFirst();
            boolean encontrado = false;
            if(camino == getFirst().getElement()){
                deleteBegin();
                pointer = getFirst();
            }
            if(camino == getLast().getElement()){
                deleteFinal();
                pointer = getLast();
            }
            while(pointer != null && encontrado == false){
                if(pointer.getElement() == camino){
                    MyNode temp = pointer.getPrev();
                    MyNode temp2 = pointer.getNext();
                    temp.setNext(temp2);
                    temp2.setPrev(temp);
                    pointer.setPrev(null);
                    pointer.setNext(null);
                    size--;
                    encontrado = true;
                }else{
                    pointer = pointer.getNext();
                }
            }
        }
  }   
   
}