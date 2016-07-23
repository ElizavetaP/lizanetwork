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
    private String filePath;

    public OtherUser() {
        super();
        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
        friendshipDAO = new FriendshipDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Integer id = Integer.parseInt(req.getParameter("id"));
        if (id == null){
            resp.sendRedirect("/");
        }else {
            req.setAttribute("user", personDAO.getUser(id));
            req.setAttribute("image", photoDAO.getAvatar(id));
            req.setAttribute("isFriend", friendshipDAO.isFriend(id,Integer.parseInt((String) httpSession.getAttribute("ID"))));
            req.getRequestDispatcher("otheruser.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String ID_otheruser = req.getParameter("id_otheruser");
        if(ID_otheruser!=null) {
            friendshipDAO.addFriend(Integer.parseInt(ID_otheruser), Integer.parseInt((String) httpSession.getAttribute("ID")));
        }
        String remove_otheruser = req.getParameter("remove_otheruser");
        if(remove_otheruser!=null) {
            friendshipDAO.removeFriend(Integer.parseInt(remove_otheruser), Integer.parseInt((String) httpSession.getAttribute("ID")));
        }
        req.getRequestDispatcher("otheruser.jsp").forward(req, resp);
    }

    public void init() {
        filePath = getServletContext().getInitParameter("file-upload");
    }
}
