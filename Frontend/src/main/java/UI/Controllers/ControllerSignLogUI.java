/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Controllers;


import UI.SignIn.*;
import Utils.WindowManager;
import static UI.Controllers.Utils.controllersUtils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Services.AuthService;
import Utils.Requests.LoginRequest;
import Utils.Responses.LoginResponse;
import javax.swing.JButton;

//<editor-fold defaultstate="collapsed" desc="LoreImpsum">
//</editor-fold>

/**
 *
 * @author JUANDA
 */
public class ControllerSignLogUI implements ActionListener {

    private final Inicio VistaInicio;
    private InicioSesion VistaInicioSesion;
    private Registro VistaRegistro;

    private void assignListeners(JButton[] buttons) {
        for (JButton button : buttons) {
            button.addActionListener(this);
        }
    }
    
    /**
     * Initializes the view of the main page
     *
     * @param Vista
     */
    public ControllerSignLogUI(Inicio Vista) {

        this.VistaInicio = Vista;
        assignListeners(VistaInicio.getButtons());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //<editor-fold defaultstate="collapsed" desc="Inicio">
        if (canDoAction(VistaInicio, e, VistaInicio.getSignIn()))openLogin();
        if (canDoAction(VistaInicio, e, VistaInicio.getRegister()))openRegister();
        if (canDoAction(VistaInicio, e, VistaInicio.getExit()))exit();
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Inicio Sesion">
        if (canDoAction(VistaInicioSesion, e, VistaInicioSesion.getLogIn()))LogIn();
        if (canDoAction(VistaInicioSesion, e, VistaInicioSesion.getBack()))goBack(VistaInicioSesion);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Registro">
        if (canDoAction(VistaRegistro, e, VistaRegistro.getSave()))Register();
        if (canDoAction(VistaRegistro, e, VistaRegistro.getBack()))goBack(VistaRegistro);
        //</editor-fold>
    }

    //<editor-fold defaultstate="collapsed" desc="Menu principal">
    private void openLogin() {
        this.VistaInicioSesion = new InicioSesion();
        assignListeners(VistaInicioSesion.getButtons());
        WindowManager.change(VistaInicio, VistaInicioSesion);
    }

    private void openRegister() {
        this.VistaRegistro = new Registro();
        assignListeners(VistaRegistro.getButtons());
        WindowManager.change(VistaInicio, VistaRegistro);
    }

    private void exit() {
        WindowManager.close(VistaInicio);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inicio Sesion">
    private void LogIn(){
        String email = VistaInicioSesion.getEmail().getText();
        char[] password = VistaInicioSesion.getPassword().getPassword();
        String strPassword = new String(password);
        Arrays.fill(password, '0');
        
        AuthService authService = new AuthService();
        LoginResponse response = authService.login(email, strPassword);
        
        if(response == null){
            JOptionPane.showMessageDialog(null, "Credenciales Incorrectas");
            return;
        }
        
        switch(response.getRole()){
            case "Codirector":;break;
            case "Coordinador":;break;
            case "Director":;break;
            case "Estudiante":;break;
            case "Jefe Departamento":;break;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Registro">
    public void Register(){
        String name = VistaRegistro.getNameUser().getText();
        String lastName = VistaRegistro.getLastName().getText();
        String fullName = name + " " + lastName;
        byte[] cel = VistaRegistro.getCellphone().getText().getBytes();
        String Program = VistaRegistro.getProgram().getSelectedItem().toString();
        String rol = VistaRegistro.getRol().getSelectedItem().toString();
        String email = VistaRegistro.getEmail().getText();
        String pass1 = VistaRegistro.getPassword().getText();
        char[] pass2 = VistaRegistro.getPassword2().getPassword();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Backer">
    private void goBack(JFrame current){
        WindowManager.change(current, VistaInicio);
    }
    //</editor-fold>
    
}
