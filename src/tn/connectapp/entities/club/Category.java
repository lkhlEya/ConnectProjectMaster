package tn.connectapp.entities.club;

import java.sql.Date;

public class Category {

	private Long categoryId;
	private Long creationUser;
	private Date creationDate;
	private String status;
	private String categoryName;
	private String description;
        private String creationUserName;

    public void setCreationUserName(String creationUserName) {
        this.creationUserName = creationUserName;
    }

    public String getCreationUserName() {
        return creationUserName;
    }

	public Category() {}
	
	public Category(Long categoryId, String categoryName, Long creationUser, Date creationDate, String status,String description,String creationUserName)
	{
		this.categoryId   = categoryId;		
		this.categoryName = categoryName;
		this.creationUser = creationUser;
		this.creationDate = creationDate;
		this.status = status;
		this.description = description;
                this.creationUserName = creationUserName;
	}

	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getCreationUser() {
		return creationUser;
	}
	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", creationUser=" + creationUser + ", creationDate=" + creationDate + ", status=" + status + ", categoryName=" + categoryName + ", description=" + description + ", creationUserName=" + creationUserName + '}';
    }


}
