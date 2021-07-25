/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.club;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import tn.connectapp.entities.club.Club;
import tn.connectapp.entities.club.Membership;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.services.club.MembershipService;


/**
 * FXML Controller class
 *
 * @author crist
 */
public class ClubSpaceController implements Initializable {
    
        ClubService cs = new ClubService();
        MembershipService ms = new MembershipService();
    
      @FXML
    private Tab ClubData;

    @FXML
    private Button joinClub;

    @FXML
    private Button updateClub;

    @FXML
    private ImageView logoImage;

    @FXML
    private Hyperlink mail;

    @FXML
    private Text phoneNumber;

    @FXML
    private Text univercityText;

    @FXML
    private Text establishmentText;

    @FXML
    private Text CategoryText;

    @FXML
    private Text CreationdateText;

    @FXML
    private Tab eventPane;

    @FXML
    private Button joinClub1;

    @FXML
    private TextField universitytf1;

    @FXML
    private Tab membersPane;

    @FXML
    private Pagination pagination;

    Club currentClub;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
        @FXML
    void AddMembersList(ActionEvent event) throws SQLException {
        
        for (Membership m : ms.ReadMembersClub(currentClub.getIdClub()))
        {
            
        }
        

    }
    
}
