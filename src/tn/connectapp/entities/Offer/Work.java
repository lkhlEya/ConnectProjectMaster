package tn.connectapp.entities.Offer;

import java.sql.Date;

public class Work extends Offer {

    private String agreementType;
    private String position;
    private Date startDate;

    public Work(int id, String company, String field, String title, String description, Date startDate, String agreementType, String position, String status) {
        super(id, company, field, description, title, status);
        this.agreementType = agreementType;
        this.position = position;
        this.startDate = startDate;

    }

    public String getAgreementType() {
        return agreementType;
    }

    public String getPosition() {
        return position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
