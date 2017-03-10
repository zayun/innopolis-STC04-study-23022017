package com.smoldyrev.controllers;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.pojo.Student;
import com.smoldyrev.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

/**
 * Created by smoldyrev on 06.03.17.
 */
@Controller
public class EditStudentController {

    private static Logger logger = Logger.getLogger(EditStudentController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/editstudent", method = RequestMethod.GET)
    public String showEditStudentPage(Model model,
                                      @RequestParam int id) {
        Student student = studentService.getStudentById(id);
        logger.debug(student.getId());
        model.addAttribute("student", student);
        return "rooms/editStudent";
    }

    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public String showAddStudentPage(Model model) {
        return "editStudent";
    }

    @RequestMapping(value = "/deletestudent", method = RequestMethod.GET)
    public String deleteStudentPage(Model model,
                                    @RequestParam int id) {
        if (studentService.deleteStudentOnId(id) > 0) {
            logger.trace(id + " was deleted");
            return "redirect:" + "/list";
        } else {
            logger.trace(id + " not deleted");
            return "error";
        }
    }

    @RequestMapping(value = "/editstudent", method = RequestMethod.POST)
    public String login(Model model,
                        @RequestParam(name = "id") String strId,
                        @RequestParam(name = "name") String name,
                        @RequestParam(name = "birthdate") Date birthdate,
                        @RequestParam(name = "sex") String sex,
                        @RequestParam(name = "group") int group) {

        int id = (strId.equals("")) ? 0 : Integer.parseInt(strId);
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setBirthdate(birthdate);
        student.setSex(sex);
        student.setIdGroup(group);

        int count = 0;
        if (id == 0) {
            count = studentService.insertStudent(student);
        } else {
            count = studentService.updateStudentOnId(student);
        }
        if (count != 0) {
            return "redirect:" + "/list";
        } else {
            return "error";

        }
    }
}
