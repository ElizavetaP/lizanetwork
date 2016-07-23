package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.FriendshipDAO;
import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class OtherUser extends HttpServlet {
    PersonDAO personDAO = null;
    PhotoDAO photoDAO = null;
    FriendshipDAO friendshipDAO = null;

    public OtherUser() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
        friendshipDAO = new FriendshipDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String ID_otheruser = req.getParameter("id");
        if (ID_otheruser == null){
            resp.sendRedirect("/");
        }else {
            int ID = Integer.parseInt(ID_otheruser);
            req.setAttribute("user", personDAO.getUser(ID));
            req.setAttribute("image", photoDAO.getAvatar(ID));
            req.setAttribute("isFriend", friendshipDAO.isFriend(ID,Integer.parseInt((String) httpSession.getAttribute("ID"))));
            req.getRequestDispatcher("otheruser.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String ID_otheruser = req.getParameter("id");
        if(ID_otheruser!=null) {
            int ID = Integer.parseInt(ID_otheruser);
            friendshipDAO.addFriend(ID, Integer.parseInt((String) httpSession.getAttribute("ID")));
            req.setAttribute("user", personDAO.getUser(ID));
            req.setAttribute("image", photoDAO.getAvatar(ID));
            req.setAttribute("isFriend", friendshipDAO.isFriend(ID,Integer.parseInt((String) httpSession.getAttribute("ID"))));

        }

        req.getRequestDispatcher("otheruser.jsp").forward(req, resp);
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
        req.getRequestDispatcher("otheruser.jsp").forward(req, resp);

    }

}
