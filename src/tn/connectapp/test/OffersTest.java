/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author haifaj
 */
public class OffersTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
//        try {
            int t = 1;
            Parent parent;
            try {
                parent = FXMLLoader.load(getClass().getResource("/gui/Administrateur.fxml"));
            } catch (IOException ex) {
                System.out.println(ex);
            }
            parent = FXMLLoader.load(getClass().getResource("/gui/Administrateur.fxml"));

            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
//        } catch (IOException ex) {
////            System.out.println("Trouble reading from the file: " + ex.getMessage());
//
//            Logger.getLogger(OffersTest.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
        } catch (IOException ex) {
            Logger.getLogger(OffersTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
