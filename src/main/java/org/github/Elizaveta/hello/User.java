package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

public class User extends HttpServlet {
    PersonDAO personDAO;
    PhotoDAO photoDAO;
    private String filePath;

    public User() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        req.setAttribute("user", personDAO.getUser((Integer)httpSession.getAttribute(Authorization.ID)));
        req.setAttribute("image", photoDAO.getAvatar((Integer)httpSession.getAttribute("ID")));
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        String imagename = "image" + UUID.randomUUID().toString();
        Files.copy(fileContent, new File(filePath + imagename).toPath());
        HttpSession httpSession = req.getSession();
        photoDAO.setAvatar((Integer)httpSession.getAttribute(Authorization.ID), imagename);
        req.setAttribute("image", photoDAO.getAvatar((Integer) httpSession.getAttribute("ID")));
        req.getRequestDispatcher("user.jsp").forward(req, resp);

        fileContent.close();
    }

    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
    }
}
