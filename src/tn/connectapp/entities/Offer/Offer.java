package tn.connectapp.entities.Offer;

public class Offer {

    private int id;
    private String company;
    private String field;
    private String description;
    private String title;
    private String status;
    
    public Offer(String title){
        this.title = title;
    }

    public Offer(int id, String company, String field, String description, String title, String status) {
        this.id = id;
        this.company = company;
        this.field = field;
        this.description = description;
        this.title = title;
        this.status = status;
    }

    public Offer(String company, String field, String description, String title, String status) {
        this.company = company;
        this.field = field;
        this.description = description;
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getField() {
        return field;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", company=" + company + ", field="
                + field + ", description=" + description + '}';
    }

}
