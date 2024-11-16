package com.entity;

import java.util.Date;

public class Complaint {
    private int ticketId;
    private int cid;  // Client ID
    private String description;
    private int status; // 1-open, 2-inprogress, etc.
    private Date raiseDate;
    private int adminId;  // Admin ID assigned to the complaint

    // Constructors
    public Complaint() {}

    public Complaint(int ticketId, int cid, String description, int status, Date raiseDate, int adminId) {
        this.ticketId = ticketId;
        this.cid = cid;
        this.description = description;
        this.status = status;
        this.raiseDate = raiseDate;
        this.adminId = adminId;
    }

    // Getters and Setters
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getRaiseDate() {
        return raiseDate;
    }

    public void setRaiseDate(Date raiseDate) {
        this.raiseDate = raiseDate;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "ticketId=" + ticketId +
                ", cid=" + cid +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", raiseDate=" + raiseDate +
                ", adminId=" + adminId +
                '}';
    }
}
