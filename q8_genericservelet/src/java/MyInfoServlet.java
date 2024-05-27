import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MyInfoServlet", urlPatterns = { "/myinfo" })
public class MyInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String enrollmentNumber = "04116404523";
        String name = "Yashwant Singh";
        System.out.println("Setting attributes - Enrollment Number: " + enrollmentNumber + ", Name: " + name);
        request.setAttribute("enrollmentNumber", enrollmentNumber);
        request.setAttribute("name", name);
        request.getRequestDispatcher("/myinfo.jsp").forward(request, response);
    }

}
