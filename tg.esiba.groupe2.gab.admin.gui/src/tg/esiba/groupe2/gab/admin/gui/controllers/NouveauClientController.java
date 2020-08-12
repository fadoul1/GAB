/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.admin.gui.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.APPLICATION_ICON;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.idAdmin;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.entiies.Client;
import tg.esiba.groupe2.gab.exception.ClientException;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class NouveauClientController implements Initializable {

    @FXML
    private BorderPane nouveauClient;

    Button btnbalider;
    @FXML
    CheckBox cHomme;
    @FXML
    CheckBox cFemme;
    @FXML
    TextField nomField;
    @FXML
    TextField prenomField;
    @FXML
    TextField adresseField;
    @FXML
    TextField mailField;
    @FXML
    TextField phoneField;
    @FXML
    TextField soldeField;
    ErrorController erreur;
    @FXML
    private FontAwesomeIcon close;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void fermerAction() {

    }

    @FXML
    public void btnValiderAction() {
        ClientDao cliD = new ClientDao();
        Client cli = new Client();
        String sexe;
        int tel;
        double soldeInitial;
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String adresse = adresseField.getText();
        String mail = mailField.getText();

        if (cHomme.isSelected()) {
            sexe = "Masculin";
        } else {
            sexe = "Feminin";
        }
        try {
            tel = Integer.parseInt(phoneField.getText());
            cli.setAdresse(adresse);
            cli.setMail(mail);
            cli.setNom(nom);
            cli.setPrenom(prenom);
            cli.setSexe(sexe);
            cli.setTelephone(tel);
            soldeInitial = Double.parseDouble(soldeField.getText());
            try {
                int numAdmin = idAdmin();
                cliD.creerClient(cli, soldeInitial, numAdmin);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Succes.fxml"));
                } catch (IOException e) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                }
                Stage stage = new Stage();
                stage.getIcons().add(new Image(APPLICATION_ICON));
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.showAndWait();
            } catch (ClientException ex) {
               Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Client.fxml"));
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
        } catch (NumberFormatException e) {
            Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Error.fxml"));
                } catch (IOException e1) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e1);
                }
                Stage stage = new Stage();
                stage.getIcons().add(new Image(APPLICATION_ICON));
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.showAndWait();
        }

    }

    @FXML
    private void fermer(MouseEvent event) {
        nouveauClient.getScene().getWindow().hide();
    }
}
