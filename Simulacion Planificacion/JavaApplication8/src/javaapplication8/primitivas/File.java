/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.primitivas;

import java.awt.Color;

/**
 *
 * @author RDG
 */
public class File {
    public int[] locations;
    public int size;
    public String name;
    public Color color;
    public int level;
    public String parent;
    
    public void File(int size){
        this.size = size;
        this.locations = new int[size];
    }
    
    public String[] readFile(){
        String[] data = new String[3];
        data[0] = this.name;
        data[1] = String.valueOf(this.size);
        data[2] = String.valueOf(this.locations[0]);
        return data;
    }
    
    public void editFile(String newName){
        this.name = newName;
    }
    
    public void delFile(){
        this.locations = null;
        this.name = null;
        this.size = 0;
    }
    
    public void updateLocations(int[] newLocations){
        this.locations = newLocations;
    }
    
    public void updateColor(Color color){
        if (color != Color.lightGray){
            this.color = color;
        } else {
            this.color = null;
        }
    }
}