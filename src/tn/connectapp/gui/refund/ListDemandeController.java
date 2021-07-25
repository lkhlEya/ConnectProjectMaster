/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.refund;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.connectapp.entities.refund.Remboursement;
import tn.connectapp.services.refund.RembousementService;

/**
 * FXML Controller class
 *
 * @author i_dkh
 */
public class ListDemandeController implements Initializable {

    @FXML
    private TableView<Remboursement> tabView;
    @FXML
    private TableColumn<Remboursement, Date> colDate;
    @FXML
    private TableColumn<Remboursement, String> colDemande;
    @FXML
    private TableColumn<Remboursement, String> colEtat;

    private RembousementService rembousementService = new RembousementService();
    @FXML
    private TableColumn<Remboursement, String> colAction;
    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Remboursement> list = rembousementService.findByUser(1);

            ObservableList<Remboursement> data = FXCollections.observableArrayList(list);

            colDate.setCellValueFactory(new PropertyValueFactory<Remboursement, Date>("date"));
            colDemande.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("demande"));
            colEtat.setCellValueFactory(new PropertyValueFactory<Remboursement, String>("Etatdemande"));
            // add action button
            Callback<TableColumn<Remboursement, String>, TableCell<Remboursement, String>> cellFactory
                    = new Callback<TableColumn<Remboursement, String>, TableCell<Remboursement, String>>() {
                @Override
                public TableCell call(final TableColumn<Remboursement, String> param) {
                    final TableCell<Remboursement, String> cell = new TableCell<Remboursement, String>() {

                        final Button btnDelete = new Button("Supprimer");
                      
                        final Button btnEdit = new Button("Editer");
                        HBox pane = new HBox(btnDelete, btnEdit);

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btnDelete.setStyle("-fx-background-color: #1a6aeb");
                                Remboursement remboursement = getTableView().getItems().get(getIndex());
                                if (!remboursement.getEtatdemande().equals("En attente")) {
                                    btnDelete.setDisable(true);
                                    btnEdit.setDisable(true);
                                }
                                btnDelete.setOnAction(event -> {
                                    supprimer(remboursement);
                                });

                                btnEdit.setOnAction(event -> {
                                    editer(remboursement);
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

    public void supprimer(Remboursement remboursement) {
        // alert dialog confirmation
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Attention, Vous etes sur de supprimer??");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                rembousementService.delete(remboursement.getId());
                tabView.getItems().remove(remboursement);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // yhez men liste  l editer . men itnerface l oihra
    private void editer(Remboursement remboursement) {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditerRemboursement.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            EditerRemboursementController scene2Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            scene2Controller.receiveRembourcement(remboursement);

            content.getChildren().setAll(root);
            //Show scene 2 in new window            
          /*  Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editer Demande");
            stage.show();
            ((Stage) tabView.getScene().getWindow()).close();*/
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
