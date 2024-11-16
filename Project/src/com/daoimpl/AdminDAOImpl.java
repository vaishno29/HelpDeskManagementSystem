package com.daoimpl;

import java.sql.*;
import java.util.List;
import com.dao.AdminDAO;
import com.dbconnection.DBConnection;
import com.entity.Admin;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public void addAdmin(Admin admin) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO Admin(name, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE Admin SET name = ?, email = ?, password = ? WHERE adminid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.setInt(4, admin.getAdminId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int adminId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM Admin WHERE adminid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, adminId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminById(int adminId) {
        Admin admin = null;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Admin WHERE adminid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("adminid"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM Admin";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("adminid"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
}
