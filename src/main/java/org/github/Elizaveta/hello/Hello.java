package org.github.Elizaveta.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.UUID;

public class Hello extends HttpServlet {

    private PersonDAO personDAO;
    private PhotoDAO photoDAO;

    public Hello() {
        super();

        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchname = req.getParameter("searchname");

        req.setAttribute("photos", photoDAO.getAllAvatar());
        if (searchname == null || searchname == "") {
            req.setAttribute("persons", personDAO.getPersons());
            System.out.println("null");
        } else {
            System.out.println(searchname);
            try {
                req.setAttribute("persons", personDAO.searchByName(searchname));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        Files.copy(fileContent, new File("image" + UUID.randomUUID().toString()).toPath());

        /*FileOutputStream os = new FileOutputStream("qwerty.txt");
        byte[] b = new byte[(int)filePart.getSize()];
        fileContent.read(b);
        os.write(b);
        os.close();*/

        fileContent.close();
        resp.sendRedirect("/");

    }


}