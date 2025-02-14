/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.util.Arrays;
import primitivas.List;
/**
 *
 * @author RDG
 */
public class roundrobin {
    public static List generateList(List entryList, int roundRobinCycle){
        List finalList = new List();
        int[] instNum = new int[entryList.isSize()];
        int[] doneInstNum = new int[entryList.isSize()];
        int[] waitInstNum = new int[entryList.isSize()];
        
        // Se creo un array con el numero de instrucciones
        // Se escribe en cada posicion el numero de instrucciones
        for (int i = 0; i < entryList.isSize(); i++) {
            String[] tempA = (String[]) entryList.searchPos(i);
            instNum[i] = Integer.parseInt(tempA[1]);
        }
        
        // Crear la lista de ejecucion final en base
        // la logica roundrobin, las excepciones y la
        // satisfaccion de cada excepcion
        boolean leave = false;
        for (int i = 0; i < entryList.isSize(); i++) {
            String[] tempB = (String[]) entryList.searchPos(i);
            String[] tempBFinal = new String[8];
            for (int k = 0; k < tempB.length; k++) {
                tempBFinal[k] = tempB[k];
            }
            tempBFinal[7] = "READY";
            finalList.addEnd(tempBFinal);
        }
        while (!leave) {
            //System.out.println("Comienza el ciclo");
            //System.out.println(Arrays.toString(instNum));
            int canILeave = 0;
            for (int i = 0; i < instNum.length; i++) {
                canILeave = canILeave + instNum[i];
            }
            if (canILeave <= (-entryList.isSize())){
                leave = true;
            }
            for (int i = 0; i < entryList.isSize(); i++) {
                //System.out.print("For en Pos, ");
                //System.out.println(i);
                String[] temp = (String[]) entryList.searchPos(i);
                if (temp[3].equals("false")){
                    for (int j = 0; j < roundRobinCycle; j++) {
                        if (instNum[i] > 0){
                            temp[7] = "RUNNING";
                            instNum[i]--;
                            doneInstNum[i]++;
                            String[] finalTemp = new String[8];
                            for (int k = 0; k < temp.length; k++) {
                                finalTemp[k] = temp[k];
                            }
                            finalList.addEnd(finalTemp);
                        } else if (instNum[i] == 0){
                            temp[7] = "DONE";
                            instNum[i]--;
                            String[] finalTemp = new String[8];
                            for (int k = 0; k < temp.length; k++) {
                                finalTemp[k] = temp[k];
                            }
                            finalList.addEnd(finalTemp);
                        }
//                        System.out.println(Arrays.toString(temp));
                    }
                } else {
                    for (int j = 0; j < roundRobinCycle; j++) {
                        if (instNum[i] > 0){
                            if (doneInstNum[i] < Integer.parseInt(temp[4])){
                                temp[7] = "RUNNING";
                                instNum[i]--;
                                doneInstNum[i]++;
                                String[] finalTemp = new String[8];
                                for (int k = 0; k < temp.length; k++) {
                                    finalTemp[k] = temp[k];
                                }
                                finalList.addEnd(finalTemp);
                            } else {
                                waitInstNum[i]++;
                                if (waitInstNum[i] >= Integer.parseInt(temp[5])){
                                    doneInstNum[i] = 0;
                                    waitInstNum[i] = 0;
                                    temp[7] = "READY";
                                    String[] finalTemp = new String[8];
                                    for (int k = 0; k < temp.length; k++) {
                                        finalTemp[k] = temp[k];
                                    }
                                    finalList.addEnd(finalTemp);
                                } else {
                                    temp[7] = "BLOCKED";
                                    String[] finalTemp = new String[8];
                                    for (int k = 0; k < temp.length; k++) {
                                        finalTemp[k] = temp[k];
                                    }
                                    finalList.addEnd(finalTemp);
                                }
                            }
                        } else if (instNum[i] == 0){
                            temp[7] = "DONE";
                            instNum[i]--;
                            String[] finalTemp = new String[8];
                            for (int k = 0; k < temp.length; k++) {
                                finalTemp[k] = temp[k];
                            }
                            finalList.addEnd(finalTemp);
                        }
//                        System.out.println(Arrays.toString(temp));
                    }
                }
            }
        }
//        for (int i = 0; i < finalList.isSize(); i++) {
//            System.out.println(finalList.searchPos(i));
//            System.out.println(Arrays.toString((String[]) finalList.searchPos(i)));
//        }
        return finalList;
    }
}
