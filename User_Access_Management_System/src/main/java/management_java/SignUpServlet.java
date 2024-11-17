package management_java;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetching data from the signup form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role"); // Assume 'role' is passed from the form or defaults to 'Employee'

        // Default role if not provided
        if (role == null || role.isEmpty()) {
            role = "Employee"; // Default role is Employee
        }

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementdb", "root", "root");

            // SQL Insert Query for user registration
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, role) VALUES (?, ?, ?)");

            // Setting values into the prepared statement
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            // Execute the update
            ps.executeUpdate();

            // Close the connection
            ps.close();
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect to login page after successful signup
        resp.sendRedirect("login.jsp");
    }
}
