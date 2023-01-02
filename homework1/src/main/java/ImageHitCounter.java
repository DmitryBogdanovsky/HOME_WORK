import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


// ==========================  TASK 12  ==============================================

import static java.awt.Font.BOLD;

public class ImageHitCounter extends HttpServlet {

    String fileName = "\\hitcounter.txt";
    long hitCounter = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        readFile();
        updateHitCounterFile();
        HttpSession usersession = request.getSession();
        usersession.setAttribute("HITCOUNTER", hitCounter);
        response.setContentType("image/jpeg");
        BufferedImage image = new BufferedImage(500, 200, BufferedImage.TYPE_INT_BGR );
        Graphics2D g = image.createGraphics();
        g.setFont(new Font("Arial", BOLD, 25));
        g.setColor(Color.YELLOW);
        g.drawString("Request Number:  " + hitCounter, 100, 100);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
        out.println("<html><head><title>IMAGE</title><head>");
        out.println("<body>");
        out.println("<h1>image</h1>");
        out.println("</body></html>");

    }

    private void updateHitCounterFile() throws IOException {

        /**
         * Here I am increasing counter each time this HitCounterServlet is called.
         * I am updating hitcounter.txt file which store total number of visitors on website.
         * Now I want total number of visitor on per day basis.
         */
        hitCounter++;

        // read and update into file
        //   File file = new File( path + fileName);

        File file = new File(fileName);

        // if file doesn't exists, then create it
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
            //System.out.println("HIT Counter : " + hitCounter);
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