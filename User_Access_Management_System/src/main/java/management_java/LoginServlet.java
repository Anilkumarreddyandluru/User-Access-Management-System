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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Register the JDBC driver and establish the connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementdb", "root", "root");

            // Prepare SQL query to check user credentials
            PreparedStatement ps = con.prepareStatement("SELECT username, password, role FROM users WHERE username= ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            // Check if credentials are valid
            if (rs.next()) {
                String role = rs.getString("role");

                // Create a session for the user
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                // Redirect based on the role
                if ("Employee".equals(role)) {
                    resp.sendRedirect("requestAccess.jsp");  // Redirect Employee to Access Request Page
                } else if ("Manager".equals(role)) {
                    resp.sendRedirect("pendingRequests.jsp");  // Redirect Manager to Pending Requests Page
                } else if ("Admin".equals(role)) {
                    resp.sendRedirect("createSoftware.jsp");  // Redirect Admin to Software Creation Page
                }

            } else {
                // If credentials are incorrect, show an error message
                resp.setContentType("text/html");
                PrintWriter pw = resp.getWriter();
                pw.print("Incorrect email or password...");
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.include(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


        