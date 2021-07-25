/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.club;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import tn.connectapp.entities.club.Category;
import tn.connectapp.entities.club.Club;
import tn.connectapp.services.club.CategoryService;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.utils.commun.InputControl;


/**
 * FXML Controller class
 *
 * @author elkhl
 */
public class AddClubFXMLController implements Initializable {

    private ClubService cs;
    private CategoryService cats;
    Date sysdate = new Date(System.currentTimeMillis());
    
        @FXML
    private AnchorPane CreateClubPane;

    private String currentUser;

    @FXML
    private Button resetButton;


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
    private RadioButton licenceRadio;

    @FXML
    private ImageView exittf1;

    private Pane opacityPane;

    private Pane addCategrytf;


    @FXML
    private Button addLogoButtom;

    @FXML
    private TextField logoFile;
    
    
    @FXML
    private Button saveButton;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cs = new ClubService();
        cats = new CategoryService();
        ObservableList<String> list = FXCollections.observableArrayList();
        try
        {
            for (Category cat : cats.ReadListCategory("EXPL")) {
                list.add(cat.getCategoryName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddClubFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        categorytf.setItems(list);
        creationdatetf.setValue(InputControl.asLocalDate(sysdate));

    }
    

    @FXML
    public void addClub(ActionEvent event) {

        String currentUser = "Me";
        Date creationDate;

        System.out.println("creationdatetf.toString() : " + creationdatetf.getValue());
        if (InputControl.isNull(nametf.getText())) {
            InputControl.genAlert("101", "ERROR", "Name", "", "").show();
        } else if (InputControl.isNull(universitytf.getText())) {
            InputControl.genAlert("101", "ERROR", "University", "", "").show();
        } else if (InputControl.isNull(establishmenttf.getText())) {
            InputControl.genAlert("101", "ERROR", "Establishment", "", "").show();
        } else if (categorytf.getSelectionModel().isEmpty()) {
            InputControl.genAlert("103", "ERROR", "Category", "", "").show();
        } else if (null == creationdatetf.getValue()) {
            InputControl.genAlert("101", "ERROR", "Creation date", "", "").show();
        } else if (InputControl.isNull(emailtf.getText())) {
            InputControl.genAlert("101", "ERROR", "Email", "", "").show();
        } else if (!(licenceRadio.isSelected())) {
            InputControl.genAlert("102", "ERROR", "", "", "").show();
        } else if (InputControl.isNull(logoFile.getText())) {
            InputControl.genAlert("103", "Logo", "", "", "").show();
        } else {
            if (!InputControl.isString(nametf.getText())) {
                InputControl.genAlert("201", "ERROR", nametf.getText(), "", "").show();
            } else if (!InputControl.isString(universitytf.getText())) {
                InputControl.genAlert("201", "ERROR", universitytf.getText(), "", "").show();
            } else if (!InputControl.isString(establishmenttf.getText())) {
                InputControl.genAlert("201", "ERROR", establishmenttf.getText(), "", "").show();
            } else if (!InputControl.valiemail(emailtf.getText())) {
                InputControl.genAlert("204", "ERROR", emailtf.getText(), "", "").show();
            } else if (!InputControl.isGSM(phonenumbertf.getText())) {
                InputControl.genAlert("206", "ERROR", phonenumbertf.getText(), "", "").show();
            } else {
                try {
                    creationDate = InputControl.asDate(creationdatetf.getValue());
                    cs.createClub(new Club(null, nametf.getText(), universitytf.getText(), establishmenttf.getText(), "ETUD", descriptiontf.getText(),
                            categorytf.getValue().toString(), creationDate, logoFile.getText(), Long.parseLong(phonenumbertf.getText()), emailtf.getText(),123456L,sysdate)
                    );

                    System.out.println("INSERTION OK!!");
                    InputControl.genAlert("500", "CONFIRMATION", "Club", "", "").show();

                } catch (Exception ex) {
                    InputControl.genAlert("300", "ERROR", "", "", ex.getMessage()).show();

                }

            }
        }

    }

    @FXML
    void showaddCategory(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddCategoryFXML.fxml"));
        CreateClubPane.getChildren().set(2, pane);
    }


 

    @FXML
    void AddClubLogo(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Choose an image", "*.tif", "*.tiff", "*.bmp", "*.jpg", "*.jpeg", "*.gif", "*.png", "*.eps", "*.raw", "*.cr2", "*.nef", "*.orf", "*.sr2"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            logoFile.setText(selectedFile.getName());
        }
    }

    @FXML
    void resetClubForm(ActionEvent event) {
        nametf.setText("");
        universitytf.setText("");
        establishmenttf.setText("");
        categorytf.setValue("");
        creationdatetf.setValue(InputControl.asLocalDate(sysdate));
        emailtf.setText("");
        phonenumbertf.setText("");
        descriptiontf.setText("");
        logoFile.setText("");
        licenceRadio.setSelected(false);

    }
 


 

    /*  @FXML
    private void afficherPersonne(ActionEvent event) {
        try {

            String list = ps.afficherPersonnes().toString();

            FXMLLoader root = new FXMLLoader(getClass().getResource("./AfficherPersonneFXML.fxml"));

            Parent parent = root.load();
            
            AfficherPersonneFXMLController apc = root.getController();
            
            apc.setLbAfficher(list);

            //Lancement dans une nouvelle fenetre
//            Scene scene = new Scene(parent);
//            
//            Stage st = new Stage();
//            
//            st.setScene(scene);
//            
//            st.show();
            
            //Lancement dans la fenetre actuelle
            tfNom.getScene().setRoot(parent);
                       
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
  } */

    @FXML
    private void closeCurrent(MouseEvent event) {
        CreateClubPane.setVisible(false);
    }


   
}
