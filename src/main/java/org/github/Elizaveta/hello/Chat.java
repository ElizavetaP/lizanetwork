package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.Message;
import org.github.Elizaveta.hello.dao.MessageDAO;
import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Chat extends HttpServlet {
    PersonDAO personDAO;
    PhotoDAO photoDAO;
    MessageDAO messageDAO;

    public Chat() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
        messageDAO = new MessageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        int otherUserID = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", otherUserID);
        req.setAttribute("photos", photoDAO.getAllAvatar());

        List<Message> allmessages = messageDAO.getMessage("chat",(Integer) httpSession.getAttribute(Authorization.ID),
                otherUserID);
        int noOfRecords = allmessages.size();
        int recordsPerPage = 5;
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        int page = noOfPages;
        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<Message> pagemassages;
        if((page-1)*recordsPerPage+recordsPerPage>noOfRecords){
            pagemassages = allmessages.subList((page-1)*recordsPerPage,noOfRecords);
        }else {
            pagemassages = allmessages.subList((page-1)*recordsPerPage,(page-1)*recordsPerPage+recordsPerPage);
        }
        JSPListBean jsp = new JSPListBean(pagemassages);
        req.setAttribute("messages", jsp);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("chat.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        int otherUserID = Integer.parseInt(req.getParameter("id"));
        String text = req.getParameter("text");
        messageDAO.sendMessage(text, (Integer) httpSession.getAttribute(Authorization.ID), otherUserID, "chat");
        resp.sendRedirect("/chat?id=" + otherUserID);
    }
}
