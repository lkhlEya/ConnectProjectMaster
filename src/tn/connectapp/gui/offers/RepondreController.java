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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import tn.connectapp.util.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class RepondreController implements Initializable {

    @FXML
    private ComboBox choix = new ComboBox();
    @FXML
    private TextField company;
    @FXML
    private TextField tfRef;
    @FXML
    private TextField prenom;
    @FXML
    private TextField titre;
    @FXML
    private DatePicker startDate = new DatePicker();
    @FXML
    private TextArea taDescription;
    @FXML
    private TextArea answer;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Internships reclamation = null;
    private boolean update;
    String ref;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> matrialStatusList = FXCollections.observableArrayList("Initiation", "technicien", "PFE","CDI","CDD","CIVP");
        choix.setItems(matrialStatusList);
    }

    @FXML
    private void save(ActionEvent event) {

        connection = DBConnect.getConnect();
        String rep = answer.getText();

        if (rep.isEmpty()) {
            String titre = "Insert";
            String msg = "Please Fill all Data";
            TrayNotification tray = new TrayNotification();
            AnimationType anim = AnimationType.POPUP;
            tray.setAnimationType(anim);
            tray.setTitle(titre);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            getQuery();
            insert();
            String titre = "Insert";
            String msg = "Insert succed";
            TrayNotification tray = new TrayNotification();
            AnimationType anim = AnimationType.POPUP;
            tray.setAnimationType(anim);
            tray.setTitle(titre);
            tray.setMessage(msg);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));

        }

    }

    private void getQuery() {

        query = "UPDATE Internship SET `answer`=? WHERE ref = '" + ref + "'";

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void InProgressStatu() {
        connection = DBConnect.getConnect();
        query = "UPDATE Internship SET `status`='in progress' WHERE ref = '" + ref + "'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RepondreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DoneStatu() {
        connection = DBConnect.getConnect();
        query = "UPDATE Internship SET `status`='done' WHERE ref = '" + ref + "'";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RepondreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ReadTextField(String ref, String type, String nam, String pren, String title, String description, String rep) {
        tfRef.setDisable(true);
        choix.setDisable(true);
        choix.setEditable(false);
        choix.setValue(type);
        company.setEditable(false);
        company.setText(nam);
        prenom.setEditable(false);
        prenom.setText(pren);
        titre.setEditable(false);
        titre.setText(title);
        //date.setConverter(datee);
        taDescription.setEditable(false);
        taDescription.setText(description);
        answer.setEditable(false);
        answer.setText(rep);

    }

    void setTextField(String ref, String type, String nam, String pren, String title, String description, String rep) {
        tfRef.setDisable(true);
        choix.setDisable(true);
        choix.setEditable(false);
        choix.setValue(type);
        company.setEditable(false);
        company.setText(nam);
        prenom.setEditable(false);
        prenom.setText(pren);
        titre.setEditable(false);
        titre.setText(title);
        //date.setConverter(datee);
        taDescription.setEditable(false);
        taDescription.setText(description);

        answer.setText(rep);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
