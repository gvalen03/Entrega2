/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Controllers.Utils;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *Clase de utilidades para los controladores de la UI
 * @author JUANDA
 */
public final class controllersUtils {
    
    private static boolean isViewNotNull(JFrame v){
        return v != null;
    }
    
    private static boolean isActionFrom(ActionEvent e, JButton s){
        return e.getSource() == s;
    }
    
    public static boolean canDoAction(JFrame v, ActionEvent e, JButton s){
        return isViewNotNull(v) && isActionFrom(e, s);
    }
}
