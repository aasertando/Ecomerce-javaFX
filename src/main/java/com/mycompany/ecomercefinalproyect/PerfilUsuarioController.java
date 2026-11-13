/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ecomercefinalproyect;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author garci
 */
public class PerfilUsuarioController implements Initializable {
    
    @FXML
    private TextField casillaNombreUsuario;
    
    @FXML
    private TextField casillaIDUsuario;
    
    @FXML
    private TextField casillaCorreoUsuario;
    
    @FXML
    private TextField casillaTelefonoUsuario;
    
    @FXML
    private TextField casillaCiudadUsuario;
    
    @FXML
    private TextField casillaDirecciondUsuario;
    
    @FXML
    private Button btnEditarInfo;
    
    @FXML
    private Button btnGuardarInfo;
    
    @FXML
    private void guardarInfo(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
