package tn.connectapp.entities.Offer;

import java.sql.Date;

/**
 *
 * @author Thinkpad
 */
public class Internships extends Offer {

    private Date startDate;
    private Date endDate;
    private String supervisor;
    private String type;
    private String REF;

    public Internships(String ref,
            String company,
            String field,
            String title,
            String description,
            Date startDate,
            Date endDate,
            String type,
            String supervisor,
            String status) {
        super(company, field, description, title, status);
        this.supervisor = supervisor;
        this.endDate = endDate;
        this.startDate = startDate;
        this.type = type;

    }

    public Internships(String company,
            String field,
            String title,
            String description,
            Date startDate,
            Date endDate,
            String supervisor,
            String status) {
        super(company, field, description, title, status);
        this.supervisor = supervisor;
        this.endDate = endDate;
        this.startDate = startDate;

    }

    public Internships(String title ) {
        super(title);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }

    public String getType() {
        return type;
    }

    public String getREF() {
        return REF;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getSupervisor() {
        return supervisor;
    }

}
