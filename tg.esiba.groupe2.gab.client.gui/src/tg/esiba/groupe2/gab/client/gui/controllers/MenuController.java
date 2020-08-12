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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.client.gui.controllers.AcceuilController.APPLICATION_ICON;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class MenuController implements Initializable {

    @FXML
    private Label bienveue;
    @FXML
    private Button btnConsulter;
    @FXML
    private Button btnRetrait;
    @FXML
    private Button btnCode;
    @FXML
    private Pane menu;
    @FXML
    private FontAwesomeIcon close;
    @FXML
    private FontAwesomeIcon back;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void consulter(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Solde.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
    }

    @FXML
    private void retrait(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Retrait.fxml"));
        menu.getChildren().removeAll();
        menu.getChildren().setAll(root);
    }

    @FXML
    private void code(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/NouveauCode.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
    }

    @FXML
    private void fermer(MouseEvent event) {
        menu.getScene().getWindow().hide();
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        menu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
    }
}
