/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.primitivas;
/**
 *
 * @author RDG
 */
public class Folder {
    public String name;
    public List files;
    
    public void Folder(String name) {
        this.name = name;
        this.files = new List();
    }
    
    public void addFile(File file){
        this.files.addEnd(file);
    }
    
    public void delFile(File file){
        for (int i = 0; i < this.files.isSize(); i++) {
            if (this.files.searchPos(i) == file){
                this.files.delPos(i);
            }
        }
    }
    
    public List returnFiles(){
        return this.files;
    }
}
