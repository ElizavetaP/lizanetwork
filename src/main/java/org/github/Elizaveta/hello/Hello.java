package org.github.Elizaveta.hello;

import org.github.Elizaveta.hello.dao.PersonDAO;
import org.github.Elizaveta.hello.dao.PhotoDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Hello extends HttpServlet {

    private PersonDAO personDAO;
    private PhotoDAO photoDAO;

    public Hello() {
        super();

        personDAO = new PersonDAO();
        photoDAO = new PhotoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchname = req.getParameter("searchname");

        req.setAttribute("photos", photoDAO.getAllAvatar());
        if (searchname == null || searchname == "") {
            req.setAttribute("persons", personDAO.getPersons());
        } else {
            System.out.println(searchname);
            req.setAttribute("persons", personDAO.searchByName(searchname));
        }
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
