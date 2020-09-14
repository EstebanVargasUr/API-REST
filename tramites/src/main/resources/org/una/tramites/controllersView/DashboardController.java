/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllersView;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Esteban Vargas
 */
public class DashboardController implements Initializable {

    @FXML
    private JFXButton btnNombre;
    @FXML
    private JFXButton btnInformacion;
    @FXML
    private JFXButton btnDepartamentos;
    @FXML
    private JFXButton btnTipoTramite;
    @FXML
    private JFXButton btnDisenoTramite;
    @FXML
    private JFXButton btnAdmin;
    @FXML
    private JFXButton btnPermisos;
    @FXML
    private JFXButton btnUsuarios;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
