/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.user;

import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Wissal
 */
public class StartPage {

    @FXML
    private ImageView background;
    @FXML
    private Button btnRN;
    @FXML
    private Text tfText1;
    @FXML
    private Text tfText11;
    @FXML
    private ImageView tfLogo;
    @FXML
    private Button btnSign;

 
 
      
    

    @FXML
    private void btnRegister(ActionEvent event) {
        //showMessageDialog(null, "TEST Button");
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("SignInTest01.fxml"));
            Parent parent = root.load();
            tfText11.getScene().setRoot(parent);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void SignIn(ActionEvent event) {
//        showMessageDialog(null, "TEST Button");
        
         try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("SignIn01.fxml"));
            Parent parent = root.load();
            tfText1.getScene().setRoot(parent);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
        
    
    
    
    
    }

    
    

