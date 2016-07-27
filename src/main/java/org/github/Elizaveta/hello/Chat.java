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
        String otherUserID = req.getParameter("id");
        if(otherUserID==null){
            resp.sendRedirect("/user");
        }else {
            int ID = Integer.parseInt(otherUserID);
            req.setAttribute("id",ID);
            req.setAttribute("photos", photoDAO.getAllAvatar());
            req.setAttribute("messages", messageDAO.getMessage("chat", Integer.parseInt((String) httpSession.getAttribute("ID")),ID));
            req.getRequestDispatcher("chat.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        int otherUserID = Integer.parseInt(req.getParameter("id"));
        String text= req.getParameter("text");
        messageDAO.sendMessage(text,Integer.parseInt((String)httpSession.getAttribute("ID")),otherUserID,"chat");
        resp.sendRedirect("/user/chat?id="+otherUserID);
    }
}
