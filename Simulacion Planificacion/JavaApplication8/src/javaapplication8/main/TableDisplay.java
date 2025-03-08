/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.main;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javaapplication8.primitivas.File;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author RDG
 */
public class TableDisplay {
    public JFrame frame;
    public JTable table;
    public JScrollPane scroll;
    
    public TableDisplay(){
        table = new JTable(0,4);
        JTableHeader th = table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setHeaderValue("Nombre");
        th.repaint();        
        tc = tcm.getColumn(1);
        tc.setHeaderValue("Num. Bloques");
        th.repaint();
        tc = tcm.getColumn(2);
        tc.setHeaderValue("Pos. Bloque Inicial");
        th.repaint();
        tc = tcm.getColumn(3);
        tc.setHeaderValue("Color");
        th.repaint();
        
        scroll = new JScrollPane(table);
        frame = new JFrame("Table Display");
        frame.add(scroll);
        frame.setSize(400, 400);
        frame.setLocation(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        main.miscSystem.updateLog("TableDisplay started Correctly");
    }
    
    public void dispose(){
        frame.dispose();
    }
    
    public void addFile(File file){
        JLabel coloredLabel = new JLabel();
        coloredLabel.setOpaque(true);
        coloredLabel.setBackground(file.color);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{file.name, file.size, file.locations[0],coloredLabel});
        table.getColumnModel().getColumn(3).setCellRenderer(new LabelRenderer());
    }
    
    public void editFile(File file, String newName){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                if (model.getValueAt(row, column).equals(file.name)){
                    model.setValueAt(newName, row, column);
                }
            }
        }
    }
    
    public void delFile(File file){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int targetRow = -1;
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int column = 0; column < model.getColumnCount(); column++) {
                if (model.getValueAt(row, column).equals(file.name)){
                    targetRow = row;
                }
            }
        }
        if (targetRow != -1){
            model.removeRow(targetRow);
        }
    }
    
    private static class LabelRenderer extends JLabel implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JLabel) {
                JLabel label = (JLabel) value;
                return label;
            } else {
                return new JLabel("Error");
            }
        }
    }
}
