package management_java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/createSoftware")
public class SoftwareServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String softwareName = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("access_levels");

        // Connect to the database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/managementdb", "root", "root")) {
            String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, softwareName);
                stmt.setString(2, description);
                stmt.setString(3, accessLevels);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.sendRedirect("createSoftware.jsp");
    }
}




