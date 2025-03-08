/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication8.main;

import java.awt.Color;
import javaapplication8.primitivas.File;
import javaapplication8.primitivas.List;
import javaapplication8.primitivas.Folder;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author RDG
 */
public class main {
    public static MiscSystem miscSystem;
    public static TableDisplay tableDisplay;
    public static BlockDisplay blockDisplay;
    public static List files;
    public static int Storage = 64;
    
    public static void main(String[] args) throws InterruptedException {
        files = new List();
        miscSystem = new MiscSystem();
        tableDisplay = new TableDisplay();
        blockDisplay = new BlockDisplay(Storage);
    }
        
//        TableDisplay tableDisplay = new TableDisplay();
//        File file = new File();
//        file.name = "Locura";
//        file.size = 5;
//        file.locations = new int[]{0,4,5};
//        file.color = Color.lightGray;
//        TimeUnit.MILLISECONDS.sleep(1000);
//        tableDisplay.addFile(file);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        tableDisplay.addFile(file);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        tableDisplay.addFile(file);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        tableDisplay.addFile(file);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        tableDisplay.delFile(file);



//        File fileA = new File();
//        fileA.name = "TestA";
//        fileA.size = 5;
//        File fileB = new File();
//        fileB.name = "TestB";
//        fileB.size = 10;
//        File fileC = new File();
//        fileC.name = "TestC";
//        fileC.size = 8;
//        File fileD = new File();
//        fileD.name = "TestD";
//        fileD.size = 2;
//        File fileE = new File();
//        fileE.name = "TestE";
//        fileE.size = 6;
//        BlockDisplay blockDisplay = new BlockDisplay(64);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga A");
//        blockDisplay.addFile(fileA);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga B");
//        blockDisplay.addFile(fileB);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga C");
//        blockDisplay.addFile(fileC);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga D");
//        blockDisplay.addFile(fileD);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga E");
//        blockDisplay.addFile(fileE);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("deleting B");
//        blockDisplay.delFile(fileB);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga C");
//        blockDisplay.addFile(fileC);
//        TimeUnit.MILLISECONDS.sleep(1000);
//        System.out.println("Addinga B");
//        blockDisplay.addFile(fileB);
        
        

//        File fileA = new File();
//        fileA.name = "TestA";
//        fileA.size = 3;
//        File fileB = new File();
//        fileB.name = "TestB";
//        fileB.size = 3;
//        File fileC = new File();
//        fileC.name = "TestC";
//        fileC.size = 3;
//        Folder folder = new Folder();
//        folder.name = "Probando";
//        
//        FileDisplay fileDisplay = new FileDisplay();
//        TimeUnit.MILLISECONDS.sleep(3000);
//        fileDisplay.addFolder(folder,fileDisplay.root,"Root");
//        fileDisplay.addFile(fileA,fileDisplay.root,folder.name);
//        fileDisplay.addFile(fileA,fileDisplay.root,"Root");
//        TimeUnit.MILLISECONDS.sleep(3000);
//        fileDisplay.addFile(fileB,fileDisplay.root,folder.name);
//        fileDisplay.addFile(fileB,fileDisplay.root,"Parent 2");
//        fileDisplay.delFile(fileA,fileDisplay.root,"Root");
//        TimeUnit.MILLISECONDS.sleep(3000);
//        fileDisplay.addFile(fileC,fileDisplay.root,folder.name);
//        fileDisplay.addFile(fileC,fileDisplay.root,"Parent 1");
}
