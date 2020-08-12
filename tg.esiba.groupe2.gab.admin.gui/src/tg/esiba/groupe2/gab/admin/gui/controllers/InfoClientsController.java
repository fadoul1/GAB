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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.APPLICATION_ICON;
import tg.esiba.groupe2.gab.dao.impl.ClientDao;
import tg.esiba.groupe2.gab.entiies.Client;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class InfoClientsController implements Initializable {

    @FXML
    private BorderPane infoClient;
    @FXML
    private FontAwesomeIcon icomptes;
    @FXML
    private Button btnNouveau;
    @FXML
    private FontAwesomeIcon iclients;
    @FXML
    private Button btnModifier;
    @FXML
    private FontAwesomeIcon iadmins;
    @FXML
    private Button btnSupprimer;
    @FXML
    private HBox h1;
    @FXML
    private HBox h2;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField telField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField searchField;
    @FXML
    private Button btnChercher;
    @FXML
    private Button btnActualiser;
    @FXML
    private ProgressIndicator progresRecherche;
    @FXML
    private TableView<Client> clientTables;
    @FXML
    private TableColumn<Client, Integer> num;
    @FXML
    private TableColumn<Client, String> nom;
    @FXML
    private TableColumn<Client, String> prenom;
    @FXML
    private TableColumn<Client, String> sexe;
    @FXML
    private TableColumn<Client, Integer> tel;
    @FXML
    private TableColumn<Client, String> email;
    @FXML
    private TableColumn<Client, String> add;
    @FXML
    private TableColumn<Client, String> passe;

    ClientDao clientD = new ClientDao();
    Client client = new Client();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progresRecherche.setStyle(" -fx-progress-color: #DB4346");
        progresRecherche.setVisible(false);

        num.setCellValueFactory(new PropertyValueFactory<>("numeroClient"));
        num.setStyle("-fx-alignment : center");

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nom.setStyle("-fx-alignment : center");

        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenom.setStyle("-fx-alignment : center");

        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        sexe.setStyle("-fx-alignment : center");

        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tel.setStyle("-fx-alignment : center");

        add.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        add.setStyle("-fx-alignment : center");

        passe.setCellValueFactory(new PropertyValueFactory<>("passe"));
        passe.setStyle("-fx-alignment : center");

        email.setCellValueFactory(new PropertyValueFactory<>("mail"));
        email.setStyle("-fx-alignment : center");

        ObservableList<Client> clients = clientD.getAllClient();
        clientTables.setItems(clients);
        clientTables.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        h1.setVisible(false);
        h2.setVisible(false);
    }

    @FXML
    private void btnNouveauAction(MouseEvent event) {
        BorderPane root = null;
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
        stage.setTitle("Nouveau Client");
        stage.showAndWait();
    }

    @FXML
    private void btnModifierAction(MouseEvent event) {

        String nomClient = nomField.getText();
        String prenomClient = prenomField.getText();
        String adresse = adresseField.getText();
        String mail = mailField.getText();

        ObservableList<Client> clientSelected, allClients;
        allClients = clientTables.getItems();
        clientSelected = clientTables.getSelectionModel().getSelectedItems();
        int numClient = clientSelected.get(0).getNumeroClient();

        try {

            int telClient = Integer.parseInt(telField.getText());
            client.setAdresse(adresse);
            client.setMail(mail);
            client.setNom(nomClient);
            client.setPrenom(prenomClient);
            client.setTelephone(telClient);
            client.setNumeroClient(numClient);

            clientD.updateClient(client);
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Modifier.fxml"));
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

        this.btnActualiserAction(event);
    }

    @FXML
    private void btnSupprimerAction(MouseEvent event) {
        ObservableList<Client> clientSelected, allClients;
        allClients = clientTables.getItems();
        clientSelected = clientTables.getSelectionModel().getSelectedItems();

        int numClient = clientSelected.get(0).getNumeroClient();
        clientD.supprimerClient(numClient);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Supprimer.fxml"));
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(new Image(APPLICATION_ICON));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

        this.btnActualiserAction(event);
    }

    @FXML
    private void btnChercherAction(MouseEvent event) {

        String terme = searchField.getText();
        progresRecherche.setVisible(true);
        ObservableList<Client> clients = clientD.searchClient(terme);
        clientTables.setItems(clients);
    }

    @FXML
    private void btnActualiserAction(MouseEvent event) {
        h1.setVisible(false);
        h2.setVisible(false);

        ObservableList<Client> clients = clientD.getAllClient();
        clientTables.setItems(clients);
        progresRecherche.setVisible(false);
    }

    @FXML
    private void clientTablesAction(MouseEvent event) {

        h1.setVisible(true);
        h2.setVisible(true);

        ObservableList<Client> clientSelected, allClients;
        allClients = clientTables.getItems();
        clientSelected = clientTables.getSelectionModel().getSelectedItems();
        nomField.setText(clientSelected.get(0).getNom());
        prenomField.setText(clientSelected.get(0).getPrenom());
        adresseField.setText(String.valueOf(clientSelected.get(0).getAdresse()));
        telField.setText(String.valueOf(clientSelected.get(0).getTelephone()));
        mailField.setText(String.valueOf(clientSelected.get(0).getMail()));
    }

}
