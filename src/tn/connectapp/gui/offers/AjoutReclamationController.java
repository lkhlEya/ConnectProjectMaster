/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.offers;

//import com.jfoenix.controls.DatePicker;
//import com.jfoenix.controls.TextArea;
//import com.jfoenix.controls.TextField;
import tn.connectapp.entities.Offer.Internships;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import tn.connectapp.util.DBConnect;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private ComboBox choix = new ComboBox();

    @FXML
    private TextField tfCompany;

    @FXML
    private TextField tfRef;

    @FXML
    private TextField tfField;

    @FXML
    private TextField tfTitle;

    @FXML
    private DatePicker startDate = new DatePicker();

    @FXML
    private DatePicker endDate = new DatePicker();

    @FXML
    private TextArea taDescription;

    @FXML
    private TextField tfSupervisor;

    @FXML
    private TextField tfStatus;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Internships reclamation = null;
    private boolean update;
    int recId;
    String ref;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        ObservableList<String> matrialStatusList = FXCollections.observableArrayList("opinion", "recommendation", "Technical Problem");
        choix.setItems(matrialStatusList);

    }

    @FXML
    private void save(ActionEvent event) {

        connection = DBConnect.getConnect();
        String type = (String) choix.getValue();
        String name = tfCompany.getText();
        String ref = tfRef.getText();
        String pren = tfField.getText();
        String title = tfTitle.getText();
        String start_date = String.valueOf(startDate.getValue());
        String end_date = String.valueOf(endDate.getValue());
        String taDescriptionription = taDescription.getText();

        if (type == null || name.isEmpty() || ref.isEmpty() || pren.isEmpty() || title.isEmpty() || start_date.isEmpty() || end_date.isEmpty() || taDescriptionription.isEmpty()) {
            String tfTitle = "Insert";
            String msg = "Please Fill all Data";
            TrayNotification tray = new TrayNotification();
            AnimationType anim = AnimationType.POPUP;
            tray.setAnimationType(anim);
            tray.setTitle(tfTitle);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));

        } else if (update == false) {
            getQuery();
            insert();
            clean();
            String tfTitle = "Insert";
            String msg = "Insert succed";
            TrayNotification tray = new TrayNotification();
            AnimationType anim = AnimationType.POPUP;
            tray.setAnimationType(anim);
            tray.setTitle(tfTitle);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        } else {
            getQuery();
            update();
            String tfTitle = "Update";
            String msg = "Update succed";
            TrayNotification tray = new TrayNotification();
            AnimationType anim = AnimationType.POPUP;
            tray.setAnimationType(anim);
            tray.setTitle(tfTitle);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }

    }

    @FXML
    private void clean() {
        choix.setValue(null);
        tfCompany.setText(null);
        tfField.setText(null);
        tfTitle.setText(null);
        startDate.setValue(null);
        endDate.setValue(null);
        taDescription.setText(null);

    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO internship ( `ref`, `company`, `field`, `title`, `description`, `start_date`, `end_date`, `supervisor`, `status` ) VALUES (?,?,?,?,?,?,?,?,?)";

        } else {
            query = "UPDATE Internships SET "
                    + "`ref`=?,"
                    + "`company`=?,"
                    + "`field`=?,"
                    + "`title`=?,"
                    + "`description`=?,"
                    + "`start_date`=?,"
                    + "`end_date`=?,"
                    + "`supervisor`=?,"
                    + "`status`=?WHERE ref = '" + ref + "'";

        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfRef.getText());
            preparedStatement.setString(1, tfCompany.getText());
            preparedStatement.setString(2, tfField.getText());
            preparedStatement.setString(3, tfTitle.getText());
            preparedStatement.setString(4, taDescription.getText());
            preparedStatement.setString(5, String.valueOf(startDate.getValue()));
            preparedStatement.setString(6, String.valueOf(endDate.getValue()));
            preparedStatement.setString(7, (String) choix.getValue());
            preparedStatement.setString(8, tfSupervisor.getText());
            preparedStatement.setString(9, tfStatus.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void update() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfCompany.getText());
            preparedStatement.setString(2, tfField.getText());
            preparedStatement.setString(3, tfTitle.getText());
            preparedStatement.setString(4, taDescription.getText());
            preparedStatement.setString(5, String.valueOf(startDate.getValue()));
            preparedStatement.setString(6, String.valueOf(endDate.getValue()));
            preparedStatement.setString(7, (String) choix.getValue());
            preparedStatement.setString(8, tfSupervisor.getText());
            preparedStatement.setString(9, tfStatus.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setTextField(String ref, String type, String nam, String pren, String title, String taDescriptionrip) {
        tfRef.setText(ref);
        choix.setValue(type);
        tfCompany.setText(nam);
        tfField.setText(pren);
        tfTitle.setText(title);
        //date.setConverter(datee);
        taDescription.setText(taDescriptionrip);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
}
