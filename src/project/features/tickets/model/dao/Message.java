package project.features.tickets.model.dao;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int ticketId;
    private int senderId;
    private String message;
    private Timestamp sentTime;
    private boolean isSenderAdmin;

    //constructor getter setter
    public Message(int id, int ticketId, int senderId, String message, Timestamp sentTime, boolean isSenderAdmin) {
        this.id = id;
        this.ticketId = ticketId;
        this.senderId = senderId;
        this.message = message;
        this.sentTime = sentTime;
        this.isSenderAdmin = isSenderAdmin;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    public boolean isSenderAdmin() {
        return isSenderAdmin;
    }

    public void setSenderAdmin(boolean senderAdmin) {
        isSenderAdmin = senderAdmin;
    }
}
