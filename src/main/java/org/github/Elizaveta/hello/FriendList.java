package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.FriendshipDAO;
import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FriendList extends HttpServlet {
    PersonDAO personDAO = null;
    PhotoDAO photoDAO = null;

    public FriendList() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        req.setAttribute("friends", personDAO.getFriends(Integer.parseInt((String) httpSession.getAttribute("ID"))));
        req.setAttribute("photos", photoDAO.getAllAvatar());
        req.getRequestDispatcher("friendlist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
