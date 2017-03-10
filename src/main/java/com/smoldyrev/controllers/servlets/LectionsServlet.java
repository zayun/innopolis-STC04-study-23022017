package com.smoldyrev.controllers.servlets;

import com.smoldyrev.models.pojo.Lection;
import org.apache.log4j.Logger;
import com.smoldyrev.services.LectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
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

    @Autowired(required = true)
    private LectionService lectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lection> lections= lectionService.getAllLections();
        logger.debug(lections.size());
        req.setAttribute("lections", lections);
        req.getRequestDispatcher("/lections.jsp").forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
