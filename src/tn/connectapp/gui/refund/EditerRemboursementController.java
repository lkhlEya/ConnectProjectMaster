/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.refund;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.connectapp.entities.refund.Remboursement;
import tn.connectapp.services.refund.RembousementService;

/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class EditerRemboursementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField Tnom;
    @FXML
    private TextField Tclub;
    @FXML
    private TextField Temail;
    @FXML
    private TextField Tville;
    @FXML
    private TextField Ttelephone;
    @FXML
    private TextArea Tdemande;

    RembousementService r1;
    @FXML
    private Button btnCancel;

    private Remboursement remboursement;
    @FXML
    private AnchorPane content;
    @FXML
    private Button edit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        r1 = new RembousementService();
        //verifier input number téléphone
        Ttelephone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    Ttelephone.setText(newValue.replaceAll("[^\\d]", ""));
                }

                if (Ttelephone.getText().length() > 8) {
                    String s = Ttelephone.getText().substring(0, 8);
                    Ttelephone.setText(s);
                }
            }
        });

    }

    public void receiveRembourcement(Remboursement remboursement) {
        this.remboursement = remboursement;
        Tnom.setText(remboursement.getNom());
        Tclub.setText(remboursement.getClub());
        Temail.setText(remboursement.getEmail());
        Tville.setText(remboursement.getVille());
        Ttelephone.setText(String.valueOf(remboursement.getTelephone()));
        Tdemande.setText(remboursement.getDemande());
    }

    @FXML
    void modifierDemande(ActionEvent event) {

        Integer idUser = 1;
        String nom1 = Tnom.getText();
        String club1 = Tclub.getText();
        String email1 = Temail.getText();
        String ville1 = Tville.getText();

        String aux2 = Ttelephone.getText();

        String demande1 = Tdemande.getText();

        boolean valid = true;

        if (nom1.isEmpty()) {
            showAlerte("Nom réquis");

            valid = false;
            return;
        }
        if (club1.isEmpty()) {
            showAlerte("Club réquis");

            valid = false;
            return;
        }
        if (email1.isEmpty()) {
            showAlerte("Email réquis");

            valid = false;
            return;
        }
        if (!email1.isEmpty()) {
            Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
            if (!regex.matcher(email1).matches()) {
                showAlerte("Email invalid");

                valid = false;
                return;
            }
        }
        if (ville1.isEmpty()) {
            showAlerte("Ville réquis");

            valid = false;
            return;
        }
        if (aux2.isEmpty()) {
            showAlerte("GSM réquis");

            valid = false;
            return;
        }
        if (!aux2.isEmpty()) {
            if (aux2.length() < 8) {
                showAlerte("GSM doit conentir 8 chiffres");

                valid = false;
                return;
            }

        }
        if (demande1.isEmpty()) {
            showAlerte("Demande réquis");

            valid = false;
            return;
        }
        if (valid) {
            int telephone1 = Integer.parseInt(aux2);
            try {
                remboursement.setNom(nom1);
                remboursement.setClub(club1);
                remboursement.setVille(ville1);
                remboursement.setTelephone(telephone1);
                remboursement.setDemande(demande1);
                remboursement.setEmail(email1);
                r1.update(remboursement);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDemande.fxml"));
                Parent root = loader.load();

          

                content.getChildren().setAll(root);
            } catch (Exception ex) {
                Logger.getLogger(AjouterRemboursementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //clear input
    @FXML
    private void clear(ActionEvent event) {

        Tnom.setText(remboursement.getNom());
        Tclub.setText(remboursement.getClub());
        Temail.setText(remboursement.getEmail());
        Tville.setText(remboursement.getVille());
        Ttelephone.setText(String.valueOf(remboursement.getTelephone()));
        Tdemande.setText(remboursement.getDemande());
    }

    // afficher alerte pour validation form
    private void showAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(message);
        //   alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }

}
