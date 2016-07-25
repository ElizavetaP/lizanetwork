package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.MessageDAO;
import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Chat extends HttpServlet{
    PersonDAO personDAO = null;
    PhotoDAO photoDAO = null;
    MessageDAO messageDAO = null;

    public Chat() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
        messageDAO = new MessageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String ID_otheruser = req.getParameter("id");
        if(ID_otheruser==null){
            resp.sendRedirect("/");
        }else {
            Long ID = Long.parseLong(ID_otheruser);
            req.setAttribute("photos", photoDAO.getAllAvatar());
            req.setAttribute("messages", messageDAO.getMessage("chat", Long.parseLong((String) httpSession.getAttribute("ID")),ID));
            req.getRequestDispatcher("chat.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
