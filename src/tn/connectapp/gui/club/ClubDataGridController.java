/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.gui.club;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import tn.connectapp.entities.club.Category;
import tn.connectapp.entities.club.Club;
import tn.connectapp.services.club.CategoryService;
import tn.connectapp.services.club.ClubService;
import tn.connectapp.utils.commun.InputControl;


/**
 * FXML Controller class
 *
 * @author crist
 */
public class ClubDataGridController implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList();
    ClubService cs = new ClubService();

    private CategoryService cats;
    Date sysdate = new Date(System.currentTimeMillis());

    @FXML
    private AnchorPane GridRootPane;

    @FXML
    private TableView<Club> activClubs;

    @FXML
    private TableColumn<Club, Long> idClubC;

    @FXML
    private TableColumn<Club, String> nameC;

    @FXML
    private TableColumn<Club, String> universityC;

    @FXML
    private TableColumn<Club, String> institueC;

    @FXML
    private TableColumn<Club, String> descriptionC;

    @FXML
    private TableColumn<Club, String> categoryC;

    @FXML
    private TableColumn<Club, Date> creationDateC;

    @FXML
    private TableColumn<Club, String> creationUserC;

    @FXML
    private TableColumn<Club, Boolean> selectingdch;

    @FXML
    private CheckBox selectAllList;

    @FXML
    private TableColumn<Club, Long> CreationUserId;

    @FXML
    private Button filtrate;

    @FXML
    private Pane opacityPane;

    @FXML
    private Pane addFilter;

    @FXML
    private ImageView exittf;

    @FXML
    private TextField nametf;

    @FXML
    private TextField universitytf;

    @FXML
    private TextField establishmenttf;

    @FXML
    private DatePicker creationdatetf;

    @FXML
    private TextField phonenumbertf;

    @FXML
    private Button resetButton;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> categorytf;

    @FXML
    private TextField emailtf;

    @FXML
    private TextField minmumber;

    @FXML
    private TextField maxmumber1;

    private static boolean checkedRow;

    @FXML
    private ImageView SearchClubKey;

    @FXML
    private TextField searchtf;

    @FXML
    private Tab activeClub;

    @FXML
    private Tab requeststab;

    @FXML
    private Tab archive;

    @FXML
    private MenuItem updateaction;

    @FXML
    private MenuItem ArchiveAction;

    @FXML
    private MenuItem AcceptAction;

    @FXML
    private MenuItem RejectAction;

    @FXML
    private MenuItem Activateaction;

    @FXML
    private ImageView exitsearchfiltre;

    private String status;

    public ObservableList<Club> clubList = FXCollections.observableArrayList();

    void setStatus(ActionEvent event) {

    }

    @FXML
    void checkBoxInitialize(ActionEvent event) {
        for (Club n : clubList) {
            n.setCheck(selectAllList.isSelected());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void addFilterPaneShow(ActionEvent event) {

        opacityPane.setVisible(true);
        addFilter.setVisible(true);
        cats = new CategoryService();
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            for (Category cat : cats.ReadListCategory(null)) {
                list.add(cat.getCategoryName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddClubFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        categorytf.setItems(list);

    }

    @FXML
    void closeCurrent(MouseEvent event) {
        opacityPane.setVisible(false);
        addFilter.setVisible(false);
    }

    @FXML
    void FiltrateClubs(ActionEvent event) {

        opacityPane.setVisible(false);
        addFilter.setVisible(false);

        Long numMin = null;
        Long numMax = null;
        clubList.clear();

        if (!InputControl.isNull(minmumber.getText())) {
            if (!InputControl.isNumber(minmumber.getText())) {
                InputControl.genAlert("207", "ERROR", minmumber.getText(), "", "").show();
            } else {
                numMin = Long.parseLong(minmumber.getText());
            }

        } else if (!InputControl.isNull(minmumber.getText())) {
            if (!InputControl.isNumber(maxmumber1.getText())) {
                InputControl.genAlert("207", "ERROR", maxmumber1.getText(), "", "").show();
            } else {
                numMax = Long.parseLong(maxmumber1.getText());
            }
        }

        Club criterianEntity = new Club();
        if (!InputControl.isNull(nametf.getText())) {
            criterianEntity.setName(nametf.getText());
        }
        if (!InputControl.isNull(universitytf.getText())) {
            criterianEntity.setUniversity(universitytf.getText());
        }
        if (!InputControl.isNull(establishmenttf.getText())) {
            criterianEntity.setInstitue(establishmenttf.getText());
        }
        if (!(categorytf.getSelectionModel().isEmpty())) {
            criterianEntity.setCategory(categorytf.getSelectionModel().getSelectedItem());
        }
        if (null != creationdatetf.getValue()) {
            criterianEntity.setCreationDate(InputControl.asDate(creationdatetf.getValue()));
        }
        if (!InputControl.isNull(phonenumbertf.getText())) {
            if (!InputControl.isGSM(phonenumbertf.getText())) {
                InputControl.genAlert("206", "ERROR", phonenumbertf.getText(), "", "").show();
            } else {
                criterianEntity.setPhoneNumber(Long.parseLong(phonenumbertf.getText()));
            }
        }
        if (!InputControl.isNull(emailtf.getText())) {
            criterianEntity.setEmail(emailtf.getText());
        }

        try {
            for (Club c : cs.SearchClubByCriterian(criterianEntity, status, numMin, numMax)) {
                clubList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddClubFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idClubC.setCellValueFactory(new PropertyValueFactory<Club, Long>("idClub"));
        CreationUserId.setCellValueFactory(new PropertyValueFactory<Club, Long>("creationUser"));
        nameC.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
        universityC.setCellValueFactory(new PropertyValueFactory<Club, String>("university"));
        institueC.setCellValueFactory(new PropertyValueFactory<Club, String>("institue"));
        categoryC.setCellValueFactory(new PropertyValueFactory<Club, String>("category"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Club, String>("description"));
        creationDateC.setCellValueFactory(new PropertyValueFactory<Club, Date>("creationDate"));
        creationUserC.setCellValueFactory(new PropertyValueFactory<Club, String>("CreationUserName"));
        selectingdch.setCellValueFactory(new PropertyValueFactory<Club, Boolean>("check"));

        activClubs.setItems(clubList);
        resetFiltersFields();

    }

    @FXML
    void resetFilterePane(ActionEvent event) {
        resetFiltersFields();
    }

    void resetFiltersFields() {

        nametf.setText("");
        universitytf.setText("");
        establishmenttf.setText("");
        categorytf.setValue("");
        creationdatetf.setValue(null);
        phonenumbertf.setText("");
        phonenumbertf.setText("");
        phonenumbertf.setText("");
        emailtf.setText("");
        minmumber.setText("");
        maxmumber1.setText("");

    }

    @FXML
    void updateList(ActionEvent event) throws IOException {

        if (checkEmptyList(1, "Update")== 1) {


        AnchorPane pane = FXMLLoader.load(getClass().getResource("UpdateClubFXML.fxml"));
        pane.setUserData(getSelectedlist().get(0));
        GridRootPane.getChildren().set(7, pane);
        }
    }

    @FXML
    void rejectList(ActionEvent event) {

        if (checkEmptyList(0, "Reject")== 1) {

        for (Club item : getSelectedlist()) {
            try {
                cs.deleteClub(item.getIdClub());
            } catch (SQLException ex) {
                InputControl.genAlert("301", "ERROR", "Club", item.getName(), "").show();
            }
        }
        }
    }

    @FXML
    void archiveList(ActionEvent event) {

        if (checkEmptyList(0, "Archive") == 1) {

            for (Club item : getSelectedlist()) {
                try {
                    cs.deleteClub(item.getIdClub());
                } catch (SQLException ex) {
                    InputControl.genAlert("302", "ERROR", "Club", item.getName(), "").show();
                }
            }
        }
    }

    @FXML
    void activeList(ActionEvent event) {

        if (checkEmptyList(0, "Activate") == 1) {
            for (Club item : getSelectedlist()) {
                try {
                    cs.updateClubStatus(item.getIdClub(), "EXPL");
                } catch (SQLException ex) {
                    InputControl.genAlert("303", "ERROR", "Club", item.getName(), "").show();
                }
            }
        }
    }

    @FXML
    void acceptList(ActionEvent event) {

        if (checkEmptyList(0, "Accept") == 1) {
            for (Club item : getSelectedlist()) {
                try {
                    cs.updateClubStatus(item.getIdClub(), "EXPL");
                    if (cats.ReadCategoryByReference(null, item.getCategory()).getStatus().equals("ETUD")) {
                        cats.validateCategory(cats.ReadCategoryByReference(null, item.getCategory()).getCategoryId());
                    }

                } catch (SQLException ex) {
                    InputControl.genAlert("304", "ERROR", "Club", item.getName(), "").show();
                }
            }
        }
    }

    int checkEmptyList(int maximum, String action) {
        if (getSelectedlist().size() == 0) {
            InputControl.genAlert("105", "ERROR", action, "", "").show();
            return 0;
        } else {
            if (maximum != 0) {
                if (maximum == 1 && getSelectedlist().size() > 1) {
                    InputControl.genAlert("106", "ERROR", "one", "row", action).show();
                    return 0;
                } else if (maximum > 1 && getSelectedlist().size() > maximum) {
                    InputControl.genAlert("106", "ERROR", Integer.toString(maximum), "rows", action).show();
                    return 0;

                }
            }

        }
        return 1;

    }

    private ObservableList<Club> getSelectedlist() {
        ObservableList<Club> selectedList = FXCollections.observableArrayList();

        for (Club selClub : clubList) {
            if (selClub.getCheck().isSelected()) {
                selectedList.add(selClub);
            }
        }
        return selectedList;
    }

    @FXML
    void uploadClubData(Event event) {
        uploadClubData();
    }

    void uploadClubData() {
        if (activeClub.isSelected()) {
            status = "EXPL";
            /*  AcceptAction.setDisable(true);
              RejectAction.setVisible(false);
            updateaction.setVisible(true);
            ArchiveAction.setVisible(true);
            Activateaction.setVisible(false);*/
        } else if (requeststab.isSelected()) {
            status = "ETUD";
            /*  AcceptAction.setVisible(false);
             RejectAction.setVisible(true);
            updateaction.setVisible(true);
            ArchiveAction.setVisible(false);
            Activateaction.setVisible(false);*/

        } else if (archive.isSelected()) {
            status = "ETUD";
            /*     AcceptAction.setVisible(true);
            RejectAction.setVisible(true);
            updateaction.setVisible(true);
            ArchiveAction.setVisible(false);
            Activateaction.setVisible(true);*/
        }
        clubList.clear();
        try {
            for (Club c : cs.ReadListClub(status)) {
                clubList.add(c);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AddClubFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idClubC.setCellValueFactory(new PropertyValueFactory<Club, Long>("idClub"));
        CreationUserId.setCellValueFactory(new PropertyValueFactory<Club, Long>("creationUser"));
        nameC.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
        universityC.setCellValueFactory(new PropertyValueFactory<Club, String>("university"));
        institueC.setCellValueFactory(new PropertyValueFactory<Club, String>("institue"));
        categoryC.setCellValueFactory(new PropertyValueFactory<Club, String>("category"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Club, String>("description"));
        creationDateC.setCellValueFactory(new PropertyValueFactory<Club, Date>("creationDate"));
        creationUserC.setCellValueFactory(new PropertyValueFactory<Club, String>("CreationUserName"));
        selectingdch.setCellValueFactory(new PropertyValueFactory<Club, Boolean>("check"));

        activClubs.setItems(clubList);
    }

    @FXML
    void searchClubByKey(MouseEvent event) {

        /*
        if (exitsearchfiltre.isPressed())
        {
            exitsearchfiltre.setVisible(false);
            SearchClubKey.setVisible(true);
            searchtf.setText(null) ;
        }
        else if (SearchClubKey.isPressed())
        {
            System.out.println("SearchClubKey.isPressed()");
            exitsearchfiltre.setVisible(true);
            SearchClubKey.setVisible(false);
        }*/
        clubList.clear();

        try {
            for (Club c : cs.SearchClubByKey(searchtf.getText(), status)) {
                clubList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddClubFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idClubC.setCellValueFactory(new PropertyValueFactory<Club, Long>("idClub"));
        CreationUserId.setCellValueFactory(new PropertyValueFactory<Club, Long>("creationUser"));
        nameC.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
        universityC.setCellValueFactory(new PropertyValueFactory<Club, String>("university"));
        institueC.setCellValueFactory(new PropertyValueFactory<Club, String>("institue"));
        categoryC.setCellValueFactory(new PropertyValueFactory<Club, String>("category"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Club, String>("description"));
        creationDateC.setCellValueFactory(new PropertyValueFactory<Club, Date>("creationDate"));
        creationUserC.setCellValueFactory(new PropertyValueFactory<Club, String>("CreationUserName"));
        selectingdch.setCellValueFactory(new PropertyValueFactory<Club, Boolean>("check"));

        activClubs.setItems(clubList);
    }

}
