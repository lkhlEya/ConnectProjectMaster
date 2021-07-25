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
//import java.sql.SQLException;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import tn.connectapp.util.DBConnect;

/**
 * FXML Controller class
 *
 * @author haifaj
 */
public class ReadOnlyController implements Initializable {
      @FXML
    private ComboBox choix = new ComboBox();
    @FXML
    private TextField company;
    @FXML
    private TextField tfRef;
    @FXML
    private TextField field;
    @FXML
    private TextField title;
    @FXML
    private DatePicker date = new DatePicker();
    @FXML
    private TextArea description;
     @FXML
    private TextArea start_date;
    @FXML
    private TextField supervisor;
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Internships reclamation = null;
    String ref;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList <String> matrialStatusList = FXCollections.observableArrayList("opinion","recommendation","Technical Problem");
         choix.setItems(matrialStatusList);
    }    
    
    
    void ReadTextField(String ref ,String type,String nam, String pren, String titre,String descriptionrip, String rep, String stat) {
        tfRef.setDisable(true);
        choix.setDisable(true);
        choix.setEditable(false);
        choix.setValue(type);
        company.setEditable(false);
        company.setText(nam);
        field.setEditable(false);
        field.setText(pren);
        title.setEditable(false);
        title.setText(titre);
        //date.setConverter(datee);
        description.setEditable(false);
        description.setText(descriptionrip);
        start_date.setEditable(false);
        start_date.setText(rep);
        supervisor.setEditable(false);
        supervisor.setText(stat);

    }
       
    
}
