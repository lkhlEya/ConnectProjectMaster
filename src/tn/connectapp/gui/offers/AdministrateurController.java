/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.offers;

//import com.sun.deploy.util.StringUtils;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import tn.connectapp.entities.Offer.Internships;
import static tn.connectapp.gui.offers.AdministrateurController.title;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
//import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.event.Event;
import javafx.scene.control.TextField;

//import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
//import pfa.PFA;
import tn.connectapp.util.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class AdministrateurController implements Initializable {

    @FXML
//    private BorderPane bp;

    Connection cnx = DBConnect.getConnect();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Internships internship = null;

    ObservableList<String> InternshipsList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> lvInternships;
    @FXML
    private TextField filtredfield;
    @FXML
    private Pane btnRefresh;
    @FXML
    private Button opinion;
    @FXML
    private Button opinion1;
    @FXML
    private Button button1;

    public static String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String title = lvInternships.getSelectionModel().getSelectedItem();

        // TODO
        //            loadDate();
        refresh(cnx, InternshipsList);
        refresh((Connection) cnx, this.InternshipsList);
        lvInternships.setItems(this.InternshipsList);

    }

    public void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
//
//    @FXML
//    public void handleMouseClick(MouseEvent arg0) {
//
//    }

    @FXML
    public void handleMouseEvent(MouseEvent event) {
        // handling click on items 
        String t = lvInternships.getSelectionModel().getSelectedItem();
        System.out.println(t);

        FXMLLoader loader = new FXMLLoader();
        try {
//            Pane view = loader.load(getClass().getResource("internshipInfos.fxml"));
            Parent parent = FXMLLoader.load(getClass().getResource("internshipInfos.fxml"));
            Scene scene = new Scene(parent);

//            internshipInfosController iController = loader.getController();
//            iController.transferMessage(t);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            title = t;
            System.out.println("this is the second title "+title);
//            internshipInfosController iController = loader.getController();
//            iController.transferMessage(title);

//            bp.setBottom(parent);
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
        };
//        internshipInfosController archiveuticontroller = loader.getController();
//        internshipInfosController.Internships t = null;
//        archiveuticontroller.transferMessage(t);
    }
    

    /**
     *
     * @param cnx
     * @param InternshipsList
     */
    @FXML
    public void refresh(Connection cnx, ObservableList<String> InternshipsList) {
        try {
            cnx = DBConnect.getConnect();
            int test = 1;
            System.out.println("this is the title = "+title);
            Statement statement = cnx.createStatement();
            SQLException e = null;
            System.out.println(e);
            ResultSet rs = statement.executeQuery("SELECT title from internship");
            while (rs.next()) {
                try {
                    String t = rs.getString("title");
                    InternshipsList.add(t);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
//                InternshipsList.add(new String(rs.getString(title)));
//                InternshipsList.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
//    private void refresh() {
//        try {
//
//            Connection cnx = DBConnect.getConnect();
//            ObservableList<String> items = FXCollections.observableArrayList();
//            ListView<String> list = new ListView<>(items);
//
//            String sql = "SELECT title FROM INTERNSHIP; ";
//            PreparedStatement ste = cnx.prepareStatement(sql);
//            ResultSet rs = ste.executeQuery();
//
//            while (rs.next()) {
//                String title = rs.getString("title");
//                lvInternship.getItems().add("title");
//
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//
//        }
//    }

    private void loadDate() throws SQLException {

//        connection = DBConnect.getConnect();
//        refresh();
    }

}
