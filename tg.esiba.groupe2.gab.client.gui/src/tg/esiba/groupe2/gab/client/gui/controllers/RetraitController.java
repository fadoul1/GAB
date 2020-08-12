package tg.esiba.groupe2.gab.client.gui.controllers;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tg.esiba.groupe2.gab.client.gui.controllers.AcceuilController.APPLICATION_ICON;
import static tg.esiba.groupe2.gab.client.gui.controllers.AcceuilController.leCompte;
import tg.esiba.groupe2.gab.dao.impl.CompteDao;
import tg.esiba.groupe2.gab.exception.MontantInsuffisantException;

/**
 * FXML Controller class
 *
 * @author fad
 */
public class RetraitController implements Initializable {

    @FXML
    private Label bienveue;
    @FXML
    private Button btn6;
    @FXML
    private Button btn5;
    @FXML
    private Button btn4;
    @FXML
    private Button btn3;
    @FXML
    private Button btn2;
    @FXML
    private Button btn1;
    @FXML
    private Label bienveue1;
    @FXML
    private TextField montantField;
    @FXML
    private FontAwesomeIcon back;
    @FXML
    private Pane retrait;
    @FXML
    private FontAwesomeIcon close;
    @FXML
    private Button valider;

    CompteDao compteD = new CompteDao();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btn6Action(MouseEvent event) throws IOException {
        try {
            compteD.faireRetrait(500000, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.getIcons().add(new Image(APPLICATION_ICON));

            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    @FXML
    private void btn5Action(MouseEvent event) throws IOException {
        try {
            compteD.faireRetrait(400000, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    @FXML
    private void btn4Action(MouseEvent event) throws IOException {
        try {
            compteD.faireRetrait(300000, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    @FXML
    private void btn3Action(MouseEvent event) throws IOException {
        try {
            compteD.faireRetrait(200000, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    @FXML
    private void btn2Action(MouseEvent event) throws IOException {
        try {
            compteD.faireRetrait(100000, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    @FXML
    private void btn1Action(MouseEvent event) throws IOException {
        try {
            compteD.faireRetrait(50000, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Menu.fxml"));
        retrait.getChildren().removeAll();
        retrait.getChildren().setAll(root);
    }

    @FXML
    private void fermer(MouseEvent event) {
        retrait.getScene().getWindow().hide();
    }

    @FXML
    private void valider(MouseEvent event) throws IOException {
        double montant = 0;
        try {
            montant = Double.valueOf(montantField.getText());
        } catch (NumberFormatException e) {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error_1.fxml"));
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

        try {
            compteD.faireRetrait(montant, leCompte());

            Parent acceuil = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/Acceuil.fxml"));
            retrait.getChildren().removeAll();
            retrait.getChildren().setAll(acceuil);

            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Succes.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        } catch (MontantInsuffisantException ex) {
            Parent root = FXMLLoader.load(getClass().getResource("/tg/esiba/groupe2/gab/client/gui/alertes/Error.fxml"));

            Stage stage = new Stage();
            stage.getIcons().add(new Image(APPLICATION_ICON));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
    }

}
