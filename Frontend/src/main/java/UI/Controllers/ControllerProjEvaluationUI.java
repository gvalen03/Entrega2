/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Controllers;

import UI.Projects.Evaluations.*;
import Utils.WindowManager;
import static UI.Controllers.Utils.controllersUtils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

//<editor-fold defaultstate="collapsed" desc="Lore Ipsum">
//</editor-fold>

/**
 *
 * @author JUANDA
 */
public class ControllerProjEvaluationUI implements ActionListener{

    private AnteProyectosJefe VistaJefe;
    private EvaluacionFormatoA VistaFormatoA;
    
    private void assignListeners(JButton[] buttons) {
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ControllerProjEvaluationUI(AnteProyectosJefe Vista){
        this.VistaJefe = Vista;
        assignListeners(this.VistaJefe.getButtons());
    }
    
    public ControllerProjEvaluationUI(EvaluacionFormatoA Vista){
        this.VistaFormatoA = Vista;
        assignListeners(this.VistaFormatoA.getButtons());
    }
    //</editor-fold>
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
