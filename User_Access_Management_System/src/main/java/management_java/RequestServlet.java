package management_java;





import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve user input
        String softwareName = req.getParameter("softwareName");
        String accessType = req.getParameter("accessType");
        String reason = req.getParameter("reason");

        // Get the current user's username from session
        HttpSession session = req.getSession(false); // false to prevent creating a new session
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");

        try {
            // Register JDBC driver and establish a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementdb", "root", "root");

            // Fetch user ID from users table using username
            PreparedStatement fetchUserId = con.prepareStatement("SELECT id FROM users WHERE username = ?");
            fetchUserId.setString(1, username);
            ResultSet rs = fetchUserId.executeQuery();

            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt("id");
            } else {
                throw new SQLException("User ID not found for the username: " + username);
            }

            // Fetch software ID from the software table
            PreparedStatement fetchSoftwareId = con.prepareStatement("SELECT id FROM software WHERE name = ?");
            fetchSoftwareId.setString(1, softwareName);
            rs = fetchSoftwareId.executeQuery();

            int softwareId = -1;
            if (rs.next()) {
                softwareId = rs.getInt("id");
            } else {
                throw new SQLException("Software ID not found for the software: " + softwareName);
            }

            // Insert the access request into the requests table
            PreparedStatement insertRequest = con.prepareStatement(
                    "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)");
            insertRequest.setInt(1, userId);
            insertRequest.setInt(2, softwareId);
            insertRequest.setString(3, accessType);
            insertRequest.setString(4, reason);
            insertRequest.setString(5, "Pending"); // Default status

            int rowsAffected = insertRequest.executeUpdate();

            // Provide feedback to the user
            if (rowsAffected > 0) {
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.print("<p>Request submitted successfully!</p>");
                RequestDispatcher rd = req.getRequestDispatcher("requestAccess.jsp");
                rd.include(req, resp);
            } else {
                throw new SQLException("Failed to submit the request.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.print("<p>Error processing the request: " + e.getMessage() + "</p>");
            RequestDispatcher rd = req.getRequestDispatcher("requestAccess.jsp");
            rd.include(req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
