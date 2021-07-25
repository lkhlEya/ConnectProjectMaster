/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.user;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.connectapp.entities.user.User;

/**
 * FXML Controller class
 *
 * @author Wissal
 */
public class ViewUsersController implements Initializable {

    @FXML
    private AnchorPane AP1Admin;
    @FXML
    private ChoiceBox<?> btnDeconnexion;
    @FXML
    private ImageView iconeAdmin;
    @FXML
    private ImageView logoAdmin;
    @FXML
    private ComboBox<?> SelectTeam;
    @FXML
    private Hyperlink APmenu;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAddUser;
    @FXML
    private TableView<User> TableView1;
    @FXML
    private TableColumn<User, String> TCfirstName;
    @FXML
    private TableColumn<User, String> tcLastName;
    @FXML
    private TableColumn<User, String> tcClub;
    @FXML
    private TableColumn<User, String> tcEmail;
    @FXML
    private TableColumn<User, String> tcPassword;
    @FXML
    private TableColumn<?, ?> tcDateBirth;
    @FXML
    private TableColumn<?, ?> tcGender;
    
    ObservableList<User> list= FXCollections.observableArrayList(
//            new User(1,"Wissal", "YERMANI", "clubX", "", "", "", ""),
//            new User(2,"Eya", "Lakha", "ClubY", "", "", "", "")
    );
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddUserAdmin(ActionEvent event) {
        
        
    }
    
}
