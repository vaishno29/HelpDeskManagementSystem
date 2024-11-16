package com.dao;

import java.util.List;
import com.entity.Admin;

public interface AdminDAO {
    void addAdmin(Admin admin);
    void updateAdmin(Admin admin);
    void deleteAdmin(int adminId);
    Admin getAdminById(int adminId);
    List<Admin> getAllAdmins();
}


