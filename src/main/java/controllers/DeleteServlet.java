package controllers;

import common.exceptions.UserDAOException;
import org.apache.log4j.Logger;
import services.StudentService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by smoldyrev on 23.02.17.
 */
public class DeleteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on post");
        String id = req.getParameter("id");
        if (StudentService.deleteStudentOnId(Integer.parseInt(id)) > 0) {
            logger.trace(id + " was deleted");
            resp.sendRedirect("/students/list");
        } else {
            logger.trace(id + " not deleted");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(123);

    }
}
