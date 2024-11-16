package com.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dao.ComplaintDAO;
import com.dbconnection.DBConnection;
import com.entity.Complaint;

public class ComplaintDAOImpl implements ComplaintDAO {

    @Override
    public void raiseComplaint(Complaint complaint) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO Complaints(cid, description, status, raisedate, adminid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, complaint.getCid());
            ps.setString(2, complaint.getDescription());
            ps.setInt(3, complaint.getStatus());
            ps.setDate(4, new Date(complaint.getRaiseDate().getTime()));
            ps.setInt(5, complaint.getAdminId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateComplaintStatus(int ticketId, int status, int adminId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE Complaints SET status = ?, adminid = ? WHERE ticketid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, adminId);
            ps.setInt(3, ticketId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComplaint(int ticketId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM Complaints WHERE ticketid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticketId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Complaint getComplaintById(int ticketId) {
        Complaint complaint = null;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Complaints WHERE ticketid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                complaint = new Complaint();
                complaint.setTicketId(rs.getInt("ticketid"));
                complaint.setCid(rs.getInt("cid"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getInt("status"));
                complaint.setRaiseDate(rs.getDate("raisedate"));
                complaint.setAdminId(rs.getInt("adminid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaint;
    }

    @Override
    public List<Complaint> getAllComplaintsByClientId(int clientId) {
        List<Complaint> complaints = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Complaints WHERE cid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setTicketId(rs.getInt("ticketid"));
                complaint.setCid(rs.getInt("cid"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getInt("status"));
                complaint.setRaiseDate(rs.getDate("raisedate"));
                complaint.setAdminId(rs.getInt("adminid"));
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaints;
    }

    @Override
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Complaints";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setTicketId(rs.getInt("ticketid"));
                complaint.setCid(rs.getInt("cid"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getInt("status"));
                complaint.setRaiseDate(rs.getDate("raisedate"));
                complaint.setAdminId(rs.getInt("adminid"));
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaints;
    }
}
