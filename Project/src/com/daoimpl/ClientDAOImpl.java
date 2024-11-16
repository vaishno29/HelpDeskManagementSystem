package com.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dao.ClientDAO;
import com.dbconnection.DBConnection;
import com.entity.Client;

public class ClientDAOImpl implements ClientDAO {

    @Override
    public void addClient(Client client) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO Clients(cname, phone, email, address, totalNumberComplaints) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, client.getCname());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getAddress());
            ps.setInt(5, client.getTotalNumberComplaints());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client client) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE Clients SET cname = ?, phone = ?, email = ?, address = ?, totalNumberComplaints = ? WHERE cid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, client.getCname());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getAddress());
            ps.setInt(5, client.getTotalNumberComplaints());
            ps.setInt(6, client.getCid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int clientId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM Clients WHERE cid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clientId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client getClientById(int clientId) {
        Client client = null;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Clients WHERE cid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setCid(rs.getInt("cid"));
                client.setCname(rs.getString("cname"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("address"));
                client.setTotalNumberComplaints(rs.getInt("totalNumberComplaints"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Clients";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Client client = new Client();
                client.setCid(rs.getInt("cid"));
                client.setCname(rs.getString("cname"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("address"));
                client.setTotalNumberComplaints(rs.getInt("totalNumberComplaints"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
