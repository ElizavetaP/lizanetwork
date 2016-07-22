package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class OtherUser extends HttpServlet {
    PersonDAO personDAO = null;
    PhotoDAO photoDAO = null;
    private String filePath;

    public OtherUser() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id == null){
            resp.sendRedirect("/");
        }else {
            System.out.println("id=" + id );
            req.setAttribute("user", personDAO.getUser(id));
            req.setAttribute("image", photoDAO.getAvatar(id));
            req.getRequestDispatcher("otheruser.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
    }
}
