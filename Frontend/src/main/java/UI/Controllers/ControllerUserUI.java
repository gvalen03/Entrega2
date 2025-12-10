/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Controllers;

import UI.Users.*;
import UI.Projects.Evaluations.*;
import UI.Projects.Uploads.*;
import Utils.WindowManager;
import static UI.Controllers.Utils.controllersUtils.*;
import UI.Projects.Evaluations.EvaluacionFormatoA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UI.SignIn.Inicio;
import javax.swing.JFrame;
import javax.swing.JButton;

//<editor-fold defaultstate="collapsed" desc="Lore Ipsum">
//</editor-fold>

/**
 *
 * @author JUANDA
 */
public class ControllerUserUI implements ActionListener{

    private Codirector vistaCodirector;
    private Coordinador vistaCoordinador;
    private Director vistaDirector;
    private Estudiante vistaEstudiante;
    private JefeDepartamento vistaJefeDpto;
    
    private void assignListeners(JButton[] buttons) {
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public ControllerUserUI(Codirector Vista){
        this.vistaCodirector = Vista;
        assignListeners(vistaCodirector.getButtons());
    }
    
    public ControllerUserUI(Coordinador vista){
        this.vistaCoordinador = vista;
        assignListeners(vistaCoordinador.getButtons());
    }
    
    public ControllerUserUI(Director Vista){
        this.vistaDirector = Vista;
        assignListeners(vistaDirector.getButtons());
    }
    
    public ControllerUserUI(Estudiante Vista){
        this.vistaEstudiante = Vista;
        assignListeners(vistaEstudiante.getButtons());
    }
    
    public ControllerUserUI(JefeDepartamento Vista){
        this.vistaJefeDpto = Vista;
        assignListeners(vistaJefeDpto.getButtons());
    }
    //</editor-fold>    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //<editor-fold defaultstate="collapsed" desc="Codirector">
        if(canDoAction(vistaCodirector, e, vistaCodirector.getAFormat()))goFormatA(vistaCodirector);
        if(canDoAction(vistaCodirector, e, vistaCodirector.getExit()))goBack(vistaCodirector);
        if(canDoAction(vistaCodirector, e, vistaCodirector.getMonographies()));
        if(canDoAction(vistaCodirector, e, vistaCodirector.getPreProject()))goPreProj(vistaCodirector);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Coordinador">
        if(canDoAction(vistaCoordinador, e, vistaCoordinador.getAFormat()))goFormatA(vistaCoordinador);
        if(canDoAction(vistaCoordinador, e, vistaCoordinador.getExit()))goBack(vistaCoordinador);
        if(canDoAction(vistaCoordinador, e, vistaCoordinador.getMonographies()));
        if(canDoAction(vistaCoordinador, e, vistaCoordinador.getPreProjects()))goPreProj(vistaCoordinador);
        if(canDoAction(vistaCoordinador, e, vistaCoordinador.getReports()));
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Director">
        if(canDoAction(vistaDirector, e, vistaDirector.getAFormat()))goFormatA(vistaDirector);
        if(canDoAction(vistaDirector, e, vistaDirector.getExit()))goBack(vistaDirector);
        if(canDoAction(vistaDirector, e, vistaDirector.getPreProjects()))goPreProj(vistaDirector);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Estudiante">
        if(canDoAction(vistaEstudiante, e, vistaEstudiante.getExit()))goBack(vistaEstudiante);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JefeDepartamento">
        if(canDoAction(vistaJefeDpto, e, vistaJefeDpto.getAssignEvaluators()));
        if(canDoAction(vistaJefeDpto, e, vistaJefeDpto.getExit()))goBack(vistaJefeDpto);
        if(canDoAction(vistaJefeDpto, e, vistaJefeDpto.getPreProjects()));
        //</editor-fold>
    }
    
    //<editor-fold defaultstate="collapsed" desc="Acciones">
    //<editor-fold defaultstate="collapsed" desc="Codirector">
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Jefe Depto">
    private void seeReports(){
        
    }
    //</editor-fold>
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Backer&Generics">
    private void goBack(JFrame Current){
        WindowManager.close(Current);
        Inicio inicio = new Inicio();
        new ControllerSignLogUI(inicio);
        inicio.setVisible(true);
    }
    
    private void goFormatA(JFrame Current){
        WindowManager.close(Current);
        EvaluacionFormatoA EVA = new EvaluacionFormatoA();
        new ControllerProjEvaluationUI(EVA);
        EVA.setVisible(true);
    }
    
    private void goPreProj(JFrame Current){
        WindowManager.close(Current);
        AnteProyectosJefe EVA = new AnteProyectosJefe();
        new ControllerProjEvaluationUI(EVA);
        EVA.setVisible(true);
    }
    //</editor-fold>
    
}
