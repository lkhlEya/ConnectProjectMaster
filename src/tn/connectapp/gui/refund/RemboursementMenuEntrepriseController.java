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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class RemboursementMenuEntrepriseController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Text txtEnAttente;
    @FXML
    private Text txtArchive;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickEnAttente(MouseEvent event) {
           try {
         Parent root = FXMLLoader.load(getClass().getResource("ListDemandeEntreprise.fxml"));
         content.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickArchive(MouseEvent event) {
           try {
         Parent root = FXMLLoader.load(getClass().getResource("ArchiveEntreprise.fxml"));
         content.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
}
