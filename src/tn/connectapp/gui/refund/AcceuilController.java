/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.refund;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class AcceuilController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Button btnRemboursement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickRemboursement(ActionEvent event) {
        
        try {
         Parent root = FXMLLoader.load(getClass().getResource("RemboursementMenu.fxml"));
         content.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void clickAddRemboursement(ActionEvent event) {
          try {
         Parent root = FXMLLoader.load(getClass().getResource("RemboursementMenuEntreprise.fxml"));
         content.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickHome(ActionEvent event) {
          try {
      Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
         content.getChildren().setAll(root);
        } catch (Exception ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
