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

public class OtherUser extends HttpServlet {
    PersonDAO personDAO;
    PhotoDAO photoDAO;
    FriendshipDAO friendshipDAO;

    public OtherUser() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
        friendshipDAO = new FriendshipDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        int otherUserID = Integer.parseInt(req.getParameter("id"));
        if(otherUserID == (Integer)httpSession.getAttribute(Authorization.ID)){
            resp.sendRedirect("/user");
            return;
        }
        req.setAttribute("user", personDAO.getUser(otherUserID));
        req.setAttribute("image", photoDAO.getAvatar(otherUserID));
        req.setAttribute("isFriend", friendshipDAO.isFriend(otherUserID,(Integer) httpSession.getAttribute(Authorization.ID)));

        req.getRequestDispatcher("otheruser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String action = req.getParameter("action");
        int otherUserID = Integer.parseInt(req.getParameter("id"));
        if (action.equals("add")) {
            friendshipDAO.addFriend(otherUserID, (Integer) httpSession.getAttribute(Authorization.ID));
        } else {
            friendshipDAO.removeFriend(otherUserID, (Integer) httpSession.getAttribute(Authorization.ID));
        }
        resp.sendRedirect("/otheruser?id=" + otherUserID);
    }
}
