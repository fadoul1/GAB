/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.admin.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.APPLICATION_ICON;
import tg.esiba.groupe2.gab.dao.impl.AdministrateurDao;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.dao.impl.CompteDao;
import tg.esiba.groupe2.gab.entiies.Compte;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class DashboardController implements Initializable {

    CompteDao compteDao = new CompteDao();
    ClientDao clientDao = new ClientDao();
    AdministrateurDao adminDao = new AdministrateurDao();

    @FXML
    private BorderPane dashboard;

    @FXML
    private Button btnClients;
    @FXML
    private Button btnAdmins;
    @FXML
    private Button btnDeconnexion;

    @FXML
    private Button btnNouveauClient;
    @FXML
    private Button btnNouveauCompte;

    @FXML
    private Button infoClients;

    @FXML
    private Button infoComptes;

    @FXML
    private Button btninfoAdministrateurs;

    @FXML
    private TextField searchField;

    @FXML
    private Button btnChercher;

    @FXML
    private Button btnActualiser;

    @FXML
    private Label nbreClients;

    @FXML
    private Label nbreComptes;

    @FXML
    private Label nbreAdmins;

    @FXML
    private TableView comptesTables;

    @FXML
    private ProgressIndicator progresRecherche;

    @FXML
    TableColumn<Compte, Integer> num;

    @FXML
    TableColumn<Compte, Integer> adminCompte;

    @FXML
    TableColumn<Compte, String> titulaire;

    @FXML
    TableColumn<Compte, String> numCompte;

    @FXML
    TableColumn<Compte, Double> solde;

    @FXML
    TableColumn<Compte, Date> date;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progresRecherche.setStyle(" -fx-progress-color: #DB4346");
        progresRecherche.setVisible(false);
        nbreClients.setText(Integer.toString(clientDao.nbrClient()));
        nbreComptes.setText(Integer.toString(compteDao.nbrCompte()));
        nbreAdmins.setText(Integer.toString(adminDao.nbrAdmin()));
        comptesTables.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        searchField.setStyle("-fx-text-fill : white;"
                + "-fx-background-color : transparent;"
                + "-fx-border-width : 0px 0px 2px 0px;"
                + "-fx-border-color : #ffff");

        num.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
        num.setStyle("-fx-alignment : center");

        titulaire.setCellValueFactory(new PropertyValueFactory<>("titulaire"));
        titulaire.setStyle("-fx-alignment : center");
        numCompte.setCellValueFactory(new PropertyValueFactory<>("numeroCompte"));
        numCompte.setStyle("-fx-alignment : center");

        solde.setCellValueFactory(new PropertyValueFactory<>("solde"));
        solde.setStyle("-fx-alignment : center");

        date.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        date.setStyle("-fx-alignment : center");

        adminCompte.setCellValueFactory(new PropertyValueFactory<>("administrateur"));
        adminCompte.setStyle("-fx-alignment : center");

        ObservableList<Compte> comptes = compteDao.getAllCompte();
        comptesTables.setItems(comptes);
    }

    @FXML
    public void btnClientsAction() {
        try {
            dashboard = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/InfoClients.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(dashboard);
        stage.getIcons().add(new Image(APPLICATION_ICON));

        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Gestion des clients");
        stage.showAndWait();
    }

    @FXML
    public void btnComptesAction() {
        try {
            dashboard = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/InfoComptes.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(dashboard);

        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Gestion des comptes");
        stage.showAndWait();
    }

    @FXML
    public void btnDeconnexionAction() {
        dashboard.getScene().getWindow().hide();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(APPLICATION_ICON));

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    public void nouveauClient() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/NouveauClient.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Nouveau Client");
        stage.showAndWait();
    }

    @FXML
    public void nouveauCompte() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/NouveauCompte.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(APPLICATION_ICON));

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Nouveau Compte");
        stage.showAndWait();
    }

    @FXML
    public void infoClientsAction() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/InfoClients.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Gestion des Clients");
        stage.showAndWait();
    }

    @FXML
    public void infoComptesAction() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/InfoComptes.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Gestion des Comptes");
        stage.showAndWait();
    }

    @FXML
    public void infoAdministrateursAction() {

    }

    @FXML
    public void btnActualiserAction() {
        nbreClients.setText(Integer.toString(clientDao.nbrClient()));
        nbreComptes.setText(Integer.toString(compteDao.nbrCompte()));
        nbreAdmins.setText(Integer.toString(adminDao.nbrAdmin()));
        searchField.clear();
        ObservableList<Compte> comptes = compteDao.getAllCompte();
        comptesTables.setItems(comptes);
        progresRecherche.setVisible(false);

    }

    @FXML
    public void btnChercherAction() {
        progresRecherche.setVisible(true);
        String terme = searchField.getText();

        ObservableList<Compte> comptes = compteDao.searchCompte(terme);
        comptesTables.setItems(comptes);

    }

}
