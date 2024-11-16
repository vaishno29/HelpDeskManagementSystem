package com.helpdeskmanagementsystem;

import java.util.Scanner;
import java.util.List;
import com.daoimpl.ComplaintDAOImpl;
import com.entity.Complaint;
import com.daoimpl.AdminDAOImpl;
import com.daoimpl.ClientDAOImpl;
import com.dao.ClientDAO;
import com.dao.AdminDAO;
import com.dao.ComplaintDAO;
import com.entity.Admin;
import com.entity.Client;

public class HelpDeskApp {
    private static ClientDAO clientDAO = new ClientDAOImpl();
    private static AdminDAO adminDAO = new AdminDAOImpl();
    private static ComplaintDAO complaintDAO = new ComplaintDAOImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Admin\n2. Client\n3. Exit\nChoose your role:");
            int role = sc.nextInt();
            switch (role) {
                case 1: // Admin operations
                    adminOperations(sc);
                    break;
                case 2: // Client operations
                    clientOperations(sc);
                    break;
                case 3: // Exit
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private static void adminOperations(Scanner sc) {
        while (true) {
            System.out.println("Admin Operations: ");
            System.out.println("1. Add Admin");
            System.out.println("2. Update Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. Assign Ticket");
            System.out.println("5. Change Complaint Status");
            System.out.println("6. View All Complaints");
            System.out.println("7. View All Tickets"); // Added this line
            System.out.println("8. Exit");
            System.out.print("Choose an operation: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Admin
                    System.out.println("Enter Admin name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Admin email:");
                    String email = sc.nextLine();
                    System.out.println("Enter Admin password:");
                    String password = sc.nextLine();

                    Admin admin = new Admin();
                    admin.setName(name);
                    admin.setEmail(email);
                    admin.setPassword(password);
                    adminDAO.addAdmin(admin);
                    System.out.println("Admin added successfully.");
                    break;

                case 2: // Update Admin
                    System.out.println("Enter Admin ID:");
                    int adminId = sc.nextInt();
                    System.out.println("Enter updated name:");
                    name = sc.next();
                    System.out.println("Enter updated email:");
                    email = sc.next();
                    System.out.println("Enter updated password:");
                    password = sc.next();

                    Admin updateAdmin = new Admin();
                    updateAdmin.setAdminId(adminId);
                    updateAdmin.setName(name);
                    updateAdmin.setEmail(email);
                    updateAdmin.setPassword(password);
                    adminDAO.updateAdmin(updateAdmin);
                    System.out.println("Admin updated successfully.");
                    break;

                case 3: // Delete Admin
                    System.out.println("Enter Admin ID to delete:");
                    adminId = sc.nextInt();
                    adminDAO.deleteAdmin(adminId);
                    System.out.println("Admin deleted successfully.");
                    break;

                case 4: // Assign Ticket
                    System.out.println("Enter Complaint Ticket ID to assign:");
                    int ticketId = sc.nextInt();
                    System.out.println("Enter Admin ID to assign:");
                    adminId = sc.nextInt();
                    complaintDAO.updateComplaintStatus(ticketId, 2, adminId); // 2 means 'in progress'
                    System.out.println("Ticket assigned to Admin.");
                    break;

                case 5: // Change Complaint Status
                    System.out.println("Enter Complaint Ticket ID:");
                    ticketId = sc.nextInt();
                    System.out.println("Enter new Status (1-Open, 2-InProgress, 3-WaitingForVerification, 4-Verified, 5-Reopen, 6-Close):");
                    int status = sc.nextInt();
                    System.out.println("Enter Admin ID:");
                    adminId = sc.nextInt();
                    complaintDAO.updateComplaintStatus(ticketId, status, adminId);
                    System.out.println("Complaint status updated.");
                    break;

                case 6: // View All Complaints
                    List<Complaint> complaints = complaintDAO.getAllComplaints();
                    if (complaints.isEmpty()) {
                        System.out.println("No complaints found.");
                    } else {
                        System.out.println("Viewing all complaints:");
                        for (Complaint comp : complaints) {
                            System.out.println("Ticket ID: " + comp.getTicketId() + ", Client ID: " + comp.getCid() +
                                    ", Description: " + comp.getDescription() + ", Status: " + comp.getStatus() +
                                    ", Raised Date: " + comp.getRaiseDate() + ", Assigned Admin ID: " + comp.getAdminId());
                        }
                    }
                    break;

                case 7: // View All Tickets
                    viewAllTickets(); // Call the new method
                    break;

                case 8: // Exit
                    System.out.println("Exiting Admin operations...");
                    return;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private static void viewAllTickets() {
        List<Complaint> complaints = complaintDAO.getAllComplaints();
        if (complaints.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            System.out.println("View All Tickets:");
            for (Complaint complaint : complaints) {
                System.out.println("Ticket ID: " + complaint.getTicketId() +
                                   ", Client ID: " + complaint.getCid() +
                                   ", Description: " + complaint.getDescription() +
                                   ", Status: " + complaint.getStatus() +
                                   ", Raised Date: " + complaint.getRaiseDate());
            }
        }
    }

    private static void clientOperations(Scanner sc) {
        while (true) {
            System.out.println("1. Add Client\n2. Update Client\n3. Delete Client\n4. Raise Complaint\n5. Check Complaint Status\n6. Exit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Add Client
                    System.out.println("Enter Client name:");
                    String cname = sc.next();
                    System.out.println("Enter Client phone:");
                    String phone = sc.next();
                    System.out.println("Enter Client email:");
                    String email = sc.next();
                    System.out.println("Enter Client address:");
                    String address = sc.next();
                    System.out.println("Enter total number of complaints:");
                    int totalComplaints = sc.nextInt();

                    Client client = new Client();
                    client.setCname(cname);
                    client.setPhone(phone);
                    client.setEmail(email);
                    client.setAddress(address);
                    client.setTotalNumberComplaints(totalComplaints);
                    clientDAO.addClient(client);
                    System.out.println("Client added successfully.");
                    break;

                case 2: // Update Client
                    System.out.println("Enter Client ID:");
                    int clientId = sc.nextInt();
                    System.out.println("Enter updated name:");
                    cname = sc.next();
                    System.out.println("Enter updated phone:");
                    phone = sc.next();
                    System.out.println("Enter updated email:");
                    email = sc.next();
                    System.out.println("Enter updated address:");
                    address = sc.next();
                    System.out.println("Enter updated total number of complaints:");
                    totalComplaints = sc.nextInt();

                    Client updateClient = new Client();
                    updateClient.setCid(clientId);
                    updateClient.setCname(cname);
                    updateClient.setPhone(phone);
                    updateClient.setEmail(email);
                    updateClient.setAddress(address);
                    updateClient.setTotalNumberComplaints(totalComplaints);
                    clientDAO.updateClient(updateClient);
                    System.out.println("Client updated successfully.");
                    break;

                case 3: // Delete Client
                    System.out.println("Enter Client ID to delete:");
                    clientId = sc.nextInt();
                    clientDAO.deleteClient(clientId);
                    System.out.println("Client deleted successfully.");
                    break;

                case 4: // Raise Complaint
                    System.out.println("Enter Client ID:");
                    clientId = sc.nextInt();
                    System.out.println("Enter complaint description:");
                    String description = sc.next();
                    System.out.println("Enter Admin ID:");
                    int adminId = sc.nextInt();

                    Complaint complaint = new Complaint();
                    complaint.setCid(clientId);
                    complaint.setDescription(description);
                    complaint.setStatus(1); // 1 means 'open'
                    complaint.setRaiseDate(new java.util.Date());
                    complaint.setAdminId(adminId);
                    complaintDAO.raiseComplaint(complaint);
                    System.out.println("Complaint raised successfully.");
                    break;

                case 5: // Check Complaint Status
                    System.out.println("Enter Client ID:");
                    clientId = sc.nextInt();
                    List<Complaint> clientComplaints = complaintDAO.getAllComplaintsByClientId(clientId);
                    for (Complaint comp : clientComplaints) {
                        System.out.println("Ticket ID: " + comp.getTicketId() + ", Status: " + comp.getStatus());
                    }
                    break;

                case 6: // Exit
                    return;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
