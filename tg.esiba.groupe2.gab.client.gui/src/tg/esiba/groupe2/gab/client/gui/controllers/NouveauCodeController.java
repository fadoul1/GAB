/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.client.gui.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.client.gui.controllers.AcceuilController.APPLICATION_ICON;
import static tg.esiba.groupe2.gab.client.gui.controllers.AcceuilController.leCompte;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.entiies.Client;
import tg.esiba.groupe2.gab.entiies.Compte;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class NouveauCodeController implements Initializable {

    @FXML
    private AnchorPane nouveauCode;
    @FXML
    private FontAwesomeIcon close;
    @FXML
    private TextField oldPasse;
    @FXML
    private TextField codeField;
    @FXML
    private Button btnValider;

    ClientDao clientD = new ClientDao();
    Client client = new Client();
    Compte compte = leCompte();
    String ancien = clientD.getOneClient(compte.getNumeroClient()).getPasse();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        oldPasse.setText(ancien);
    }

    @FXML
    private void fermer(MouseEvent event) {
        nouveauCode.getScene().getWindow().hide();
    }

    @FXML
    private void valider(MouseEvent event) throws IOException {
        String passe = codeField.getText();
        client = clientD.getOneClient(compte.getNumeroClient());
        client.setPasse(passe);
        clientD.updatePasse(client);

        Parent root1 = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Modifier.fxml"));

        Stage stage1 = new Stage();
        stage1.getIcons().add(new Image(APPLICATION_ICON));

        Scene scene1 = new Scene(root1);

        stage1.setScene(scene1);
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.showAndWait();
    }

}
