/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.admin.gui.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.APPLICATION_ICON;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.idAdmin;
import tg.esiba.groupe2.gab.dao.impl.AdministrateurDao;
import tg.esiba.groupe2.gab.dao.impl.CompteDao;
import tg.esiba.groupe2.gab.entiies.Compte;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class InfoComptesController implements Initializable {

    @FXML
    private BorderPane infoCompte;
    @FXML
    private FontAwesomeIcon icomptes;
    @FXML
    private Button btnNouveau;
    @FXML
    private FontAwesomeIcon idepot;
    @FXML
    private Button btnDepot;
    @FXML
    private FontAwesomeIcon iadmins1;
    @FXML
    private Button btnSupprimer1;
    @FXML
    private HBox no1;
    @FXML
    private TextField titulaireField;
    @FXML
    private TextField numeroField;
    @FXML
    private TextField soldeField;
    @FXML
    private HBox no;
    @FXML
    private TextField montantField;
    @FXML
    private Button btnDepot2;
    @FXML
    private TextField searchField;
    @FXML
    private Button btnChercher;
    @FXML
    private Button btnActualiser;
    @FXML
    private ProgressIndicator progresRecherche;
    @FXML
    private TableView<Compte> comptesTables;
    @FXML
    private TableColumn<Compte, Integer> num;
    @FXML
    private TableColumn<Compte, String> titulaire;
    @FXML
    private TableColumn<Compte, String> numCompte;
    @FXML
    private TableColumn<Compte, Double> solde;
    @FXML
    private TableColumn<Compte, Date> date;

    CompteDao compteDao = new CompteDao();
    Compte compte = new Compte();
    AdministrateurDao admin = new AdministrateurDao();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        montantField.setDisable(true);
        btnDepot2.setDisable(true);
        btnDepot.setDisable(true);
        btnSupprimer1.setDisable(true);
        progresRecherche.setStyle(" -fx-progress-color: #DB4346");
        progresRecherche.setVisible(false);
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
        
        int numAdmin = idAdmin();

        ObservableList<Compte> comptes = admin.getCompteGeree(numAdmin);
        comptesTables.setItems(comptes);
        comptesTables.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        no.setVisible(false);
        no1.setVisible(false);
    }

    @FXML
    private void btnNouveauAction(MouseEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/NouveauCompte.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setTitle("Nouveau Compte");
        stage.showAndWait();
    }

    @FXML
    private void btnDepotAction(MouseEvent event) {
        ObservableList<Compte> compteSelected, allComptes;
        allComptes = comptesTables.getItems();
        compteSelected = comptesTables.getSelectionModel().getSelectedItems();
        try {
            double montant = Double.valueOf(montantField.getText());
            compte = compteSelected.get(0);
            compteDao.faireDepot(montant, compte);
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

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Depot.fxml"));
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

        this.btnActualiserAction(event);
    }

    @FXML
    private void btnSupprimerAction(MouseEvent event) {
        ObservableList<Compte> compteSelected, allComptes;
        allComptes = comptesTables.getItems();
        compteSelected = comptesTables.getSelectionModel().getSelectedItems();

        int numeroCompte = compteSelected.get(0).getIdCompte();
        compteDao.supprimerCompte(numeroCompte);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/admin/gui/alertes/Supprimer.fxml"));
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
        Stage stage = new Stage();
        stage.getIcons().add(new Image(APPLICATION_ICON));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

        this.btnActualiserAction(event);
    }

    @FXML
    private void btnChercherAction(MouseEvent event) {
        progresRecherche.setVisible(true);
        String terme = searchField.getText();

        ObservableList<Compte> comptes = compteDao.searchCompte(terme);
        comptesTables.setItems(comptes);
    }

    @FXML
    private void btnActualiserAction(MouseEvent event) {
        montantField.setDisable(true);
        btnDepot2.setDisable(true);
        btnDepot.setDisable(true);
        btnSupprimer1.setDisable(true);
        ObservableList<Compte> comptes = admin.getCompteGeree(1);
        comptesTables.setItems(comptes);
        searchField.clear();
        progresRecherche.setVisible(false);
        montantField.clear();

        no.setVisible(false);
        no1.setVisible(false);

    }

    @FXML
    private void comptesTablesAction(MouseEvent event) {
        no.setVisible(true);
        no1.setVisible(true);
        montantField.setDisable(false);
        titulaireField.setEditable(false);
        numeroField.setEditable(false);
        soldeField.setEditable(false);
        btnSupprimer1.setDisable(false);

        ObservableList<Compte> compteSelected, allComptes;
        allComptes = comptesTables.getItems();
        compteSelected = comptesTables.getSelectionModel().getSelectedItems();
        titulaireField.setText(compteSelected.get(0).getTitulaire());
        numeroField.setText(compteSelected.get(0).getNumeroCompte());
        soldeField.setText(String.valueOf(compteSelected.get(0).getSolde()));
    }

    @FXML
    private void btnDepotActiver(KeyEvent event) {
        btnDepot.setDisable(false);
        btnDepot2.setDisable(false);
    }

}
