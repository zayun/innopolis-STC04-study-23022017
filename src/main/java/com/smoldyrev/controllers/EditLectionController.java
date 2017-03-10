package com.smoldyrev.controllers;

import com.smoldyrev.models.pojo.Lection;
import com.smoldyrev.models.pojo.Student;
import com.smoldyrev.services.LectionService;
import com.smoldyrev.services.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class EditLectionController {

    private static Logger logger = Logger.getLogger(EditLectionController.class);

    @Autowired
    private LectionService lectionService;

    @RequestMapping(value = "/editlection", method = RequestMethod.GET)
    public String showEditLectionPage(Model model,
                                      @RequestParam int id) {
        Lection lection = lectionService.getLectionOnId(id);

        model.addAttribute("lection", lection);
        return "rooms/addlection";
    }

    @RequestMapping(value = "/addlection", method = RequestMethod.GET)
    public String showAddStudentPage(Model model) {
        return "rooms/addlection";
    }

    @RequestMapping(value = "/deletelection", method = RequestMethod.GET)
    public String deleteStudentPage(Model model,
                                    @RequestParam int id) {


        if (lectionService.deleteLectioOnId(id) > 0) {
            return "redirect:" + "/lections";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/addlection", method = RequestMethod.POST)
    public String login(Model model,
                        @RequestParam(name = "id") String strId,
                        @RequestParam(name = "name") String name,
                        @RequestParam(name = "subject") String subject,
                        @RequestParam(name = "textLection") String textlection,
                        @RequestParam(name = "groupid") int group,
                        @RequestParam(name = "datetime") Date date) {

        int id = (strId.equals("")) ? 0 : Integer.parseInt(strId);
        Lection lection = new Lection();
        lection.setId(id);
        lection.setName(name);
        lection.setSubject(subject);
        lection.setTextLection(textlection);
        lection.setGroupid(group);
        lection.setDate(date);

        int count = 0;
        if (id == 0) {
            count = lectionService.insertLection(lection);
        } else {
            count = lectionService.updateLectionOnId(lection);
        }
        if (count != 0) {
            return "redirect:" + "/lections";
        } else {
            return "error";

        }
    }
}



