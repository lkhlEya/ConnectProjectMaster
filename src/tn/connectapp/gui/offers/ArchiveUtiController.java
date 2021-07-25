/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.offers;

//import com.jfoenix.controls.TextField;
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
import java.util.function.Predicate;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
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
public class ArchiveUtiController implements Initializable {

    @FXML
    private TableView<Internships> ClaimsTable;
    @FXML
    private TableColumn<Internships, String> ref = new TableColumn<>("ref");
    @FXML
    private TableColumn<Internships, String> company = new TableColumn<>("company");
    @FXML
    private TableColumn<Internships, String> field = new TableColumn<>("field");
    @FXML
    private TableColumn<Internships, String> titre = new TableColumn<>("titre");
    @FXML
    private TableColumn<Internships, Date> date = new TableColumn<>("date");
    @FXML
    private TableColumn<Internships, String> description = new TableColumn<>("description");
    @FXML
    private TableColumn<Internships, String> start_date = new TableColumn<>("start_date");
    @FXML
    private TableColumn<Internships, String> type = new TableColumn<>("type");
    @FXML
    private TableColumn<Internships, String> supervisor = new TableColumn<>("supervisor");
    @FXML
    private TableColumn<Internships, String> status = new TableColumn<>("status");

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships reclamation = null;
    ObservableList<Internships> InternshipsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        // search();

    }

    @FXML
    private void refreshTable() {
        try {
            InternshipsList.clear();

            query = "SELECT * FROM Internship";
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
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void search() {
        connection = DBConnect.getConnect();
        refreshTable();

        ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        field.setCellValueFactory(new PropertyValueFactory<>("field"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_declaration"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        ClaimsTable.setItems(InternshipsList);

        FilteredList<Internships> filtereddata = new FilteredList<>(InternshipsList, b -> true);
        UtilisateurController uti = new UtilisateurController();
        uti.filtredfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate((Predicate<? super Internships>) rec -> {
                if ((newValue == null) || (newValue.isEmpty())) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (rec.getTitle().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } /*else if (rec.getType().toLowerCase().indexOf(lowercasefilter) != -1){
                 return true;
                 } else if (rec.getStatus().toLowerCase().indexOf(lowercasefilter) != -1){
                 return false;
                 }*/ else {
                    return false;
                }
            });
        });
        SortedList<Internships> sortedlist = new SortedList<>(filtereddata);
        sortedlist.comparatorProperty().bind(ClaimsTable.comparatorProperty());
        ClaimsTable.setItems(sortedlist);
    }

    private void loadDate() {

        connection = DBConnect.getConnect();
        refreshTable();

        ref.setCellValueFactory(new PropertyValueFactory<>("ref"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        field.setCellValueFactory(new PropertyValueFactory<>("field"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_declaration"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));

        //add cell of button edit 
        Callback<TableColumn<Internships, String>, TableCell<Internships, String>> cellFoctory = (TableColumn<Internships, String> param) -> {
            // make cell containing buttons
            final TableCell<Internships, String> cell;
            cell = new TableCell<Internships, String>() {
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

                            reclamation = ClaimsTable.getSelectionModel().getSelectedItem();
                            reclamation = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("readOnly.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ReadOnlyController readonlycontroller = loader.getController();
                            //repondrecontroller.setUpdate(true);
                            readonlycontroller.ReadTextField(reclamation.getREF(),
                                    reclamation.getCompany(),
                                    reclamation.getField(),
                                    reclamation.getTitle(),
                                    reclamation.getDescription(),
                                    reclamation.getSupervisor(),
                                    reclamation.getStatus(),
                                    reclamation.getStatus());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                    }

                    HBox managebtn = new HBox(EyeIcon);
                    managebtn.setStyle("-fx-alignment:center");
                    HBox.setMargin(EyeIcon, new Insets(2, 3, 0, 2));

                    setGraphic(managebtn);

                    setText(null);

                }

            };

            return cell;
        };
        status.setCellFactory(cellFoctory);
        ClaimsTable.setItems(InternshipsList);

    }

}
