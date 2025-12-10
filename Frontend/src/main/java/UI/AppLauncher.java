/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import UI.Controllers.ControllerSignLogUI;
import UI.SignIn.*;

/**
 *
 * @author JUANDA
 */
public class AppLauncher {
    public static void start(){
        Inicio Vista = new Inicio();
        new ControllerSignLogUI(Vista);
        Vista.setLocationRelativeTo(null);
        Vista.setVisible(true);
    }
}
