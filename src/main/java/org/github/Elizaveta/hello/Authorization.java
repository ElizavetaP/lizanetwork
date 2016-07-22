package org.github.Elizaveta.hello;

import org.apache.log4j.Logger;
import org.github.Elizaveta.hello.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Authorization extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Authorization.class);
    PersonDAO personDAO = null;

    public Authorization() {
        super();
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String logout = req.getParameter("logout");
        if (logout != null) httpSession.setAttribute("isLogged", null);
        req.getRequestDispatcher("authorization.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String logemail = req.getParameter("logemail");
        String logpassword = req.getParameter("logpassword");
        boolean isLogged = personDAO.login(logpassword, logemail);
        LOG.debug("authorization is " + isLogged);

        HttpSession httpSession = req.getSession();
        if (isLogged) {
            String ID;
            ID = personDAO.getID(logemail);
            httpSession.setAttribute("isLogged", "Logged");
            httpSession.setAttribute("ID", ID);
            resp.sendRedirect("user");
        } else {
            req.setAttribute("errormessage", "Incorrect email or password");
            req.getRequestDispatcher("authorization.jsp").forward(req, resp);
        }
    }

}


