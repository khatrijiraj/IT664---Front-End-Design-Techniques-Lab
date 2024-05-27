
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FetchDataServlet")
public class FetchDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "admin")) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT EmployeeID, EmployeeName, DOB, Address, Department, DOJ, Position FROM employees");

                out.println("<html><body>");
                out.println("<h2>Employee Records</h2>");
                out.println("<table border='1'><tr><th>Employee ID</th><th>Name</th><th>DOB</th><th>Address</th><th>Department</th><th>DOJ</th><th>Position</th></tr>");

                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("EmployeeID") + "</td><td>" + rs.getString("EmployeeName") + "</td><td>" + rs.getString("DOB") + "</td><td>" + rs.getString("Address") + "</td><td>" + rs.getString("Department") + "</td><td>" + rs.getString("DOJ") + "</td><td>" + rs.getString("Position") + "</td></tr>");
                }

                out.println("</table>");
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
