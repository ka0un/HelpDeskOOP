package project.features.tickets.model.dao;

import java.sql.Timestamp;

public class Ticket {
    private int id;
    private int userId;
    private String title;
    private String description;
    private boolean isClosed;
    private boolean isDeleted;
    private Timestamp createdDate;
    private Timestamp closedDate;
    private Timestamp updatedDate;
    private boolean isNew;

    //constructor getter setter
    public Ticket(int id, int userId, String title, String description, boolean isClosed, boolean isDeleted, Timestamp createdDate, Timestamp closedDate, Timestamp updatedDate, boolean isNew) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.isClosed = isClosed;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.closedDate = closedDate;
        this.updatedDate = updatedDate;
        this.isNew = isNew;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public java.sql.Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public java.sql.Timestamp getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Timestamp closedDate) {
        this.closedDate = closedDate;
    }

    public java.sql.Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}