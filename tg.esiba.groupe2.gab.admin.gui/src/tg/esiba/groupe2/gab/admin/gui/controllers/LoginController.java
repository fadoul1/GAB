/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.admin.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tg.esiba.groupe2.gab.dao.impl.AdministrateurDao;
import tg.esiba.groupe2.gab.exception.AuthentificationException;
import tg.esiba.groupe2.gab.utils.Authentification;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class LoginController implements Initializable {

    @FXML
    TextField nomField;

    @FXML
    PasswordField passeField;

    @FXML
    private AnchorPane rootPane;

    @FXML
    ProgressIndicator progres;

    @FXML
    Button btnValider;

    Stage dashboard;

    public static AdministrateurDao adminD = new AdministrateurDao();

    public static String nomAdmin;

    public static final String APPLICATION_ICON = "tg/esiba/groupe2/gab/admin/gui/images/laBank.PNG";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progres.setVisible(false);
    }

    @FXML
    private void btnFermerAction() {
        rootPane.getScene().getWindow().hide();
    }

    @FXML
    private void btnValiderAction() {

        try {
            String nom = nomField.getText();
            nomAdmin = nom;
            String passe = passeField.getText();
            Authentification auth = new Authentification();
            auth.adminAuthentification(nom, passe);

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/Dashboard.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Tableau de bord");
            stage.getIcons().add(new Image(APPLICATION_ICON));
            stage.show();
            rootPane.getScene().getWindow().hide();

        } catch (AuthentificationException ex) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Login.fxml"));
            } catch (IOException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    public static int idAdmin() {
        return adminD.getIdAdmin(nomAdmin);
    }
}
