/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.club;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;
import tn.connectapp.entities.club.Category;
import tn.connectapp.services.club.CategoryService;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.utils.commun.InputControl;

/**
 * FXML Controller class
 *
 * @author crist
 */
public class AddCategoryController implements Initializable {

    private ClubService cs;
    private CategoryService cats;
    Date sysdate = new Date(System.currentTimeMillis());
    
    @FXML
    private AnchorPane addCategoryPane;
    @FXML
    private Pane addCategrytf;
    @FXML
    private Button resetCateButton;
    @FXML
    private Button saveCatButton;
    @FXML
    private TextField categorynametf;
    @FXML
    private TextArea catedescriptiontf;
    @FXML
    private ImageView exittf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    public void addCategory(ActionEvent event) {
        String name = "";
        String description = "";
        String category = "";
        Long currentUser = 1234566L;

        Date sysdate = new Date(System.currentTimeMillis());

        if (InputControl.isNull(categorynametf.getText())) {
            InputControl.genAlert("101", "ERROR", "Name", "", "").show();
        } else {
            if (!InputControl.isString(categorynametf.getText())) {
                InputControl.genAlert("201", "ERROR", categorynametf.getText(), "", "").show();
            } else {
                try {
                    cats.createCategory(new Category(null, categorynametf.getText(), currentUser, sysdate, "EXPL", catedescriptiontf.getText(),null));

                } catch (NumberFormatException | SQLException ex) {
                    InputControl.genAlert("300", "ERROR", "", "", "").show();
                }
                 addCategoryPane.getParent().setUserData(categorynametf.getText());

                addCategoryPane.setVisible(false);
                InputControl.genAlert("500", "CONFIRMATION", "Category", "", "").show();

            }
        }

    }

    @FXML
    private void closeCurrent(MouseEvent event1) {
        addCategoryPane.setVisible(false);
    }

    @FXML
    private void resetCurrent(ActionEvent event) {
        categorynametf.setText("");
        catedescriptiontf.setText("");
    }
    
}
