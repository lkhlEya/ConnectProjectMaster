/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.refund;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import tn.connectapp.entities.refund.Remboursement;
import tn.connectapp.services.refund.RembousementService;


/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class ArchiveEntrepriseController implements Initializable {

    @FXML
    private TableView<Remboursement> tabView;
    @FXML
    private TableColumn<Remboursement, Date> colDate;
    @FXML
    private TableColumn<Remboursement, String> colDemande;

    private RembousementService rembousementService = new RembousementService();
    @FXML
    private TableColumn<Remboursement, String> colEtat;
    @FXML
    private TableColumn<Remboursement, String> colNom;
    @FXML
    private TableColumn<Remboursement, String> colEmail;
    @FXML
    private TableColumn<Remboursement, String> colVille;
    @FXML
    private TableColumn<Remboursement, Integer> colGSM;
    @FXML
    private TableColumn<Remboursement, String> colClub;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField editSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Remboursement> list = rembousementService.findNotEnAttente();

            ObservableList<Remboursement> data = FXCollections.observableArrayList(list);

            colDate.setCellValueFactory(new PropertyValueFactory<Remboursement, Date>("date"));
            colDemande.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("demande"));
            colEtat.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("Etatdemande"));
            colNom.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("nom"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("email"));
            colVille.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("ville"));
            colClub.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("club"));
            colGSM.setCellValueFactory(new PropertyValueFactory<Remboursement, Integer>("telephone"));
            // add action button

            tabView.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ListDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void clickSearch(ActionEvent event) {

        try {
            String text = editSearch.getText();

            List<Remboursement> list = rembousementService.findNotEnAttente();

            ObservableList<Remboursement> data = FXCollections.observableArrayList(list);
            FilteredList<Remboursement> filteredData = new FilteredList<>(data, p -> true);

            filteredData.setPredicate(remb -> {
                // If filter text is empty, display all persons.
                if (text == null || text.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = text.toLowerCase();

                if (remb.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (remb.getClub().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (remb.getDemande().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });

            tabView.setItems(filteredData);
        } catch (SQLException ex) {
            Logger.getLogger(ListDemandeEntrepriseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
