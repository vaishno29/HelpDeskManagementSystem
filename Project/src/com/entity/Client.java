package com.entity;

public class Client {
    private int cid;
    private String cname;
    private String phone;
    private String email;
    private String address;
    private int totalNumberComplaints;

    // Constructor
    public Client() {}

    public Client(int cid, String cname, String phone, String email, String address, int totalNumberComplaints) {
        this.cid = cid;
        this.cname = cname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalNumberComplaints = totalNumberComplaints;
    }

    // Getters and Setters
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalNumberComplaints() {
        return totalNumberComplaints;
    }

    public void setTotalNumberComplaints(int totalNumberComplaints) {
        this.totalNumberComplaints = totalNumberComplaints;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", totalNumberComplaints=" + totalNumberComplaints +
                '}';
    }
}
