

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//====================  Task 10 ++++++++++++++++++++++++++++++++++++++++++++++++
public class FormServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String name = request.getParameter("username");
        String phone = request.getParameter("userphone");
        String email = request.getParameter("useremail");

        if ((name.equals("") | ((phone.equals("")) | (email.equals("")))) ){
            writer.println("<p>" + " ERROR!!!!!   String can not be empty! " + "</p>");
        }
        else {
            writer.println("<p>Name: " + name + "</p>");
            writer.println("<p>Phone: " + phone + "</p>");
            writer.println("<p>E-mail: " + email + "</p>");
        }

    }

}
