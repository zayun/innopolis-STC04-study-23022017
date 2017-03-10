package com.smoldyrev.controllers.servlets;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.LectionDAO;
import com.smoldyrev.models.pojo.Lection;
import org.apache.log4j.Logger;
import com.smoldyrev.services.LectionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by smoldyrev on 24.02.17.
 */
public class AddLectionServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AddLectionServlet.class);

    @Autowired
    private LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String strId = req.getParameter("id");
        int id = (strId.equals("")) ? 0 : Integer.parseInt(req.getParameter("id"));

        Lection lection = new Lection();
        if (id != 0) {
            try {
                lection = LectionDAO.getLectionById(id);
            } catch (UserDAOException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("id", lection.getId());
        req.setAttribute("name", lection.getName());
        req.setAttribute("subject", lection.getSubject());
        req.setAttribute("textlection", lection.getTextLection());
        req.setAttribute("groupid", lection.getGroupid());
        req.setAttribute("date", lection.getDate());

//        req.setAttribute("student", lection);
        req.getRequestDispatcher("/addlection.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on edit");
        String strId = req.getParameter("id");
        int id = (strId.equals("")) ? 0 : Integer.parseInt(req.getParameter("id"));

        Lection lection = new Lection();
        lection.setId(id);
        lection.setName(req.getParameter("name"));
        lection.setTextLection(req.getParameter("textLection"));
        lection.setDate(Date.valueOf(req.getParameter("datetime")));
        lection.setSubject(req.getParameter("subject"));
        lection.setGroupid(Integer.parseInt(req.getParameter("groupid")));

        int count = 0;
        if (id == 0) {
            count = lectionService.insertLection(lection);
        } else {
            count = lectionService.updateLectionOnId(lection);
        }
        if (count != 0) {
            logger.trace("true");
            resp.sendRedirect("/students/lections");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);

        }
    }
}
