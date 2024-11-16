package com.dao;

import com.entity.Admin;
import com.entity.Client;
import com.entity.Complaint;
import java.util.List;

public interface ComplaintDAO {

    void raiseComplaint(Complaint complaint);

    void updateComplaintStatus(int ticketId, int status, int adminId);

    void deleteComplaint(int ticketId);

    Complaint getComplaintById(int ticketId);  // Add this method

    List<Complaint> getAllComplaintsByClientId(int clientId);  // Add this method

    List<Complaint> getAllComplaints();  // Optionally, if not already present
}



