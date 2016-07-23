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
    FriendshipDAO friendshipDAO=null;

    public FriendList() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
        friendshipDAO = new FriendshipDAO();
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
        HttpSession httpSession = req.getSession();
        String ID_otheruser = req.getParameter("id");
        if(ID_otheruser!=null) {
            int ID = Integer.parseInt(ID_otheruser);
            friendshipDAO.removeFriend(ID, Integer.parseInt((String)httpSession.getAttribute("ID")));
            req.setAttribute("user", personDAO.getUser(ID));
            req.setAttribute("image", photoDAO.getAvatar(ID));
            req.setAttribute("isFriend", friendshipDAO.isFriend(ID,Integer.parseInt((String) httpSession.getAttribute("ID"))));

        }
        req.getRequestDispatcher("friendlist.jsp").forward(req, resp);

    }
}
