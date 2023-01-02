import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

// ==========================  TASK 9  ==============================================

public class HitCounterServlet extends HttpServlet {
    //  String path = new File("").getAbsolutePath();
    String fileName = "\\hitcounter.txt";
    long hitCounter = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        readFile();
        updateHitCounterFile();
        HttpSession usersession = request.getSession();
        usersession.setAttribute("HITCOUNTER", hitCounter);
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>HitCountert</title></head>");
        out.println("<body><h1>This is my First Servlet</h1>");
        out.println("<h2>Request Number: " + hitCounter + "</h2>");
        out.println("</body></html>");
    }

    private void updateHitCounterFile() throws IOException {

        hitCounter++;

        File file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(Long.toString(hitCounter));
        bw.close();
    }

    public void readFile() {
        BufferedReader br = null;
        String temp = "";
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((temp = br.readLine()) != null) {
                hitCounter = Long.parseLong(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
