/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import javax.swing.JFrame;

/**
 *
 * @author JUANDA
 */
public class WindowManager {
    
    public static void change(JFrame current, JFrame next){
        current.dispose();
        next.setLocationRelativeTo(null);
        next.setVisible(true);
    }

    public static void open(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void close(JFrame frame) {
        frame.dispose();
    }
    
    public static void fullChange(JFrame current, JFrame next, Object Controller){
        
    }
    
}
