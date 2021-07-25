/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.user;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Wissal
 */
public class ProfileUserController implements Initializable {

    @FXML
    private AnchorPane APtitleProf;
    @FXML
    private ImageView imgProfile;
    @FXML
    private Hyperlink hlClub;
    @FXML
    private ImageView logprofile;
    @FXML
    private Hyperlink hlEvent;
    @FXML
    private Hyperlink hlClaim;
    @FXML
    private Hyperlink hlHome;
    @FXML
    private AnchorPane APeditProf;
    @FXML
    private Text fNameProf;
    @FXML
    private ImageView iconeEdit;
    @FXML
    private Button inserImgProf;
    @FXML
    private Button insertCv;
    @FXML
    private Hyperlink editProf;
    @FXML
    private Button btnClose;
    @FXML
    private Text lastNameProf;
    @FXML
    private AnchorPane APcadre;
    @FXML
    private Rectangle cadreEdit;
    private Button btnSave;
    @FXML
    private Text titleSave;
    @FXML
    private DatePicker tfdate;
    @FXML
    private RadioButton rbw;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton rbM;
    @FXML
    private Label txtGender;
    @FXML
    private TextField tfClub;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfFirstName;
    @FXML
    private Button btnupdate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
      // btnSave.setVisible(false);
       btnupdate.setVisible(false);
        tfFirstName.setVisible(false);
        
        tfFirstName.setVisible(false);
        txtGender.setVisible(false);
        tfClub.setVisible(false);
        tfLastName.setVisible(false);
        tfEmail.setVisible(false);
        tfPassword.setVisible(false);
        tfFirstName.setVisible(false);
        rbM.setVisible(false);
        tfdate.setVisible(false);
        rbw.setVisible(false);
        titleSave.setVisible(false);
        
        
    }    

    public void myFunction(String text){
    
        fNameProf.setText(text);
    }
    
    @FXML
    private void buttonClose(ActionEvent event) {
//         Stage stage = (Stage) btnClose.getScene().getWindow();
//         stage.close();

       try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("StartPage1.fxml"));
            Parent parent = root.load();
            btnClose.getScene().setRoot(parent);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void buttonInsert(ActionEvent event) {
        
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Choose an image", "*.tif", "*.tiff", "*.bmp","*.jpg", "*.jpeg","*.gif", "*.png", "*.eps", "*.raw", "*.cr2", "*.nef", "*.orf", "*.sr2"));
        File selectedFile= fc.showOpenDialog(null);
       
         if (selectedFile != null){ inserImgProf.setText(selectedFile.getName());
        }
         }
    

    @FXML
    private void buttonInsertCV(ActionEvent event) {
        
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Choose a CV", "*.txt", "*.pdf"));
        File selectedFile= fc.showOpenDialog(null);
       
         if (selectedFile != null){ insertCv.setText(selectedFile.getName());
        }
    }

    @FXML
    private void hlEdit(ActionEvent event) {
        
        //btnSave.setVisible(true);
        btnupdate.setVisible(true);
        tfFirstName.setVisible(false);
        
        tfFirstName.setVisible(true);
        txtGender.setVisible(true);
        tfClub.setVisible(true);
        tfLastName.setVisible(true);
        tfEmail.setVisible(true);
        tfPassword.setVisible(true);
        tfFirstName.setVisible(true);
        rbM.setVisible(true);
        tfdate.setVisible(true);
        rbw.setVisible(true);
        titleSave.setVisible(true);
        
        
        
        
    }

    @FXML
    private void checkrbw(ActionEvent event) {
        
         String message="";
        if(rbM.isSelected()){
            message+= rbM.getText()+ "\n";
            System.out.println("Woman");
        }
    }

    @FXML
    private void checkrbm(ActionEvent event) {
        
         String message="";
        if(rbM.isSelected()){
            message+= rbM.getText()+ "\n";
            System.out.println(" Man");
        }
    }

    @FXML
    private void UpdateUser(ActionEvent event) {
        
    }
}

