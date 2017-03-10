package com.smoldyrev.controllers;

import com.smoldyrev.models.pojo.Lection;
import com.smoldyrev.services.LectionService;
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
public class LectionsController {
    private static Logger logger = Logger.getLogger(LectionsController.class);

    @Autowired
    private LectionService lectionService;

    @RequestMapping(value = "/lections", method = RequestMethod.GET)
    public String showListPage(Model model) {


        List<Lection> lections = lectionService.getAllLections();
        logger.debug(lections.size());

        model.addAttribute("lections", lections);

        return "rooms/lections";
    }
}
