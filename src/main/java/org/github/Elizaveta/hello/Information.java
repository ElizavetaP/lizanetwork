package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Information extends HttpServlet {
    PersonDAO personDAO = null;

    public Information() {
        super();
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        try {
            req.setAttribute("user", personDAO.getUser((String)httpSession.getAttribute("ID")));
            req.getRequestDispatcher("information.jsp").forward(req, resp);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newfirstname = req.getParameter("newfirstname");
        String newlastname = req.getParameter("newlastname");
        String newsex = req.getParameter("newsex");
        String newcountry = req.getParameter("newcountry");
        String newtown = req.getParameter("newtown");
        String newbirthday = req.getParameter("newbirthday");
        String neweducation = req.getParameter("neweducation");
        String newjob = req.getParameter("newjob");
        String newemail = req.getParameter("newemail");

        HttpSession httpSession = req.getSession();
        try {
        personDAO.editUser((String)httpSession.getAttribute("ID"),newfirstname,
        newlastname,newsex,newcountry,newtown,newbirthday,neweducation,newjob,newemail);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("user");
    }
}
