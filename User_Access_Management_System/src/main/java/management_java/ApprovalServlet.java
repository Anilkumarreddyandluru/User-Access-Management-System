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

@WebServlet("/approvalServlet")
public class ApprovalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestId = req.getParameter("requestId");
        String action = req.getParameter("action"); // "approve" or "reject"

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementdb", "root", "root");

            // Determine the status based on action
            String status = action.equals("approve") ? "Approved" : "Rejected";

            // Update the request status
            String sql = "UPDATE requests SET status = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, requestId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                resp.sendRedirect("pendingRequests.jsp?message=Request+" + status.toLowerCase() + "+successfully");
            } else {
                resp.sendRedirect("pendingRequests.jsp?error=Failed+to+process+request");
            }

            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("pendingRequests.jsp?error=An+error+occurred");
        }
    }
}

