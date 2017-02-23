package controllers;

import common.exceptions.UserDAOException;
import models.dao.StudentDao;
import models.pojo.Student;
import org.apache.log4j.Logger;
import services.StudentService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by smoldyrev on 23.02.17.
 */
public class EditServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Student student = null;

        try {
            student = StudentDao.getStudentById(id);
            logger.debug(student.getId());
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("id", student.getId());
        req.setAttribute("name", student.getName());
        req.setAttribute("birthdate", student.getBirthdate());
        req.setAttribute("sex", student.getSex());
        req.setAttribute("group", student.getIdGroup());

        req.setAttribute("student", student);
        req.getRequestDispatcher("/editStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("on edit");
        String strId = req.getParameter("id");
        int id = (strId.equals(""))?0:Integer.parseInt(req.getParameter("id"));
        Student student = new Student();
        student.setId(id);
        student.setName(req.getParameter("name"));
        student.setBirthdate(Date.valueOf(req.getParameter("birthdate")));
        student.setSex(req.getParameter("sex"));
        student.setIdGroup(Integer.parseInt(req.getParameter("group")));
        int count = 0;
        if (id == 0) {
            count = StudentService.insertStudent(student);
        } else {
            count = StudentService.updateStudentOnId(student);
        }
        if (count != 0) {
            logger.trace("true");
            resp.sendRedirect("/students/list");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);

        }
    }
}
