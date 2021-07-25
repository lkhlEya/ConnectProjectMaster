/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.refund;

import java.io.IOException;
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
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.connectapp.entities.refund.Remboursement;
import tn.connectapp.services.refund.RembousementService;
import tn.connectapp.utils.refund.SendMail;


/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class ListDemandeEntrepriseController implements Initializable {

    @FXML
    private TableView<Remboursement> tabView;
    @FXML
    private TableColumn<Remboursement, Date> colDate;
    @FXML
    private TableColumn<Remboursement, String> colDemande;
   

    private RembousementService rembousementService = new RembousementService();
    @FXML
    private TableColumn<Remboursement, String> colAction;
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
            List<Remboursement> list = rembousementService.findEnAttente();

            ObservableList<Remboursement> data = FXCollections.observableArrayList(list);

            colDate.setCellValueFactory(new PropertyValueFactory<Remboursement, Date>("date"));
            colDemande.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("demande"));
      
            colNom.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("nom"));
            colEmail.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("email"));
            colVille.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("ville"));
            colClub.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("club"));
            colGSM.setCellValueFactory(new PropertyValueFactory<Remboursement, Integer>("telephone"));
            // add action button
            Callback<TableColumn<Remboursement, String>, TableCell<Remboursement, String>> cellFactory
                    = new Callback<TableColumn<Remboursement, String>, TableCell<Remboursement, String>>() {
                @Override
                public TableCell call(final TableColumn<Remboursement, String> param) {
                    final TableCell<Remboursement, String> cell = new TableCell<Remboursement, String>() {

                        final Button btnAccept = new Button("Accepter");
                        final Button btnRefus = new Button("Refuser");
                        HBox pane = new HBox(btnAccept, btnRefus);

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                Remboursement remboursement = getTableView().getItems().get(getIndex());
                                if (!remboursement.getEtatdemande().equals("En attente")) {
                                    btnAccept.setDisable(true);
                                    btnRefus.setDisable(true);
                                }
                                btnAccept.setOnAction(event -> {
                                    remboursement.setEtatdemande("Acceptée");
                                    changeEtat(remboursement);
                                });

                                btnRefus.setOnAction(event -> {
                                    remboursement.setEtatdemande("Refusée");
                                    changeEtat(remboursement);
                                });
                                setGraphic(pane);

                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };

            colAction.setCellFactory(cellFactory);

            tabView.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(ListDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeEtat(Remboursement remboursement) {
        // alert dialog confirmation
        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Attention, Vous etes sur de changer l 'état de cette demande??");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                rembousementService.update(remboursement);
                
                
                
                 tabView.getItems().clear();
                List<Remboursement> list = rembousementService.findEnAttente();

                ObservableList<Remboursement> data = FXCollections.observableArrayList(list);
                tabView.setItems(data);
                //envoi mail confirmation
                String subject;
                String text;
                if(remboursement.getEtatdemande().equals("Acceptée")) {
                     subject= "Acceptation remboursement";
                     text = "Bonjour ,\n Votre ..............";
                } else {
                      subject= "Rfus remboursement";
                     text = "Bonjour ,\n Votre ..............";
                }
                
                SendMail.send(remboursement.getEmail(), subject, text);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void clickSearch(ActionEvent event) {
        
        try {
            String text = editSearch.getText();
            
            List<Remboursement> list = rembousementService.findEnAttente();
            
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
                }
                else if (remb.getDemande().toLowerCase().contains(lowerCaseFilter)) {
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
