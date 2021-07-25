/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.club;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import tn.connectapp.entities.club.Club;
import tn.connectapp.services.club.CategoryService;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.utils.commun.InputControl;


/**
 * FXML Controller class
 *
 * @author crist
 */
public class UpdateClubController implements Initializable {

    
    private ClubService cs;
    private CategoryService cats;
    Date sysdate = new Date(System.currentTimeMillis());
    
    
    @FXML
    private AnchorPane updatePane;
    @FXML
    private Button resetButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private TextField nametf;
    @FXML
    private TextField universitytf;
    @FXML
    private TextField establishmenttf;
    @FXML
    private TextArea descriptiontf;
    @FXML
    private Button addCategory;
    @FXML
    private ComboBox<String> categorytf;
    @FXML
    private DatePicker creationdatetf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField phonenumbertf;
    @FXML
    private ImageView exittf1;
    @FXML
    private Button addLogoButtom;
    @FXML
    private TextField logoFile;
    @FXML
    private Pane opacityPane;
    private Pane addCategrytf;
    private TextField categorynametf;
    private TextArea catedescriptiontf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void resetClubForm(ActionEvent event) {
        categorytf.setValue("");
        creationdatetf.setValue(InputControl.asLocalDate(sysdate));
        emailtf.setText("");
        phonenumbertf.setText("");
        descriptiontf.setText("");
        logoFile.setText("");
    }

    @FXML
    private void addClub(ActionEvent event) {
    }

    @FXML
    private void showaddCategory(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddCategoryFXML.fxml"));
        updatePane.getChildren().set(2, pane);
    }

    @FXML
    private void closeCurrentUpdate(MouseEvent event) {
        updatePane.setVisible(false);
    }

    @FXML
    private void AddClubLogo(ActionEvent event) {
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Choose an image", "*.tif", "*.tiff", "*.bmp", "*.jpg", "*.jpeg", "*.gif", "*.png", "*.eps", "*.raw", "*.cr2", "*.nef", "*.orf", "*.sr2"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            logoFile.setText(selectedFile.getName());
        }
    }

    void UpdateClub(ActionEvent event) {

               String currentUser = "Me";
        Date creationDate;

        System.out.println("creationdatetf.toString() : " + creationdatetf.getValue());
        if (categorytf.getSelectionModel().isEmpty()) {
            InputControl.genAlert("103", "ERROR", "Category", "", "").show();
        } else if (null == creationdatetf.getValue()) {
            InputControl.genAlert("101", "ERROR", "Creation date", "", "").show();
        } else if (InputControl.isNull(emailtf.getText())) {
            InputControl.genAlert("101", "ERROR", "Email", "", "").show();
        } else if (InputControl.isNull(logoFile.getText())) {
            InputControl.genAlert("103", "Logo", "", "", "").show();
        } else {
            if (!InputControl.valiemail(emailtf.getText())) {
                InputControl.genAlert("204", "ERROR", emailtf.getText(), "", "").show();
            } else if (!InputControl.isGSM(phonenumbertf.getText())) {
                InputControl.genAlert("206", "ERROR", phonenumbertf.getText(), "", "").show();
            } else {
                try {
                    creationDate = InputControl.asDate(creationdatetf.getValue());
                    cs.updateClub(new Club(null, nametf.getText(), universitytf.getText(), establishmenttf.getText(), "ETUD", descriptiontf.getText(),
                            categorytf.getValue().toString(), creationDate, logoFile.getText(), Long.parseLong(phonenumbertf.getText()), emailtf.getText(),123456L,sysdate)
                    );

                    System.out.println("Update OK!!");
                    InputControl.genAlert("501", "CONFIRMATION", "Club", "", "").show();

                } catch (Exception ex) {
                    InputControl.genAlert("300", "ERROR", "", "", ex.getMessage()).show();

                }

            }
        }

    }
    
}
