/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.client.gui.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static tg.esiba.groupe2.gab.client.gui.controllers.AcceuilController.leCompte;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.entiies.Compte;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class SoldeController implements Initializable {

    @FXML
    private FontAwesomeIcon close;
    @FXML
    private Label solde;
    @FXML
    private AnchorPane soldeActu;
    
    ClientDao cliD = new ClientDao();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Compte compte = leCompte();
        solde.setText(String.valueOf(compte.getSolde()));
    }

    @FXML
    private void fermer(MouseEvent event) {
        soldeActu.getScene().getWindow().hide();
    }

}
