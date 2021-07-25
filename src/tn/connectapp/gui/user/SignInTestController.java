/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.user;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import static javax.swing.JOptionPane.showMessageDialog;
import tn.connectapp.entities.user.User;
import tn.connectapp.services.user.UserService;
import tn.connectapp.utils.commun.MyConnection;

/**
 * FXML Controller class
 *
 * @author Wissal
 */
public class SignInTestController implements Initializable {

    @FXML
    private AnchorPane PAtiltle;
    @FXML
    private AnchorPane PA1;
    @FXML
    private TextField tfFirstName;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfLastName;
    @FXML
    private Label txtGender;
    @FXML
    private RadioButton rbM;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private RadioButton rbw;
    @FXML
    private DatePicker tfdate;
    @FXML
    private Text titleSave;
    @FXML
    private Button bntCancel;
    @FXML
    private Button btnSave;

    public UserService us ;
     public Connection cnx ;
    @FXML
    private TextField tfClub;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx =     MyConnection.getInstance().getCnx();
        us = new UserService();
    }    
      
    

    @FXML
    private void bActionCancel(ActionEvent event) {
         showMessageDialog(null, "registration canceled");
        
         try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("StartPage.fxml"));
            Parent parent = root.load();
            titleSave.getScene().setRoot(parent);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void btnAddUser(ActionEvent event) {
        
        
        
            String FirstName = tfFirstName.getText();
            String LaststName = tfLastName.getText();
            String Email = tfEmail.getText();
            String Password = tfPassword.getText();
            String  Date = "01/01/2020" ;
            //Callback<DatePicker, DateCell> Date_of_Birth = tfdate.getDayCellFactory();
            String Club = tfClub.getText();
            String Gender = "gender" ;
           // String Gender = Gender.getselectedToggle();
            
            //showMessageDialog( tfFirstName.getText() ) ;
        //System.out.println("Date :"+tfdate.getDayCellFactory() ) ;
        //System.out.println("Genre :"+tfLastName.getTypeSelector().get) ;
            
             try {
            us.addUser(new User( tfFirstName.getText(), tfLastName.getText(),  tfClub.getText(), tfEmail.getText(),
                    tfPassword.getText() ,Date , Gender  ));
            
             showMessageDialog(null, "registration Done");
             tfFirstName.setText("");
             tfLastName.setText("");
             tfClub.setText("");
             tfEmail.setText("");
             tfPassword.setText("");
            
        } catch (SQLException ex) {
           System.out.println(ex);
        }
             
    }

    @FXML
    private void rbmAction(ActionEvent event) {
        
        String message="";
        if(rbM.isSelected()){
            message+= rbM.getText()+ "\n";
            System.out.println(" Man");
        }
    }

    @FXML
    private void rbwAction(ActionEvent event) {
       
        String message="";
        if(rbw.isSelected()){
            message+= rbw.getText()+ "\n";
            System.out.println("Woman");
        }
    }
    
}
