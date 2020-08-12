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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.dao.impl.CompteDao;
import tg.esiba.groupe2.gab.entiies.Compte;
import tg.esiba.groupe2.gab.exception.AuthentificationException;
import tg.esiba.groupe2.gab.utils.Authentification;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class AcceuilController implements Initializable {

    @FXML
    private FontAwesomeIcon close;
    @FXML
    private Label bienveue;
    @FXML
    public static AnchorPane acceuil;
    @FXML
    private Pane contentArea;

    ClientDao clientDao = new ClientDao();
    Authentification auth = new Authentification();
    public static CompteDao compteD = new CompteDao();

    @FXML
    private TextField compteField;
    @FXML
    private Button btnLog;
    @FXML
    private Label inserer;
    @FXML
    private Label codeLabel;

    public static String num;
    public static final String APPLICATION_ICON = "tg/esiba/groupe2/gab/client/gui/images/laBank.PNG";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLog.setVisible(false);
    }

    @FXML
    private void fermer(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    private void afficheValider(KeyEvent event) {
        btnLog.setVisible(true);
    }

    @FXML
    private void login(MouseEvent event) throws IOException {
        try {
            String compte = compteField.getText();
            num = compte;
            auth.clientAuthentification(compte);

            contentArea.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Menu.fxml"));
            MenuController menuC = loader.getController();
            loader.load();
            Parent root = loader.getRoot();

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (AuthentificationException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Login.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    public static Compte leCompte() {
        return compteD.getOneCompte(num);
    }

}
