/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.offers;

//import com.jfoenix.controls.TextField;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.Button;
import tn.connectapp.gui.offers.AdministrateurController;
import tn.connectapp.entities.Offer.Internships;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.Observable;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.effect.BlurType;
//import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
//import jdk.nashorn.internal.objects.annotations.Property;
import tn.connectapp.util.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class UtilisateurController implements Initializable {

    private TableView<Internships> ClaimsTable;
    private TableColumn<Internships, String> ref = new TableColumn<>("ref");
    private TableColumn<Internships, String> company = new TableColumn<>("company");
    private TableColumn<Internships, String> field = new TableColumn<>("field");
    private TableColumn<Internships, String> description = new TableColumn<>("description");
    private TableColumn<Internships, String> titre = new TableColumn<>("title");
    private TableColumn<Internships, Date> start_date = new TableColumn<>("start_date");
    private TableColumn<Internships, Date> end_date = new TableColumn<>("end_date");
    private TableColumn<Internships, String> type = new TableColumn<>("type");
    private TableColumn<Internships, String> supervisor = new TableColumn<>("supervisor");
    private TableColumn<Internships, String> status = new TableColumn<>("status");
    @FXML
    public TextField filtredfield;

    @FXML
    private BorderPane bp;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships internship = null;
    ObservableList<Internships> InternshipsList = FXCollections.observableArrayList();
    @FXML
    private Button button1;
    @FXML
    private Button opinion;
    @FXML
    private Button opinion1;
    @FXML
    private ListView<?> lvInternships;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
        search();
        refreshTable();

    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void getAddView(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjoutReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    private void search() {
        connection = DBConnect.getConnect();
        refreshTable();

        ref.setCellValueFactory(new PropertyValueFactory<>("id"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        field.setCellValueFactory(new PropertyValueFactory<>("field"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_date.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        ClaimsTable.setItems(InternshipsList);

        FilteredList<Internships> filtereddata = new FilteredList<>(InternshipsList, b -> true);
        filtredfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filtereddata.setPredicate((Predicate<? super Internships>) rec -> {
                if ((newValue == null) || (newValue.isEmpty())) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (rec.getTitle().toLowerCase().contains(lowercasefilter)) {
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
        end_date.setCellValueFactory(new PropertyValueFactory<>("date_declaration"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        supervisor.setCellValueFactory(new PropertyValueFactory<>("supervisor"));

        //add cell of button edit 
        Callback<TableColumn<Internships, String>, TableCell<Internships, String>> cellFoctory = (TableColumn<Internships, String> param) -> {
            // make cell containing buttons
            final TableCell<Internships, String> cell;
            internship = ClaimsTable.getSelectionModel().getSelectedItem();
            cell = new TableCell<Internships, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    Button deleteIcon = new Button();
                    Button editIcon = new Button();
                    Button EyeIcon = new Button();

                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } /*else if (){
                        
                         editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#FDF1B8;"
                        );
                        
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                       
                        EyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                         EyeIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                          
                                
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("readOnly.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ReadOnlyController readonlycontroller = loader.getController();
                            //repondrecontroller.setUpdate(true);
                            readonlycontroller.ReadTextField(internship.getId(), internship.getType(),internship.getNom(), internship.getPrenom(), internship.getTitre(), internship.getDescription(), internship.getAnswer(), internship.getStatus());   
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                    }*/ else {
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff8c00;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#FDF1B8;"
                        );
                        EyeIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#B0E0E6;"
                        );
                        EyeIcon.setOnMouseClicked((MouseEvent event) -> {

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("readOnly.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ReadOnlyController readonlycontroller = loader.getController();
                            //repondrecontroller.setUpdate(true);
                            readonlycontroller.ReadTextField(internship.getREF(), internship.getSupervisor(), internship.getCompany(), internship.getField(), internship.getTitle(), internship.getDescription(), internship.getSupervisor(), internship.getStatus());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                internship = ClaimsTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM Internship WHERE ref  =" + internship.getREF();
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();

                            } catch (SQLException ex) {
                                Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            internship = ClaimsTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("AjoutInternships.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AjoutReclamationController ajoutinternshipcontroller = loader.getController();
                            ajoutinternshipcontroller.setUpdate(true);
                            ajoutinternshipcontroller.setTextField(internship.getREF(), internship.getStatus(), internship.getCompany(), internship.getField(), internship.getTitle(), internship.getDescription());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

                        });
                    }

                    HBox managebtn = new HBox(editIcon, deleteIcon, EyeIcon);
                    managebtn.setStyle("-fx-alignment:center");
                    HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                    HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
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

    @FXML
    private void archive(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        try {
            Pane view = object.load(getClass().getResource("ArchiveUti.fxml"));
            bp.setBottom(view);
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home(ActionEvent event) {
        FXMLLoader object = new FXMLLoader();
        try {
            Pane view = object.load(getClass().getResource("utilisateur.fxml"));
            bp.setBottom(view);
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateEntries(ActionEvent event) {
    }

}
