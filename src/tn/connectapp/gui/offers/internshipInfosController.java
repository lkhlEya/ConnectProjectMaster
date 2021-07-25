/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.offers;

import tn.connectapp.gui.offers.AdministrateurController;
import tn.connectapp.entities.Offer.Internships;
import tn.connectapp.entities.Offer.Offer;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.connectapp.util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Thinkpad
 */
public class internshipInfosController implements Initializable {

    Connection cnx = DBConnect.getConnect();

    @FXML
    private Label lInternship;
    @FXML
    private Label lDescription;
    @FXML
    private Label lCompany;
    @FXML
    private Label lStartDate;
    @FXML
    private Label lEndDate;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfFamilyName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Label lSupervisor;
    @FXML
    private Button postuler;
    @FXML
    private Button uploadFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refresh();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(internshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() throws SQLException {
        cnx = DBConnect.getConnect();
        Statement statement = cnx.createStatement();
        String sql = "SELECT company, description, start_date, end_date, supervisor from internship where title = " + AdministrateurController.title;
        System.out.println(AdministrateurController.title);
        lInternship.setText(AdministrateurController.title);
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);

        try {
            while (rs.next()) {
                String company = rs.getString("company");
                String description = rs.getString("description");
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");
                String supervisor = rs.getString("supervisor");

                lCompany.setText(company);
                lDescription.setText(description);
                lStartDate.setText(start_date);
                lEndDate.setText(end_date);
                lSupervisor.setText(supervisor);

            }
        } catch (SQLException ex) {
            Logger.getLogger(internshipInfosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    public void upload(Stage s) {
        uploadFile.setOnAction(e
                -> {
            FileChooser file = new FileChooser();
            file.setTitle("Save Image");
            //System.out.println(pic.getId()); 
            file.setInitialDirectory(new File("C:\\Users\\Thinkpad\\OneDrive\\Bureau"));

            File file1 = file.showSaveDialog(s);
            System.out.println(file1);
        });

    }

    @FXML
    public void applyInternship(ActionEvent event) throws SQLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your Resume");
        // to do         

        String sql = "INSERT INTO candidats (`name`, `family_name`,`email`) VALUES (?,?,?);";

        PreparedStatement pre = cnx.prepareStatement(sql);

        pre = cnx.prepareStatement(sql);
        pre.setString(1, tfName.getText());
        pre.setString(2, tfFamilyName.getText());
        pre.setString(3, tfEmail.getText());
        pre.execute();

        pre.executeUpdate();
        System.out.println("INSERTION OK!!");
    }

//    public void transferMessage(String.Internship t) {
////        id.setText(t.getId());
////        author.setText(t.getAuthor());
////        publisher.setText(t.getPublisher());
////        id.setEditable(false);
////        isInEditMode = Boolean.TRUE;
//    }
//    
}
