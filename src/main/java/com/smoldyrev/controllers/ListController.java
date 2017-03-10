package com.smoldyrev.controllers;

import com.smoldyrev.models.pojo.Student;
import com.smoldyrev.services.IStudentService;
import com.smoldyrev.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * Created by smoldyrev on 06.03.17.
 */
@Controller
public class ListController {

    private static Logger logger = Logger.getLogger(ListController.class);

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showListPage(Model model) {
        List<Student> studentsList = studentService.getAllStudents();
        model.addAttribute("studentList", studentsList);
        return "rooms/list";
    }

}
