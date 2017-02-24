package controllers;

import models.pojo.Lection;
import org.apache.log4j.Logger;
import services.LectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by smoldyrev on 24.02.17.
 */
public class LectionsServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LectionsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lection> lections= LectionService.getAllLections();
        logger.debug(lections.size());
        req.setAttribute("lections", lections);
        req.getRequestDispatcher("/lections.jsp").forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
