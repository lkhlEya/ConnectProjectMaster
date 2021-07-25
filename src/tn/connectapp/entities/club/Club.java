package tn.connectapp.entities.club;

import java.sql.Date;
import javafx.scene.control.CheckBox;

public class Club {

    private Long idClub;
    private String name;
    private String university;
    private String institue;
    private String status;
    private String description;
    private String category;
    private Date creationDate;
    private String logo;
    private Long phoneNumber;
    private String email;
    private Long creationUser;
    private Date addDate;
    private CheckBox check;
    private String CreationUserName;

    public Club(Long idClub, String name, String university, String institue, String status, String description, String category, Date creationDate, String logo, Long phoneNumber, String email, Long creationUser, Date addDate, String CreationUserName) {
        this.idClub = idClub;
        this.name = name;
        this.university = university;
        this.institue = institue;
        this.status = status;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.logo = logo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creationUser = creationUser;
        this.addDate = addDate;
        check = new CheckBox();
        this.CreationUserName = CreationUserName;
    }

    public Club(Long idClub, String name, String university, String institue, String status, String description,
            String category, Date creationDate, String logo, Long phoneNumber, String email, Long creationUser, Date addDate) {

        this.setIdClub(idClub);
        this.institue = institue;
        this.name = name;
        this.status = status;
        this.university = university;
        this.description = description;
        this.category = category;
        this.creationDate = creationDate;
        this.logo = logo;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.creationUser = creationUser;
        this.addDate = addDate;
        check = new CheckBox();

    }

    public Club() {
        check = new CheckBox();

    }

    public void setCheck(Boolean x) {
        check.setSelected(x);

    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCreationUserName(String CreationUserName) {
        this.CreationUserName = CreationUserName;
    }

    public String getCreationUserName() {
        return CreationUserName;
    }

    public void setCreationUser(Long creationUser) {
        this.creationUser = creationUser;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Long getCreationUser() {
        return creationUser;
    }

    public Date getAddDate() {
        return addDate;
    }

    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstitue() {
        return institue;
    }

    public void setInstitue(String institue) {
        this.institue = institue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Club{" + "idClub=" + idClub + ", name=" + name + ", university=" + university + ", institue=" + institue + ", status=" + status + ", description=" + description + ", category=" + category + ", creationDate=" + creationDate + ", logo=" + logo + ", phoneNumber=" + phoneNumber + ", email=" + email + ", creationUser=" + creationUser + ", addDate=" + addDate + ", CreationUserName=" + CreationUserName + '}';
    }

}
