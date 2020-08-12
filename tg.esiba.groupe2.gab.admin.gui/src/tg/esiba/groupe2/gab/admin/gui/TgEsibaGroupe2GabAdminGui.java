/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.esiba.groupe2.gab.admin.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tg.esiba.groupe2.gab.admin.gui.controllers.LoginController;
import static tg.esiba.groupe2.gab.admin.gui.controllers.LoginController.APPLICATION_ICON;

/**
 *
 * @author fad
 */
public class TgEsibaGroupe2GabAdminGui extends Application {
    
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.getIcons().add(new Image(APPLICATION_ICON));
        stage.initStyle(StageStyle.TRANSPARENT);
        this.primaryStage = stage;
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
