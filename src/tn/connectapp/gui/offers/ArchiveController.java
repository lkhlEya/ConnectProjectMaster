/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.offers;

import tn.connectapp.gui.offers.AdministrateurController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.connectapp.entities.Offer.Internships;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.connectapp.util.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class ArchiveController implements Initializable {

    @FXML
    private TableView<Internships> ClaimsTable;
    @FXML
    private TableColumn<Internships, String> tcRef = new TableColumn<>("ref");
    @FXML
    private TableColumn<Internships, String> tcCompany = new TableColumn<>("company");
    @FXML
    private TableColumn<Internships, String> tcField = new TableColumn<>("field");
    @FXML
    private TableColumn<Internships, String> tcTitle = new TableColumn<>("titre");
    @FXML
    private TableColumn<Internships, String> tcDescription = new TableColumn<>("description");
    @FXML
    private TableColumn<Internships, Date> tcStartDate = new TableColumn<>("start_date");
    @FXML
    private TableColumn<Internships, Date> tcEndDate = new TableColumn<>("end_date");
    @FXML
    private TableColumn<Internships, String> supervisor = new TableColumn<>("supervisor");
    @FXML
    private TableColumn<Internships, String> tcStatus = new TableColumn<>("status");

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships internship = null;

    ObservableList<Internships> InternshipsList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    private void refreshTable() {
        try {
            InternshipsList.clear();

            query = "SELECT * FROM Internship where status = ARCH";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InternshipsList.add(new Internships(
                        resultSet.getString("ref"),
                        resultSet.getString("company"),
                        resultSet.getString("field"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date"),
                        resultSet.getString("type"),
                        resultSet.getString("supervisor"),
                        resultSet.getString("status")));

                ClaimsTable.setItems(InternshipsList);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RecommendationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {

        connection = DBConnect.getConnect();
        refreshTable();

        tcRef.setCellValueFactory(new PropertyValueFactory<>("ref"));
        tcCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        tcField.setCellValueFactory(new PropertyValueFactory<>("field"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcStartDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        tcEndDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        //add cell of button edit 
        Callback<TableColumn<Internships, String>, TableCell<Internships, String>> cellFoctory = (TableColumn<Internships, String> param) -> {
            // make cell containing buttons
            final TableCell<Internships, String> cell = new TableCell<Internships, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    FontAwesomeIconView EyeIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        EyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#B0E0E6;"
                        );

                        EyeIcon.setOnMouseClicked((MouseEvent event) -> {

                            internship = ClaimsTable.getSelectionModel().getSelectedItem();
                            internship = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("readOnly.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ReadOnlyController readonlycontroller = loader.getController();
                            //repondrecontroller.setUpdate(true);
                            readonlycontroller.ReadTextField(internship.getREF(), "recommendation", 
                                    internship.getCompany(), 
                                    internship.getField(), 
                                    internship.getTitle(), 
                                    internship.getDescription(), 
                                    internship.getSupervisor(), 
                                    internship.getStatus());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                    }

                    HBox managebtn = new HBox(EyeIcon);
                    managebtn.setStyle("-fx-alignment:center");
                    HBox.setMargin(EyeIcon, new Insets(2, 2, 0, 3));

                    setGraphic(managebtn);

                    setText(null);

                }

            };

            return cell;
        };
        tcStatus.setCellFactory(cellFoctory);
        ClaimsTable.setItems(InternshipsList);

    }

}
