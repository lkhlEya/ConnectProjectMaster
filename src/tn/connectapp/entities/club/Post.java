package tn.connectapp.entities.club;

import java.sql.Date;

public class Post {

    private Long postId;
    private String postName;
    private Long creationUser;
    private Date creationDate;
    private String status;
    private String description;
    private String creationUserName;

    public Post() {
    }

    public Post(Long postId, String postName, Long creationUser, Date creationDate, String status, String description, String creationUserName) {
        this.postId = postId;
        this.postName = postName;
        this.creationUser = creationUser;
        this.creationDate = creationDate;
        this.status = status;
        this.description = description;
        this.creationUserName = creationUserName;

    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationUserName() {
        return creationUserName;
    }

    public void setCreationUserName(String creationUserName) {
        this.creationUserName = creationUserName;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", postName=" + postName + ", creationUser=" + creationUser + ", creationDate=" + creationDate + ", status=" + status + ", description=" + description + ", creationUserName=" + creationUserName + '}';
    }

}
