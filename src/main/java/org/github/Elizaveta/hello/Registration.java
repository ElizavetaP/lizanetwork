package org.github.Elizaveta.hello;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class Registration extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Registration.class);
    PersonDAO personDAO = null;

    public Registration() {
        super();
        personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newfirstname = req.getParameter("newfirstname");
        String newlastname = req.getParameter("newlastname");
        String newpassword = req.getParameter("newpassword");
        String newemail = req.getParameter("newemail");
        boolean add = personDAO.addUser(newfirstname, newlastname, newpassword, newemail);

        LOG.debug("ragistration is " + add);

        if(!add){
            req.setAttribute("errormessage", "User with that email already exists");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }else {
            String ID;
            try {
                ID = personDAO.getID(newemail);
                PhotoDAO photoDAO = new PhotoDAO();
                photoDAO.setAvatar(ID, "qwerty");
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            req.setAttribute("answermessage", "Successful registration. Please log in");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
       }
}