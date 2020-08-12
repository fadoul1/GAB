/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.admin.gui.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.APPLICATION_ICON;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.idAdmin;
import tg.esiba.groupe2.gab.dao.impl.AdministrateurDao;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.dao.impl.CompteDao;
import tg.esiba.groupe2.gab.entiies.Administrateur;
import tg.esiba.groupe2.gab.entiies.Client;
import tg.esiba.groupe2.gab.entiies.Compte;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class NouveauCompteController implements Initializable {

    @FXML
    private BorderPane nouveauCompte;
    
    @FXML
    private TextField soldeField;
    @FXML
    private Button btnValider;

    ClientDao clientDao = new ClientDao();
    Client client = new Client();

    CompteDao compteDao = new CompteDao();
    Compte compte = new Compte();

    AdministrateurDao adminDao = new AdministrateurDao();
    Administrateur admin = new Administrateur();
    @FXML
    private ComboBox<String> clientBox;
    @FXML
    private FontAwesomeIcon close;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {     
        List<String> client1 = clientDao.list();
        clientBox.getItems().addAll(client1);      
    }


    @FXML
    private void btnValiderAction(MouseEvent event) {
        String cliNom = clientBox.getValue().split("  ")[0];
        String cliPrenom = clientBox.getValue().split("  ")[1];

        int newClient = clientDao.getIdClient(cliNom, cliPrenom);

        Date dateCreation = Date.valueOf(LocalDate.now());

        try {
            int numAdmin = idAdmin();
            Double solde = Double.parseDouble(soldeField.getText());
            compte.setSolde(solde);
            compte.setNumeroAdministrateur(numAdmin);
            compte.setNumeroClient(newClient);
            compte.setDateCreation(dateCreation);
            compte.setNumeroCompte(compteDao.genererNumeroCompte());
            compteDao.creerCompte(compte);

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
        } catch (NumberFormatException e) {
            Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Error.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
        nouveauCompte.getScene().getWindow().hide();
    }

}
